// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

import de.surfice.smacrotools.debug
import glib.convert.Wrappers

import scala.scalanative.native.CObj.Out
import scalanative.native._


/**
 * Key-value file parser -- parses .ini-like config files.
 *
 * @see [[https://developer.gnome.org/glib/stable/glib-Key-value-file-parser.html#g-key-file-ref]]
 *
 * @note You must either `free()` instances of GKeyFile manually, or use [[GKeyFile()]] with an
 *       [[AutoreleasePool]] for allocation.
 */
@CObj
final class GKeyFile extends GAllocated {
  import GKeyFile._

  /** Clears all Clears all keys and groups from this object, and decreases the reference count by 1.
   * If the reference count reaches zero, frees the key file and all its allocated memory.
   */
  @inline def free(): Unit = extern

  /**
   * Increases the reference count of this object.
   */
  @inline def ref(): Unit = extern

  /**
   * Decreases the reference count of this object.
   * @return
   */
  @inline def unref(): Unit = extern

  /**
   * Sets the character which is used to separate values in lists.
   *
   * @param separator value separator (default: ';')
   */
  @inline def setListSeparator(separator: gchar): Unit = extern

  /**
   * Loads a key file into this GKeyFile.
   *
   * @param file name of the file to load (in GLib filename encoding)
   * @param flags flags
   * @param error return location for a GError, or null
   * @return  true if the file was loaded successfully
   */
  @inline def loadFromFile(file: Ptr[gchar], flags: GKeyFileFlags)(implicit error: Out[Error]): gboolean = extern

  /**
   * Parses the contents of the specified string and loads the values into this GKeyFile.
   *
   * @param data data to be parsed
   * @param length length of data (or (gsize)-1, if data is nul-terminated
   * @param flags flags
   * @param error return location for a GError, or null
   * @return true if the string was parsed successfully
   */
  @inline def loadFromData(data: CString, length: gsize, flags: GKeyFileFlags)(implicit error: Out[Error]): gboolean = extern

  /**
   * Loads the data from the specified GBytes object.
   *
   * @param bytes GBytes
   * @param flags flags
   * @param error return location for a GError, or null
   * @return true, if the data was loaded successfully
   */
  @inline def loadFromBytes(bytes: GBytes, flags: GKeyFileFlags)(implicit error: Out[GError]): gboolean = extern

//  /**
//   *
//   * @param file
//   * @param fullPath
//   * @param flags
//   * @param error
//   * @return
//   */
//  @inline def loadFromDataDirs(file: CString, fullPath: Ptr[CString], flags: GKeyFileFlags)(implicit error: Out[GError]): gboolean = extern
  /**
   * Returns the contents of this GKeyFile as string.
   *
   * @param gsize return location for the length of the returned string, or null
   * @param error return location for a GError, or null
   * @return a newly allocated string holding the contents of this GKeyFile
   */
  @inline def toData(implicit gsize: Out[gsize], error: Out[GError]): CString = extern

  /**
   * Writes the contents of this GKeyFile to `filename`.
   *
   * @param filename the name of the file to write to
   * @param error return location for a GError, or null
   * @return true, if the successful
   */
  @inline def saveToFile(filename: CString)(implicit error: Out[GError]): gboolean = extern

  /**
   * Returns the anem of the start group of the file.
   */
  @inline def getStartGroup(): CString = extern

  /**
   * Returns all groups in this GKeyFile.
   * The returned array is null-terminated, so `length` may optionally be null.
   *
   * @param length return location for the number of returned groups, or null
   * @return a newly allocated, null-terminated array of string. Use [[GLib.strfreev()]] to free it.
   */
  @inline def getGroups()(implicit length: Out[gsize]): Ptr[CString] = extern

  /**
   * Returns a Seq with the names of all groups in this GKeyFile.
   *
   * @note You must either `free()` the returned Seq manually, or use an [[AutoreleasePool]]!
   *
   * @return Wrapper around array returned by [[getGroups()]]
   */
  def groups(implicit pool: AutoreleasePool = null): GSeq[CString] = Zone{ implicit z: Zone =>
    val size = Out.alloc[gsize]
    val array = getGroups()(size)
    val seq = Wrappers.NullTerminatedStringArray(array,size.value.get.toInt)
    if(pool!=null)
      pool.register(seq)
    seq
  }

  /**
   * Returns all keys for the specified groupName.
   * The returned array is null-terminated, so `length` may optionally be null.
   * If the groupName does not exists, null is returned and the `error` is set.
   *
   * @param groupName a group name
   * @param length return location for the number of returned keys, or null
   * @param error return location for a GError, or null
   * @return a newly allocated, null-terminated array of string. Use [[GLib.strfreev()]] to free it.
   */
  @inline def getKeys(groupName: CString)(implicit length: Out[gsize], error: Out[GError]): Ptr[CString] = extern

  /**
   * Returns a Seq with all keys in the specified group.
   *
   * @note You must either call `free()` the returned Seq manually, or use an [[AutoreleasePool]]!
   *
   * @param groupName group for which all keys should be returned
   * @param pool GAutoreleasePool (if =null, you must free the Seq manually!)
   * @return Wrapper around array returned by [[getKeys()]]
   */
  def keys(groupName: CString)(implicit pool: AutoreleasePool = null): GSeq[CString] = Zone{ implicit z: Zone =>
    val size = Out.alloc[gsize]
    val array = getKeys(groupName)(size,null)
    val seq = Wrappers.NullTerminatedStringArray(array,size.value.get.toInt)
    if(pool!=null)
      pool.register(seq)
    seq
  }

  /**
   * Returns true, if the specified groupName exists.
   *
   * @param groupName
   */
  @inline def hasGroup(groupName: CString): gboolean = extern

  /**
   * Returns true if the specified groupName / key combination exists.
   *
   * @param groupName
   * @param key
   * @param error return location for a GError, or null
   */
  @inline def hasKey(groupName: CString, key: CString)(implicit error: Out[GError]): gboolean = extern

  /**
   * Returns the raw value associated with `key` under `groupName`, or null
   * Use [[getString()]] to retrieve an unescaped UTF-8 string.
   *
   * @param groupName
   * @param key
   * @param error return location for a GError, or null
   */
  @inline def getValue(groupName: CString, key: CString)(implicit error: Out[GError]): CString = extern

  /**
   * Returns the string value associated with `key` under `groupName`, or null.
   *
   * @param groupName
   * @param key
   * @param error return location for a GError, or null
   */
  @inline def getString(groupName: CString, key: CString)(implicit error: Out[GError]): CString = extern

  /**
   * Returns the value associated with `key` under `groupName` translated to the given `locale`, or null
   * if the value does not exist.
   * If `locale` is null, then the current locale is assumed.
   *
   * @param groupName
   * @param key
   * @param locale locale identifier, or null
   * @param error location for a GError, or null
   */
  @inline def getLocaleString(groupName: CString, key: CString, locale: CString)(implicit error: Out[GError]): CString = extern

  /**
   * Returns the value associated with `key` under `groupName` as boolean, or `false` if the value does not exist.
   *
   * @param groupName
   * @param key
   * @param error location for a GError, or null
   */
  @inline def getBoolean(groupName: CString, key: CString)(implicit error: Out[GError]): gboolean = extern

  /**
   * Returns the value associated with `key` under `groupName` as integer, or 0 if the value does not exist.
   *
   * @param groupName
   * @param key
   * @param error location for a GError, or null
   */
  @inline def getInteger(groupName: CString, key: CString)(implicit error: Out[GError]): gint = extern

  /**
   * Returns the value associated with `key` under `groupName` as 64-bit integer, or 0 if the value does not exist.
   * @param groupName
   * @param key
   * @param error location for a GError, or null.
   */
  @inline def getInt64(groupName: CString, key: CString)(implicit error: Out[GError]): gint64 = extern

  /**
   * Returns the value associated with `key` under `groupName` as unsigned 64-bit integer, or 0 if the value does not exist.
   * @param groupName
   * @param key
   * @param error location for a GError, or null.
   */
  @name("g_key_file_guint64")
  @inline def getUInt64(groupName: CString, key: CString)(implicit error: Out[GError]): guint64 = extern

  /**
   * Returns the value associated with `key` under `groupName` as a double, or 0.0 if the value does not exist.
   * @param groupName
   * @param key
   * @param error location for a GError, or null.
   */
  @inline def getDouble(groupName: CString, key: CString)(implicit error: Out[GError]): gdouble = extern

   /**
   * Returns the values associated with `key` under `groupName`, and stores the list size in `length`.
   * The returned array is null-terminated, so `length` may optionally be null.
   *
   * @param groupName a group name
   * @param key key
   * @param length return location for the number of returned values, or null
   * @param error return location for a GError, or null
   * @return a newly allocated, null-terminated array of string. Use [[GLib.strfreev()]] to free it.
   */
  @inline def getStringList(groupName: CString, key: CString)(implicit length: Out[gsize], error: Out[GError]): Ptr[CString] = extern

  /**
   * Returns a Seq with all values for `key` under `groupName`.
   *
   * @note You must either call `free()` on the returned Seq manually, or use an [[AutoreleasePool]]!
   *
   * @param groupName group for which all keys should be returned
   * @param pool GAutoreleasePool (if =null, you must free the Seq manually!)
   * @return Wrapper around array returned by [[getStringList()]]
   */
  def stringList(groupName: CString, key: CString)(implicit pool: AutoreleasePool = null): GSeq[CString] = Zone{ implicit z: Zone =>
    val size = Out.alloc[gsize]
    val array = getStringList(groupName,key)(size,null)
    val seq = Wrappers.NullTerminatedStringArray(array,size.value.get.toInt)
    if(pool!=null)
      pool.register(seq)
    seq
  }

  /**
   * Associates a new value with `key` under `groupName`.
   * If `key` cannot be found then it is created. If `groupName` cannot be found then it is created.
   *
   * @param groupName
   * @param key
   * @param value
   */
  @inline def setValue(groupName: CString, key: CString, value: CString): Unit = extern

}

object GKeyFile {

  def apply(implicit pool: AutoreleasePool = null): GKeyFile = {
    val obj = new GKeyFile
    if(pool!=null)
      pool.register(obj)
    obj
  }

  type GKeyFileError = Int
  val G_KEY_FILE_ERROR_UNKNOWN_ENCODING: GKeyFileError = 0
  val G_KEY_FILE_ERROR_PARSE: GKeyFileError = 1
  val G_KEY_FILE_ERROR_NOT_FOUND: GKeyFileError = 2
  val G_KEY_FILE_ERROR_KEY_NOT_FOUND: GKeyFileError = 3
  val G_KEY_FILE_ERROR_GROUP_NOT_FOUND: GKeyFileError = 4
  val G_KEY_FILE_ERROR_INVALID_VALUE: GKeyFileError = 5

  type GKeyFileFlags = Int
  val G_KEY_FILE_NONE: GKeyFileFlags = 0
  val G_KEY_FILE_KEEP_COMMENTS: GKeyFileFlags = 1 << 0
  val G_KEY_FILE_KEEP_TRANSLATIONS: GKeyFileFlags = 1 << 1
}
