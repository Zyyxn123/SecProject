#ifndef KONAN_LIBSHARED_H
#define KONAN_LIBSHARED_H
#ifdef __cplusplus
extern "C" {
#endif
#ifdef __cplusplus
typedef bool            libshared_KBoolean;
#else
typedef _Bool           libshared_KBoolean;
#endif
typedef unsigned short     libshared_KChar;
typedef signed char        libshared_KByte;
typedef short              libshared_KShort;
typedef int                libshared_KInt;
typedef long long          libshared_KLong;
typedef unsigned char      libshared_KUByte;
typedef unsigned short     libshared_KUShort;
typedef unsigned int       libshared_KUInt;
typedef unsigned long long libshared_KULong;
typedef float              libshared_KFloat;
typedef double             libshared_KDouble;
typedef float __attribute__ ((__vector_size__ (16))) libshared_KVector128;
typedef void*              libshared_KNativePtr;
struct libshared_KType;
typedef struct libshared_KType libshared_KType;

typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_Byte;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_Short;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_Int;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_Long;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_Float;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_Double;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_Char;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_Boolean;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_Unit;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_UByte;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_UShort;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_UInt;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_ULong;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_com_tencent_kuikly_compose_ui_Modifier;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_Function1;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_kotlin_Function2;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_com_tencent_kuikly_core_pager_Pager;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_com_example_secproject_TouchType;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_com_example_secproject_TouchType_Down;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_com_example_secproject_TouchType_Move;
typedef struct {
  libshared_KNativePtr pinned;
} libshared_kref_com_example_secproject_TouchType_Up;


typedef struct {
  /* Service functions. */
  void (*DisposeStablePointer)(libshared_KNativePtr ptr);
  void (*DisposeString)(const char* string);
  libshared_KBoolean (*IsInstance)(libshared_KNativePtr ref, const libshared_KType* type);
  libshared_kref_kotlin_Byte (*createNullableByte)(libshared_KByte);
  libshared_KByte (*getNonNullValueOfByte)(libshared_kref_kotlin_Byte);
  libshared_kref_kotlin_Short (*createNullableShort)(libshared_KShort);
  libshared_KShort (*getNonNullValueOfShort)(libshared_kref_kotlin_Short);
  libshared_kref_kotlin_Int (*createNullableInt)(libshared_KInt);
  libshared_KInt (*getNonNullValueOfInt)(libshared_kref_kotlin_Int);
  libshared_kref_kotlin_Long (*createNullableLong)(libshared_KLong);
  libshared_KLong (*getNonNullValueOfLong)(libshared_kref_kotlin_Long);
  libshared_kref_kotlin_Float (*createNullableFloat)(libshared_KFloat);
  libshared_KFloat (*getNonNullValueOfFloat)(libshared_kref_kotlin_Float);
  libshared_kref_kotlin_Double (*createNullableDouble)(libshared_KDouble);
  libshared_KDouble (*getNonNullValueOfDouble)(libshared_kref_kotlin_Double);
  libshared_kref_kotlin_Char (*createNullableChar)(libshared_KChar);
  libshared_KChar (*getNonNullValueOfChar)(libshared_kref_kotlin_Char);
  libshared_kref_kotlin_Boolean (*createNullableBoolean)(libshared_KBoolean);
  libshared_KBoolean (*getNonNullValueOfBoolean)(libshared_kref_kotlin_Boolean);
  libshared_kref_kotlin_Unit (*createNullableUnit)(void);
  libshared_kref_kotlin_UByte (*createNullableUByte)(libshared_KUByte);
  libshared_KUByte (*getNonNullValueOfUByte)(libshared_kref_kotlin_UByte);
  libshared_kref_kotlin_UShort (*createNullableUShort)(libshared_KUShort);
  libshared_KUShort (*getNonNullValueOfUShort)(libshared_kref_kotlin_UShort);
  libshared_kref_kotlin_UInt (*createNullableUInt)(libshared_KUInt);
  libshared_KUInt (*getNonNullValueOfUInt)(libshared_kref_kotlin_UInt);
  libshared_kref_kotlin_ULong (*createNullableULong)(libshared_KULong);
  libshared_KULong (*getNonNullValueOfULong)(libshared_kref_kotlin_ULong);

  /* User functions. */
  struct {
    struct {
      struct {
        struct {
          struct {
            struct {
              struct {
                libshared_kref_com_example_secproject_TouchType (*get)(); /* enum entry for Down. */
              } Down;
              struct {
                libshared_kref_com_example_secproject_TouchType (*get)(); /* enum entry for Move. */
              } Move;
              struct {
                libshared_kref_com_example_secproject_TouchType (*get)(); /* enum entry for Up. */
              } Up;
              libshared_KType* (*_type)(void);
            } TouchType;
            struct {
              libshared_KInt (*com_example_secproject_base_BasePager$stableprop_getter)();
              libshared_KInt (*com_example_secproject_base_BridgeModule$stableprop_getter)();
              libshared_KInt (*com_example_secproject_base_Utils$stableprop_getter)();
              libshared_KInt (*com_example_secproject_base_BasePager$stableprop_getter_)();
              libshared_KInt (*com_example_secproject_base_BridgeModule$stableprop_getter_)();
              libshared_KInt (*com_example_secproject_base_Utils$stableprop_getter_)();
              libshared_KInt (*com_example_secproject_base_BasePager$stableprop_getter__)();
              libshared_KInt (*com_example_secproject_base_BridgeModule$stableprop_getter__)();
              libshared_KInt (*com_example_secproject_base_Utils$stableprop_getter__)();
              libshared_KInt (*com_example_secproject_base_BasePager$stableprop_getter___)();
              libshared_KInt (*com_example_secproject_base_BridgeModule$stableprop_getter___)();
              libshared_KInt (*com_example_secproject_base_Utils$stableprop_getter___)();
            } base;
            libshared_KInt (*com_example_secproject_Border$stableprop_getter)();
            libshared_KInt (*com_example_secproject_ComposeRoutePager$stableprop_getter)();
            libshared_KInt (*com_example_secproject_ImageAdapterBenchmarks$stableprop_getter)();
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*absoluteOffset)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_KFloat x, libshared_KFloat y);
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*appearPercentage)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_kref_kotlin_Function1 onPercentageChanged);
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*backgroundColor)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_KULong color);
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*borderRadius)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_KFloat radius);
            libshared_KULong (*changeAlpha)(libshared_KULong thiz, libshared_KFloat alpha);
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*height)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_KFloat height);
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*margin)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_KFloat start, libshared_KFloat top, libshared_KFloat end, libshared_KFloat bottom);
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*margin_)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_KFloat start, libshared_KFloat top, libshared_KFloat end, libshared_KFloat bottom);
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*offset)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_KFloat x, libshared_KFloat y);
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*offsetWithParentAdjustment)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_KFloat x, libshared_KFloat y);
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*padding)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_KFloat start, libshared_KFloat top, libshared_KFloat end, libshared_KFloat bottom);
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*touchListener)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_kref_kotlin_Function2 onTouchEvent);
            libshared_kref_com_tencent_kuikly_compose_ui_Modifier (*width)(libshared_kref_com_tencent_kuikly_compose_ui_Modifier thiz, libshared_KFloat width);
            libshared_KInt (*com_example_secproject_Border$stableprop_getter_)();
            libshared_KInt (*com_example_secproject_ComposeRoutePager$stableprop_getter_)();
            libshared_KInt (*com_example_secproject_ImageAdapterBenchmarks$stableprop_getter_)();
            libshared_KInt (*com_example_secproject_Border$stableprop_getter__)();
            libshared_KInt (*com_example_secproject_ComposeRoutePager$stableprop_getter__)();
            libshared_KInt (*com_example_secproject_ImageAdapterBenchmarks$stableprop_getter__)();
            void (*jumpPage)(libshared_kref_com_tencent_kuikly_core_pager_Pager pager, const char* inputText);
          } secproject;
        } example;
      } com;
      libshared_KInt (*initKuikly)();
    } root;
  } kotlin;
} libshared_ExportedSymbols;
extern libshared_ExportedSymbols* libshared_symbols(void);
#ifdef __cplusplus
}  /* extern "C" */
#endif
#endif  /* KONAN_LIBSHARED_H */
