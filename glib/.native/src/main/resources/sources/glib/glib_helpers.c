#include <glib-object.h>

unsigned long snhelper_gtype_none() { return G_TYPE_NONE; }
unsigned long snhelper_gtype_interface() { return G_TYPE_INTERFACE; }
unsigned long snhelper_gtype_char() { return G_TYPE_CHAR; }
unsigned long snhelper_gtype_uchar() { return G_TYPE_UCHAR; }
unsigned long snhelper_gtype_boolean() { return G_TYPE_BOOLEAN; }
unsigned long snhelper_gtype_int() { return G_TYPE_INT; }
unsigned long snhelper_gtype_uint() { return G_TYPE_UINT; }
unsigned long snhelper_gtype_long() { return G_TYPE_LONG; }
unsigned long snhelper_gtype_ulong() { return G_TYPE_ULONG; }
unsigned long snhelper_gtype_enum() { return G_TYPE_ENUM; }
unsigned long snhelper_gtype_int64() { return G_TYPE_INT64; }
unsigned long snhelper_gtype_uint64() { return G_TYPE_UINT64; }
unsigned long snhelper_gtype_float() { return G_TYPE_FLOAT; }
unsigned long snhelper_gtype_double() { return G_TYPE_FLOAT; }
unsigned long snhelper_gtype_string() { return G_TYPE_STRING; }
unsigned long snhelper_gtype_pointer() { return G_TYPE_POINTER; }

size_t snhelper_gvalue_getsize() { return sizeof(GValue); }

GMutex* snhelper_g_mutex_new() {
  GMutex* m = g_new(GMutex,1);
  g_mutex_init(m);
  return m;
}
void snhelper_g_mutex_free(GMutex* m) {
  g_mutex_clear(m);
  g_free(m);
}

GRecMutex* snhelper_g_rec_mutex_new() {
  GRecMutex* m = g_new(GRecMutex,1);
  g_rec_mutex_init(m);
  return m;
}
void snhelper_g_rec_mutex_free(GRecMutex* m) {
  g_rec_mutex_clear(m);
  g_free(m);
}
