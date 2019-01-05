import scala.scalanative.native.{CFunctionPtr3, Ptr}

package object libsoup {

  type SoupMemoryUse = Int
  object SoupMemoryUse {
    val STATIC    :SoupMemoryUse = 0
    val TAKE      :SoupMemoryUse = 1
    val COPY      :SoupMemoryUse = 2
    val TEMPORARY :SoupMemoryUse = 3
  }

  type SoupHTTPVersion = Int
  object SoupHTTPVersion {
    val HTTP_1_0 :SoupHTTPVersion = 0
    val HTTP_1_1 :SoupHTTPVersion = 1
  }

  type SoupEncoding = Int
  object SoupEncoding {
    val UNRECOGNIZED   :SoupEncoding = 0
    val NONE           :SoupEncoding = 1
    val CONTENT_LENGTH :SoupEncoding = 2
    val EOF            :SoupEncoding = 3
    val CHUNKED        :SoupEncoding = 4
    val BYTERANGES     :SoupEncoding = 5
  }

  type SoupLoggerLogLevel = Int
  object SoupLoggerLogLevel {
    val NONE    :SoupLoggerLogLevel = 0
    val MINIMAL :SoupLoggerLogLevel = 1
    val HEADERS :SoupLoggerLogLevel = 2
    val BODY    :SoupLoggerLogLevel = 3
  }

}
