package com.sirekanyan.devtools.exceptions

import kotlin.reflect.KProperty

class UnknownPropertyTypeError(property: KProperty<*>) :
    Error("Not implemented property type ${property.returnType}")