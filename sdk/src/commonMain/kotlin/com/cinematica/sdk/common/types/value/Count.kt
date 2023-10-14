package com.cinematica.sdk.common.types.value

//@JvmInline
//public value class Count private constructor(public val int: Int) {
//    public companion object : Factory<Count, Int>() {
//        public const val MIN_VALUE: Int = 0
//
//        override fun create(input: Int): Result<Count> {
//            return when {
//                input < MIN_VALUE -> Result.failure(CreationFailure.ofMin(MIN_VALUE))
//                else -> Result.success(Count(input))
//            }
//        }
//    }
//}