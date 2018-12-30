// Copyright (c) 2018. Distributed under the MIT License (see included LICENSE file).
package glib

package object json {
  type JsonNodeType = Int
  object JsonNodeType {
    val OBJECT :JsonNodeType = 0
    val ARRAY  :JsonNodeType = 1
    val VALUE  :JsonNodeType = 2
    val NULL   :JsonNodeType = 3
  }
}
