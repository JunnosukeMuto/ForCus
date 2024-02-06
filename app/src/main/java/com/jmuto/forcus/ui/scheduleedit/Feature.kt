package com.jmuto.forcus.ui.scheduleedit

import androidx.annotation.StringRes
import com.jmuto.forcus.R

enum class Feature(@StringRes val nameId: Int, @StringRes val descriptionId: Int) {
    APP_BLOCK(R.string.app_block, R.string.app_block_desc),
    DETOX_TIME(R.string.detox_time, R.string.detox_time_desc),
}