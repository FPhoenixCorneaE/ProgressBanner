package com.fphoenixcorneae.bannerlayout.annotation

import androidx.annotation.IntDef

@IntDef(
        ProgressShapeMode.RECTANGLE,
        ProgressShapeMode.RING
)
@kotlin.annotation.Retention(AnnotationRetention.BINARY)
annotation class ProgressShapeMode {
    companion object {
        const val RECTANGLE = 0
        const val RING = 1
    }
}