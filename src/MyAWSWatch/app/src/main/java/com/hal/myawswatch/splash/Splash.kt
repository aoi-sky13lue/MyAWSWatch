package com.hal.myawswatch.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.hal.myawswatch.R
import com.hal.myawswatch.splash.ui.theme.MyAWSWatchTheme
import com.hal.myawswatch.utils.SysUiUtils

/**
 * Splashクラス
 * */
class Splash : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SysUiUtils().hideSystemBars(window)
        setContent {
            MyAWSWatchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DrawSplash()
                }
            }
        }
    }
}

/**
 * プレビュー(Light Theme)処理
 *
 * @author aoi
 * */
@Preview(name = "Light Theme", showBackground = true)
@Composable
fun LightThemePreview() {
    MyAWSWatchTheme {
        DrawSplash()
    }
}

/**
 * プレビュー(Dark Theme)処理
 *
 * @author aoi
 * */
@Preview(name = "Dark Theme", showBackground = false)
@Composable
fun DarkThemePreview() {
    MyAWSWatchTheme {
        DrawSplash()
    }
}

/**
 * Lottieを使用したスプラッシュスクリーン表示用のコンポーザブル関数。
 * アニメーションは無限にループします。
 * スプラッシュ完了後の処理は呼び出し元が担当します。
 *
 * @author aoi
 */
@Composable
fun DrawSplash() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.app_launch_splash))
    val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)

    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}
