// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package gtk

import scala.language.experimental.macros
import de.surfice.smacrotools.MacroAnnotationHandler

import scala.annotation.{StaticAnnotation, compileTimeOnly}
import scala.reflect.macros.whitebox

@compileTimeOnly("enable macro paradise to expand macro annotations")
class SignalHandler extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro SignalHandler.Macro.impl
}

object SignalHandler {
  class connect(callbackName: String) extends StaticAnnotation


  private[gtk] class Macro(val c: whitebox.Context) extends MacroAnnotationHandler {
    import c.universe._

    override def annotationName = "glib.SignalHandler"
    override def supportsClasses: Boolean = true
    override def supportsTraits: Boolean = false
    override def supportsObjects: Boolean = true
    override def createCompanion: Boolean = false


    implicit class MacroData(val data: Map[String,Any]) {
      type Data = Map[String,Any]
      def handlers: Iterable[(String,DefDef)] = data.getOrElse("handlers",Nil).asInstanceOf[Iterable[(String,DefDef)]]
      def withHandlers(handlers: Iterable[(String,DefDef)]): Data = data.updated("handlers",handlers)
    }


    override def analyze: Analysis = super.analyze andThen {
      case (cls: ClassParts, data) =>
        val handlers = cls.body.collect {
          case Connect(callbackName,callback) => (callbackName,callback)
        }
        (cls, data.withHandlers(handlers))
      case (obj: ObjectParts, data) =>
        val handlers = obj.body.collect {
          case Connect(callbackName,callback) => (callbackName,callback)
        }
        (obj, data.withHandlers(handlers))
      case default => default
    }

    override def transform: Transformation = super.transform andThen {
      case cls: ClassTransformData =>
        cls.addStatements(genConnect(cls.data))
      case obj: ObjectTransformData =>
        obj.addStatements(genConnect(obj.data))
      case default => default
    }

    private def genConnect(data: MacroData): Tree = {
      val stmts = data.handlers.map{
        case (callbackName,callback) =>
          q"builder.addCallbackSymbol(scalanative.native.CQuote(StringContext($callbackName)).c(),${genCallbackPtr(callback)})"
      }
      q"""def registerCallbacks(builder: gtk.GtkBuilder): Unit = {
            import scalanative.native.CFunctionPtr
          ..$stmts
          }"""
    }

    private def genCallbackPtr(m: DefDef): Tree = m.vparamss match {
      case Nil => q"CFunctionPtr.fromFunction0(${m.name})"
      case List(args) => args.length match {
        case 0 =>  q"CFunctionPtr.fromFunction0(${m.name})"
        case 1 =>  q"CFunctionPtr.fromFunction1(${m.name})"
        case 2 =>  q"CFunctionPtr.fromFunction2(${m.name})"
        case 3 =>  q"CFunctionPtr.fromFunction3(${m.name})"
        case 4 =>  q"CFunctionPtr.fromFunction4(${m.name})"
        case 5 =>  q"CFunctionPtr.fromFunction5(${m.name})"
        case 6 =>  q"CFunctionPtr.fromFunction6(${m.name})"
        case 7 =>  q"CFunctionPtr.fromFunction7(${m.name})"
        case 8 =>  q"CFunctionPtr.fromFunction8(${m.name})"
        case 9 =>  q"CFunctionPtr.fromFunction9(${m.name})"
        case 10 =>  q"CFunctionPtr.fromFunction10(${m.name})"
        case 11 =>  q"CFunctionPtr.fromFunction11(${m.name})"
        case 12 =>  q"CFunctionPtr.fromFunction12(${m.name})"
        case 13 =>  q"CFunctionPtr.fromFunction13(${m.name})"
        case 14 =>  q"CFunctionPtr.fromFunction14(${m.name})"
        case 15 =>  q"CFunctionPtr.fromFunction15(${m.name})"
        case 16 =>  q"CFunctionPtr.fromFunction16(${m.name})"
        case 17 =>  q"CFunctionPtr.fromFunction17(${m.name})"
        case 18 =>  q"CFunctionPtr.fromFunction18(${m.name})"
        case 19 =>  q"CFunctionPtr.fromFunction19(${m.name})"
        case 20 =>  q"CFunctionPtr.fromFunction20(${m.name})"
        case 21 =>  q"CFunctionPtr.fromFunction21(${m.name})"
        case 22 =>  q"CFunctionPtr.fromFunction22(${m.name})"
      }
    }

    object Connect {
      def unapply(t: Tree): Option[(String,DefDef)] = t match {
        case m: DefDef =>
          findAnnotation(m.mods.annotations,"gtk.SignalHandler.connect")
            .map{ annot =>
              val name = extractStringConstant(
                extractAnnotationParameters(annot,Seq("callbackName"))
                .apply("callbackName")
                .get
              ).get
              (name,m)
            }
        case _ => None
      }
    }
  }
}
