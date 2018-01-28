// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import de.surfice.smacrotools.debug

import scala.scalanative.native.CObj.{Out, returnsThis}
import scalanative.native._

/**
 * Strongly typed value datatype.
 *
 * @see [[https://developer.gnome.org/glib/stable/glib-GVariant.html]]
 *
 * @constructor
 * Creates a new GVariant instance.
 * @param formatString a GVariant format string
 * @param arg argument, as per formatString
 */
@CObj
final class GVariant(formatString: CString, arg: Any) extends CObj.CObjWrapper with GAllocated {

  @inline override def free(): Unit = unref()

  /**
   * Decreases the reference count of this value.
   * When its reference count drops to 0, the memory used by the variant is freed.
   */
  @inline def unref(): Unit = extern

  /**
   * Increases the reference count of this value.
   *
   * @return this
   */
  @returnsThis
  @inline def ref(): GVariant = extern

  /**
   * GVariant uses a floating reference count system.
   *
   * All constructor functions return floating references.
   * Calling this method on a GVariant with a floating reference will convert the floating reference into a full reference.
   * Calling this method on a non-floating GVariant results in an additional normal referecne being added.
   *
   * In other words, if the value is floating, then this call "assumes ownership" of the floating reference,
   * converting it to a normal reference. If the value is not floating, then this call adds a new normal reference
   * increasing the reference count by one.
   *
   * All calls that result in a GVariant instance being inserted into a container will call [[refSink]] on the instance.
   * This means that if the value was just created (and has only its floating reference) then the container will assume
   * sole ownership of the value at that point and the caller will not need to unreference it.
   * This makes certain common styles of programming much easier while still maintaining normal refcounting semantics
   * in situations where values are not floating.
   * @return this
   */
  @returnsThis
  @inline def refSink(): GVariant = extern

  /**
   * Checks whether this value has a floating reference count.
   */
  @inline def isFloating(): gboolean = extern

  /**
   * If this instance is floating, sink it; otherwise, do nothing.
   *
   * Typically you want to use [[refSink]] in order to automatically do the correct thing with respect to floating
   * or non-floating references, but there is one specific scenario where this method is helpful.
   *
   * The situation where this function is helpful is when creating an API that allows the user to provide a callback
   * function that returns a GVariant. We certainly want to allow the user the flexibility to return a non-floating
   * reference from this callback (for the case where the value that is being returned already exists).
   *
   * At the same time, the style of the GVariant API makes it likely that for newly-created GVariant instances,
   * the user can be saved some typing if they are allowed to return a GVariant with a floating reference.
   *
   * Using this method on the return value of the user's callback allows the user to do whichever is more convenient for them.
   * The caller will always receive exactly one full reference to the value: either the one that was returned in the first place,
   * or a floating reference that has been converted to a full reference.
   *
   * This function has an odd interaction when combined with [[refSink]] running at the same time in another thread on
   * the same GVariant instance. If [[refSink]] runs first then the result will be that the floating reference is converted
   * to a hard reference. If [[takeRef]] runs first then the result will be that the floating reference is converted to a hard reference and an additional reference on top of that one is added. It is best to avoid this situation.
   * @return this
   */
  @returnsThis
  @inline def takeRef(): GVariant = extern

  /**
   * Returns the type of this value.
   */
  @inline def getType(): GVariantType = extern

  /**
   * Returns the type string for this GVariant.
   */
  @inline def getTypeString(): CString = extern

  /**
   * Checks if this value has a type matching the specified one.
   */
  @inline def isOfType(tpe: GVariantType): gboolean = extern

  /**
   * Checks if this instance is a container.
   */
  @inline def isContainer(): gboolean = extern

  // classify()

  // checkFormatString()

  // get()

  // getVa()


  /**
   * Returns the boolean value of this GVariant.
   * It is an error to call this function with a value of any type other than [[GVariantType.BOOLEAN]].
   */
  @inline def getBoolean(): gboolean = extern

  /**
   * Returns the byte value of this GVariant.
   * It is an error to call this function with a value of any type other than [[GVariantType.BYTE]].
   */
  @inline def getByte(): guchar = extern

  /**
   * Returns the 16-bit signed integer value of this GVariant.
   * It is an error to call this function with a value of any type other than [[GVariantType.INT16]].
   */
  @inline def getInt16(): gint16 = extern

  /**
   * Returns the 16-bit unsigned integer value of this GVariant.
   * It is an error to call this function with a value of any type other than [[GVariantType.UINT16]].
   */
  @inline def getUint16(): guint16 = extern

  /**
   * Returns the 32-bit signed integer value of this GVariant.
   * It is an error to call this function with a value of any type other than [[GVariantType.INT32]].
   */
  @inline def getInt32(): gint32 = extern

  /**
   * Returns the 32-bit unsigned integer value of this GVariant.
   * It is an error to call this function with a value of any type other than [[GVariantType.UINT32]].
   */
  @inline def getUint32(): guint32 = extern

  /**
   * Returns the 64-bit signed integer value of this GVariant.
   * It is an error to call this function with a value of any type other than [[GVariantType.INT64]].
   */
  @inline def getInt64(): gint64 = extern

  /**
   * Returns the 64-bit unsigned integer value of this GVariant.
   * It is an error to call this function with a value of any type other than [[GVariantType.UINT64]].
   */
  @inline def getUint64(): guint64 = extern

  /**
   * Returns the handle value of this GVariant.
   * It is an error to call this function with a value of any type other than [[GVariantType.HANDLE]].
   */
  @inline def getHandle(): gint32 = extern

  /**
   * Returns the double precision floating point value of this GVariant.
   * It is an error to call this function with a value of any type other than [[GVariantType.DOUBLE]].
   */
  @inline def getDouble(): gdouble = extern

  /**
   * Returns the string value of this GVariant.
   * This method can be called for types [[GVariantType.STRING]], [[GVariantType.OBJECT_PATH]] and
   * [[GVariantType.SIGNATURE]].
   */
  @inline def getString(): CString = extern

  /**
   * Similar to [[getString]] except taht instead of returning a pointer to the original string,
   * the string is duplicated.
   * The return value must be [[GLib.free]]d.
   *
   * @param length location to store the length of the string, or null
   */
  @inline def dupString()(length: Out[gsize]): CString = extern

  /**
   * Unboxes this GVariant.
   */
  @inline def getVariant(): GVariant = extern

  // getStrv()

  // dupStrv()

  // getObjv()

  // dupObjv()

  /**
   * Returns the string value of this GVariant.
   * The string has no particular encoding.
   *
   * If the array does not end with a nul terminator character, the empty string is returned.
   * For this reason, you can always trust that a non-NULL nul-terminated string will be returned by this function.
   *
   * If the array contains a nul terminator character somewhere other than the last byte then the returned string is the string,
   * up to the first such nul character.
   *
   * [[getFixedArray]] should be used instead if the array contains arbitrary data that could not be nul-terminated or could contain nul bytes.
   *
   * It is an error to call this function with a value that is not an array of bytes.
   *
   * The return value remains valid as long as this GVariant exists.
   */
  @inline def getBytestring(): CString = extern

  /**
   * Similar to [[getBytestring()]] except that instead of returning a pointer to the original string,
   * the string is duplicated. The return value must be freed using [[GLib.free]]
   */
  @inline def dupBytestring(): CString = extern

  // getBytestringArray()

  // dupBytestringArray()

  /**
   * Extract the value of this maybe GVariant. If the value is Nothing, then this method returns null.
   */
  @inline def getMaybe(): GVariant = extern

  /**
   * Determines the number of children in this container GVariant instance.
   */
  @inline def nChildren(): gsize = extern

  /**
   * Reads a child item out of this container GVariant instance.
   * @param index index of the child to fetch
   */
  @inline def getChildValue(index: gsize): GVariant = extern

  // getChild()

  // lookupValue()

  // lookup()

  // getFixedArray()

  /**
   * Returns the number of bytes that would be required to store this instance with [[store]].
   */
  @inline def getSize(): gsize = extern

  /**
   * Returns a pointer to the serialised form of this GVariant instance.
   */
  @inline def getData(): gconstpointer = extern

  /**
   * Returns a pointer to the serialised form of this GVariant instance.
   */
  @inline def getDataAsBytes(): GBytes = extern

  /**
   * Stores the serialised form of this GVariant at `data`.
   * @param data
   */
  @inline def store(data: gpointer): Unit = extern

  /**
   * Performs a byteswapping operation on the contents of this GVariant.
   */
  @inline def byteswap(): GVariant = extern

  /**
   * Returns the normal form representation of this GVariant.
   */
  @inline def getNormalForm(): GVariant = extern

  /**
   * Checks if this instance is in normal form.
   */
  @inline def isNormalForm(): gboolean = extern

  /**
   * Generates a hash value for this instance
   */
  @inline def hash(): guint = extern

  /**
   * Returns a pretty-printed representation of this instance in the format understood by [[parse]].
   *
   * @param typeAnnotate true if the type information should be included
   * @return a newly-allocated string holding the result
   */
  @inline def print(typeAnnotate: gboolean): CString = extern

  // printString()

  // iterCopy()

  // iterFree()

  // iterInit()

  // iterNChildren()

  // iterNew()

  // iterNextValue()

  // iterNext()

  // iterLoop()


  override def equals(obj: scala.Any): Boolean =  obj match {
    case other: GVariant => GVariant.equal(this,other)
    case _ => false
  }

  override def hashCode(): gint = hash().toInt
}

object GVariant {

  def apply(ref: gpointer): GVariant = new GVariant(ref.cast[CObj.Ref[Byte]])
  def apply(value: Int): GVariant = GVariant.int32(value)
  def apply(value: Double): GVariant = GVariant.double(value)
  def apply(value: Boolean): GVariant = GVariant.boolean(value)
  def apply(value: String): GVariant = Zone{ implicit z => GVariant.string(toCString(value)) }

  /**
   * Checks if `one` and `two` have the same type and value.
   *
   * @param one
   * @param two
   */
  @inline def equal(one: GVariant, two: GVariant): gboolean = extern

  /**
   * Compares two GVariant types of the same type.
   *
   * If you only require an equality comparison, [[equal]] is more general.
   *
   * @param one a basic-typed GVariant instance
   * @param two a GVariant instance of the same type
   * @return negative value if one < two; zero if one = two; positive value if one > two
   */
  @inline def compare(one: GVariant, two: GVariant): gint = extern

  /**
   * Creates a new boolean GVariant  instance.
   *
   * @param value boolean value
   */
  @name("g_variant_new_boolean")
  @inline def boolean(value: Boolean): GVariant = extern

  /**
   * Creates a new byte GVariant instance.
   *
   * @param value byte value
   */
  @name("g_variant_new_byte")
  @inline def byte(value: guchar): GVariant = extern

  /**
   * Creates a new int16 GVariant instance.
   *
   * @param value a gint16 value
   */
  @name("g_variant_new_int16")
  @inline def int16(value: gint16): GVariant = extern

  /**
   * Creates a new uint16 GVariant instance.
   *
   * @param value a guint16 value
   */
  @name("g_variant_new_uint16")
  @inline def uint16(value: guint16): GVariant = extern

  /**
   * Creates a new int32 GVariant
   * @param value a gint32 value
   */
  @name("g_variant_new_int32")
  @inline def int32(value: gint32): GVariant = extern

  /**
   * Creates a new uint32 GVariant
   * @param value a guint32 value
   */
  @name("g_variant_new_uint32")
  @inline def uint32(value: guint32): GVariant = extern

  /**
   * Creates a new int64 GVariant.
   * @param value a gint64 value
   */
  @name("g_variant_new_int64")
  @inline def int64(value: gint64): GVariant = extern

  /**
   * Creates a new uint64 GVariant.
   * @param value a guint64 value
   */
  @name("g_variant_new_uint64")
  @inline def uint64(value: guint64): GVariant = extern

  /**
   * Creates a new handle GVariant instance.
   * By convention, handles are indexes into an array of file descriptors that are sent alongside a D-Bus message.
   * If you're not interacting with D-Bus, you probably don't need them.
   *
   * @param value a gint32 value
   */
  @name("g_variant_new_handle")
  @inline def handle(value: gint32): GVariant = extern

  /**
   * Creates a new double GVariant
   * @param value a gdouble value
   */
  @name("g_variant_new_double")
  @inline def double(value: gdouble): GVariant = extern

  /**
   * Creates a string GVariant with the contents of `value`.
   * The string must be valid UTF-8, and must not be null. To encode potentially-null strings,
   * use [[GVariant()]] with `ms` as the format string.
   * @param value a normal UTF-8 nul-terminated string
   */
  @name("g_variant_new_string")
  @inline def string(value: CString): GVariant = extern

  /**
   * Creates a string GVariant with the contents of `value`.
   * The string must be valid UTF-8, and must not be null. To encode potentially-null strings,
   * use this with [[maybe]].
   * This function consumes `value`. free will be called on string when it is no longer required.
   * You must not modify or access string in any other way after passing it to this function.
   * It is even possible that string is immediately freed.
   * @param value a normal UTF-8 nul-terminated string
   */
  @name("g_variant_new_take_string")
  @inline def takeString(value: CString): GVariant = extern

  // printf

  /**
   * Creates a D-Bus object path GVariant.
   * `objectPath` must be a valid D-Bus path; use [[isObjectPath]] if you're not sure.
   * @param objectPath a normal C nul-terminated string
   */
  @name("g_variant_new_object_path")
  @inline def objectPath(objectPath: CString): GVariant = extern

  /**
   * Determines if the specified string is a valid D-Bus object path.
   * A valid object path starts with '/' followed by zero or more sequences of characters separated by '/' characters.
   * Each sequence must contain only the characters "A-Z[0-9]_". No sequence (including the one following the final '/' character)
   * may be empty.
   * @param string a normal C nul-terminated string
   */
  @inline def isObjectPath(string: CString): gboolean = extern

  /**
   * Creates a D-Bus type signature GVariant.
   * `signature` must be a valid D-Bus type signature. Use [[isSignature]] if you're not sure.
   * @param signature a normal C nul-terminated string
   */
  @name("g_variant_new_signature")
  @inline def signature(signature: CString): GVariant = extern

  /**
   * Determines if the given string is a valid D-Bus type signature.
   * @param string a normal C nul-terminated string
   */
  @inline def isSignature(string: CString): gboolean = extern

  /**
   * Boxes `value`.
   * The result is a GVariant instance representing a variant containing the original value.
   *
   * If `value` is a floating reference, the new instance takes ownership of `value`.
   *
   * @param value a GVariant instance
   */
  @name("g_variant_new_variant")
  @inline def variant(value: GVariant): GVariant = extern

  // strv()

  // objv

  /**
   * Creates an array-of-bytes GVariant.
   * This function is just like [[string]] except that the string need not be valid UTF-8.
   * The nul terminator character at the end of the string is stored in the array.
   * @param string
   */
  @name("g_variant_new_bytestring")
  @inline def bytestring(string: CString): GVariant = extern

  // bytestringArray()

  /**
   * Depending on if child is null, either wraps child inside of a maybe container or creates a Nothing instance for the given type.
   *
   * At least one of `childType` and `child` must be non-null.
   * If child_type is non-null then it must be a definite type. If they are both non-null then `childType` must be the
   * type of child. If child is a floating reference, the new instance takes ownership of child .
   *
   * @param childType the GVariantType of the child, or null
   * @param child the child value, or null
   */
  @name("g_variant_new_maybe")
  @inline def maybe(childType: CString, child: GVariant): GVariant = extern

  // array()

  // tuple()

  /**
   * Creates a new dictionary entry GVariant.
   * `key` and `value` must be non-null. If the `key` or `value` are floating references, the new instance
   * takes ownership of them.
   *
   * @param key a basic GVariant
   * @param value a GVariant
   */
  @name("g_variant_new_dict_entry")
  @inline def dictEntry(key: GVariant, value: GVariant): GVariant = extern

  // fixedArray()

  // fromData

  /**
   * Constructs a new GVariant instance from serialised data.
   *
   * @param tpe a GVariantType
   * @param bytes the serialised GVariant data
   * @param trusted if the contents of `bytes` are trusted
   */
  @name("g_variant_new_from_bytes")
  @inline def fromBytes(tpe: GVariantType, bytes: GBytes, trusted: gboolean): GVariant = extern
}
