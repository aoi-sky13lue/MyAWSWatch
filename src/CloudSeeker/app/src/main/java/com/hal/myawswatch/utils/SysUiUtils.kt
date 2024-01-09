package com.hal.cloudseeker.utils

import android.view.Window
import android.view.WindowInsets

/**
 * システムUIユーティリティクラス
 * */
class SysUiUtils {
    /**
     * システムバーを非表示にする
     *
     * @param window Window
     * @author aoi
     * */
    fun hideSystemBars(window: Window) {
        window.decorView.windowInsetsController?.apply {
            hide(WindowInsets.Type.systemBars())
        }
    }

    /**
     * システムバーを表示する
     *
     * @param window Window
     * @author aoi
     * */
    fun showSystemBars(window: Window) {
        window.decorView.windowInsetsController?.apply {
            show(WindowInsets.Type.systemBars())
        }
    }
}