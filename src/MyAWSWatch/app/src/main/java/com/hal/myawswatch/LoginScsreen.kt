package com.hal.myawswatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.hal.myawswatch.ui.theme.AWSOrangeColor
import com.hal.myawswatch.ui.theme.MyAWSWatchTheme

/**
 * MainActivityクラス
 * */
class MainActivity : ComponentActivity() {
    /**
     * アクティビティのライフサイクル内で初めて呼び出されるメソッド。
     *
     * このメソッドでは、[MyAWSWatchTheme] を使用してUIのテーマを適用します。
     * また、[Surface] コンテナを使用してテーマからの背景色を適用し、[Drawer] 関数を呼び出してUIの内容をレンダリングします。
     *
     * @param savedInstanceState アクティビティの前のインスタンスの状態情報。初回起動時はnull。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyAWSWatchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Drawer()
                }
            }
        }
    }
}

/**
 * 本画面全体の描画処理
 * */
@Composable
fun Drawer() {
    Column(modifier = Modifier.fillMaxSize()){
        Header()
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
    }
}

/**
 * プレビュー処理
 * */
@Preview()
@Composable
fun GreetingPreview() {
    MyAWSWatchTheme {
        Drawer()
    }
}

/**
 * 本画面のヘッダ部分の描画処理
 * */
@Composable
fun Header(){
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painter = painterResource(id = R.drawable.ic_login_screen_user), contentDescription = null, modifier = Modifier.size(64.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "AWS", style = TextStyle(fontSize = 24.sp, color = AWSOrangeColor))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "User", style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Login", style = TextStyle(fontSize = 24.sp))
        }
    }
}