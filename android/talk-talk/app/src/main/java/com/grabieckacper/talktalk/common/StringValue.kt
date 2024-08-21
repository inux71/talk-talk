package com.grabieckacper.talktalk.common

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class StringValue {
    data class DynamicString(val value: String) : StringValue()

    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : StringValue()

    data object Empty : StringValue()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
            is Empty -> ""
        }
    }
}
