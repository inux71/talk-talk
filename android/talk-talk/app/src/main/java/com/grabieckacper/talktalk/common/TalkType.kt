package com.grabieckacper.talktalk.common

import com.grabieckacper.talktalk.R

enum class TalkType(val stringResource: StringValue.StringResource) {
    ALL(stringResource = StringValue.StringResource(resId = R.string.talk_type_all)),
    UNREAD(stringResource = StringValue.StringResource(resId = R.string.talk_type_unread)),
    GROUP(stringResource = StringValue.StringResource(resId = R.string.talk_type_group))
}
