// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import scala.scalanative.native._
import cobj._

@CObj
object GVariantType {
  val BOOLEAN            :GVariantType =  c"b"
  val BYTE               :GVariantType =  c"y"
  val INT16              :GVariantType =  c"n"
  val UINT16             :GVariantType =  c"q"
  val INT32              :GVariantType =  c"i"
  val UINT32             :GVariantType =  c"u"
  val INT64              :GVariantType =  c"x"
  val UINT64             :GVariantType =  c"t"
  val DOUBLE             :GVariantType =  c"d"
  val STRING             :GVariantType =  c"s"
  val OBJECT_PATH        :GVariantType =  c"o"
  val SIGNATURE          :GVariantType =  c"g"
  val VARIANT            :GVariantType =  c"v"
  val HANDLE             :GVariantType =  c"h"
  val UNIT               :GVariantType =  c"()"
  val ANY                :GVariantType =  c"*"
  val BASIC              :GVariantType =  c"?"
  val MAYBE              :GVariantType =  c"m*"
  val ARRAY              :GVariantType =  c"a*"
  val TUPLE              :GVariantType =  c"r"
  val DICT_ENTRY         :GVariantType =  c"{?*}"
  val DICTIONARY         :GVariantType =  c"a{?*}"
  val STRING_ARRAY       :GVariantType =  c"as"
  val OBJECT_PATH_ARRAY  :GVariantType =  c"ao"
  val BYTESTRING         :GVariantType =  c"ay"
  val BYTESTRING_ARRAY   :GVariantType =  c"aay"
  val VARDICT            :GVariantType =  c"a{sv}"

  /**
   * Compares `type1` and `type2` for equality.
   *
   * @param type1 a GVariantType
   * @param type2 a GVariantType
   * @return true if `type1` and `type2` are exactly equal
   */
  def equal(type1: GVariantType, type2: GVariantType): gboolean = extern

  /**
   * Checks if `tpe` is a subtype of `supertpe`.
   *
   * @param tpe a GVariantType
   * @param supertpe a GVariantType
   * @return true if `tpe` is a subtype of `supertpe`
   */
  def isSubtypeOf(tpe: GVariantType, supertpe: GVariantType): gboolean = extern

  /**
   * Creates a new GVariantType corresponding to the specified type string.
   * It is appropriate to call [[GVariantType.free]] on the return value.
   *
   * @param tpeString a valid GVariant type string
   * @return  a new GVariantType
   */
  @name("g_variant_type_new")
  def newType(tpeString: CString): GVariantType = extern

  /**
   * Frees a GVariantType that was allocated with [[GVariantType.copy]], [[GVariantType.newType]]
   * or one of the container type constructor functions.
   *
   * @param tpe a GVariantType, or null
   */
  def free(tpe: GVariantType): Unit = extern

  /**
   * Makes a copy of a GVariantType.
   * It is appropriate to call [[GVariantType.free()]] on the return value.
   *
   * @param tpe GVariantType to copy (must not be null)
   */
  def copy(tpe: GVariantType): GVariantType = extern

  /**
   * Checks if the specified type string is a valid GVariant type string.
   *
   * @param tpeString
   * @return true if `tpeString` is exactly one valid type string.
   */
  def stringIsValid(tpeString: CString): gboolean = extern

  /**
   * Scan for a single complete and valid GVariant type string in string.
   * The memory pointed to by limit (or bytes beyond it) is never accessed.
   * If a valid type string is found, endptr is updated to point to the first character past the end of the string that was found and true is returned.
   * If there is no valid type string starting at string , or if the type string does not end before limit then false is returned.
   * For the simple case of checking if a string is a valid type string, see [[stringIsValid]].
   *
   * @param string a pointer to any string
   * @param limit the end of string, or null
   * @param endptr location to store the end pointer, or null
   * @return true if a valid type string was found
   */
  def stringScan(string: CString, limit: CString)(endptr: Out[CString]): Boolean = extern

  /**
   * Returns the length of the type string for the specified type.
   * @param tpe
   */
  def getStringLength(tpe: GVariantType): gsize = extern

  /**
   * Returns the type string corresponding to the given type.
   * @param tpe
   */
  def peekString(tpe: GVariantType): CString = extern

  /**
   * Returns a newly-allocated copy of the type string corresponding to the specified type.
   * @param tpe
   */
  def dupString(tpe: GVariantType): CString = extern

  /**
   * Determines if the given type is definite (ie: not indefinite).
   *
   *  A type is definitie if its type string does not contain any indefinite type characters ('*','?', or 'r')
   * @param tpe
   */
  def isDefinite(tpe: GVariantType): gboolean = extern

  /**
   * Determines if the given type is a container type.
   * Container types are array, maybe, tuple, or dicitionary entry types plus the variant type.
   *
   * @param tpe
   */
  def isContainer(tpe: GVariantType): gboolean = extern

  /**
   * Determines if the given type is a basic type.
   *
   * Basic types are booleans, bytes, integers, doubles, strings, object paths and signatures.
   *
   * @param tpe
   */
  def isBasic(tpe: GVariantType): gboolean = extern

  /**
   * Determines if the given type is a maybe type.
   * This is true if the type string starts with an 'm'.
   *
   * @param tpe
   * @return
   */
  def isMaybe(tpe: GVariantType): gboolean = extern

  /**
   * Determines if the given type is an array type.
   *
   * @param tpe
   */
  def isArray(tpe: GVariantType): gboolean = extern

  /**
   * Determines if the given type is a tuple type.
   * This is true if the type string starts with a '(' or `tpe` is [[GVariantType.TUPLE]].
   *
   * @param tpe
   */
  def isTuple(tpe: GVariantType): gboolean = extern

  /**
   * Determines if the given type is a dictionary entry type.
   * This is true if the type string starts with a '{'.
   *
   * @param tpe
   */
  def isDictEntry(tpe: GVariantType): gboolean = extern

  /**
   * Determines if the given type is the variant type.
   *
   * @param tpe
   */
  def isVariant(tpe: GVariantType): gboolean = extern

  /**
   * Hashes `tpe`.
   *
   * @param tpe
   * @return the hash value
   */
  def hash(tpe: GVariantType): guint = extern

  /**
   * Constructs the type corresponding to a maybe instance containing type of `element` or Nothing.
   *
   * It is appropriate to call [[GVariantType.free]] on the return value.
   *
   * @param element
   * @return a new maybe GVariantType
   */
  def newMaybe(element: GVariantType): GVariantType = extern

  /**
   * Constructs the type corresponding to an array of elements of the type `element`.
   *
   * It is appropriate to call [[GVariantType.free]] on the return value.
   *
   * @param element
   * @return a new array GVariantType
   */
  def newArray(element: GVariantType): GVariantType = extern

  /**
   * Constructs a new tuple type from `items`.
   *
   * It is appropriate to call [[GVariantType.free]] on the return value.
   *
   * @param items an array of GVariantTypes, one for each item
   * @param length the length of `items`, or -1 if items is null-terminated
   * @return a new tuple GVariantType
   */
  def newTuple(items: Ptr[GVariantType], length: gint): GVariantType = extern

  /**
   * Constructs the type corresponding to a dictionary entry with a key of type `key` and
   * a value of type `value`.
   *
   * It is appropriate to call [[GVariantType.free]] on the return value.
   *
   * @param key a basic GVariantType
   * @param value a GVariantType
   * @return a new dictionary entry GVariantType
   */
  def newDictEntry(key: GVariantType, value: GVariantType): GVariantType = extern

  /**
   * Determines the element type of an array or maybe type.
   * This function may only be used with array or maybe types.
   *
   * @param tpe
   * @return the element type of `tpe`
   */
  def element(tpe: GVariantType): GVariantType = extern

  /**
   * Determines the number of items contained in a tuple or dictionary entry type.
   *
   * This function may only be used with tuple or dictionary entry types, but must not be used with the generic tiple type
   * [[TUPLE]]. In the case of a dictionary entry type, this function will always return 2.
   *
   * @param tpe
   * @return the number of items in `tpe`
   */
  def nItems(tpe: GVariantType): gsize = extern

  /**
   * Determines the first item type of a tuple or dictionary entry type.
   *
   * This function may only be used with tuple or dictionary entry types, but must not be used with the
   * generic tuple type [[TUPLE]]. In the case of a dictionary entry type, this returns the type of the key.
   * null is returned in case of `tpe` being [[UNIT]].
   * This call, together with [[next]] provides an itertor interface over tuple and dictionary entry types.
   * @param tpe
   * @return the first item type, or null
   */
  def first(tpe: GVariantType): GVariantType = extern

  /**
   * Determines the next item type of a tuple or dictionary entry type.
   * `tpe` must be the result of a previous call to [[first]] or [[next]].
   * If called on the key type of a dictionary entry then this call returns the value type.
   * If called on the value type of a dictionary entry then this call returns null.
   * For tuples, null is returned when type is the last item in a tuple.
   * @param tpe
   * @return the next GVariantType after `tpe`, or null.
   */
  def next(tpe: GVariantType): GVariantType = extern

  /**
   * Determines the key type of a dictionary entry type.
   * This function may only be used with a dictionary entry type.
   *
   * @param tpe
   * @return the key type of the dictionary.
   */
  def key(tpe: GVariantType): GVariantType = extern

  /**
   * Determines the value type of a dictionary entry type.
   * This function may only be used with a dictionary entry type.
   *
   * @param tpe
   * @return the value type of the dictionary entry
   */
  def value(tpe: GVariantType): GVariantType = extern
}
