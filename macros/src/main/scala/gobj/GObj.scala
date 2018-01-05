// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gobj

import de.surfice.smacrotools.MacroAnnotationHandler

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.whitebox
import scala.scalanative.native.Ptr

class GObj() extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro GObj.GObjMacro.impl
}

object GObj extends {
  private[gobj] class GObjMacro(val c: whitebox.Context) extends MacroAnnotationHandler {
    import c.universe._

    override def annotationName = "GObj"
    override def supportsClasses: Boolean = true
    override def supportsTraits: Boolean = false
    override def supportsObjects: Boolean = true
    override def createCompanion: Boolean = true

    private val tPtrByte = weakTypeOf[Ptr[Byte]]
    private val selfPtr = q"val self: $tPtrByte"

    implicit class MacroData(var data: Map[String, Any]) {
      type Data = Map[String,Any]
      type Externals = Map[String,(String,Tree)]

      def externals: Externals = data.getOrElse("externals", Nil).asInstanceOf[Externals]
      def externals_=(externals: Externals): Data = {
        data += "externals" -> externals
        data
      }
    }

    override def analyze: Analysis = super.analyze andThen {
      case (cls: TypeParts, data) =>
        val externalPrefix = "gtk_widget_"
        val externals = cls.body.collect {
          case t@DefDef(mods, name, types, args, rettype, rhs) if isExtern(rhs) =>
            genExternalBinding(externalPrefix,t,true)
        }
        (cls, data.externals = externals.toMap)
      case default => default
    }

    override def transform: Transformation = super.transform andThen {
      /* transform class */
      case cls: ClassTransformData =>
        val prefix = q"import ${cls.modParts.companion.get.name}.__ext._"

        val transformedBody = prefix +:
          cls.modParts.body map {
          case t@DefDef(mods, name, types, args, rettype, rhs) if isExtern(rhs) =>
            val externalName = cls.data.externals(name.toString)._1
            genExternalCall(externalName,t,true)
          case default => default
        }
        cls
          .updBody(transformedBody)
          .updCtorMods(Modifiers(Flag.PROTECTED)) // ensure that the class can't be instantiated using new
      /* transform companion object */
      case obj: ObjectTransformData =>
        val transformedBody = obj.modParts.body :+ genBindingsObject(obj.data)
        obj.updBody(transformedBody)
      case default => default
    }


    private def genExternalBinding(prefix: String, scalaDef: DefDef, isInstanceMethod: Boolean): (String,(String,Tree)) = {
      val scalaName = scalaDef.name.toString
      val externalName = genExternalName(prefix,scalaName)
      val externalParams =
        if(isInstanceMethod) scalaDef.vparamss match {
          case List(params) => List(selfPtr +: params)
          case _ =>
            c.error(c.enclosingPosition,"methods with multiple parameter lists are not supported for @GObj classes")
            ???
        }
        else scalaDef.vparamss
      val externalDef = DefDef(scalaDef.mods,TermName(externalName),scalaDef.tparams,externalParams,scalaDef.tpt,scalaDef.rhs)

      (scalaName,(externalName,externalDef))
    }

    private def genExternalCall(externalName: String, scalaDef: DefDef, isInstanceMethod: Boolean): DefDef = {
      import scalaDef._
      val args = paramNames(vparamss.head)
      val call = q"${TermName(externalName)}(this.cast[$tPtrByte],..$args)"
      DefDef(mods,name,tparams,vparamss,tpt,call)
    }

    def genExternalName(prefix: String, scalaName: String): String =
      prefix + scalaName.replaceAll("([A-Z])","_$1").toLowerCase

    private def genBindingsObject(data: MacroData): Tree = {
      val defs = data.externals.values.map(_._2)
      q"""@scalanative.native.extern object __ext {..$defs}"""
    }

    private def isExtern(rhs: Tree): Boolean = rhs match {
      case Ident(TermName(id)) =>
        id == "extern"
      case Select(_,name) =>
        name.toString == "extern"
      case x =>
        false
    }

  }

}
