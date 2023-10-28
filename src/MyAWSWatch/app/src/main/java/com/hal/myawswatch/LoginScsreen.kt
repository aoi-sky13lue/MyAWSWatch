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
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.hal.myawswatch.ui.theme.AWSOrangeColor
import com.hal.myawswatch.ui.theme.ForLine
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
        Divider(color = Color.Gray, thickness = 1.5.dp, modifier = Modifier.padding(vertical = 32.dp))
        Contents()
    }
}

/**
 * プレビュー(Light Theme)処理
 * */
@Preview(name = "Light Theme", showBackground = true)
@Composable
fun LightThemePreview() {
    MyAWSWatchTheme(darkTheme = false) {
        Drawer()
    }
}

@Preview(name = "Dark Theme", showBackground = true)
@Composable
fun DarkThemePreview(){
    MyAWSWatchTheme(darkTheme = true) {
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

/**
 * 本画面のコンテンツ部分の描画処理
 * */
@Composable
fun Contents(){
    Spacer(modifier = Modifier.height(32.dp))
    UserFieldArea()
    Spacer(modifier = Modifier.height(36.dp))
    UserOptionArea()
    Spacer(modifier = Modifier.height(36.dp))
    LoginArea()
}

/**
 * ユーザー情報入力部の描画処理
 * */
@Composable
fun UserFieldArea(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserInputField(R.drawable.ic_login_screen_email, placeholderText = "Enter your email")
        Spacer(modifier = Modifier.height(24.dp))
        UserInputField(R.drawable.ic_login_screen_password, placeholderText = "Enter your password")
    }
}

/**
 * アイコン及びテキストフィールドの描画処理
 * TODO: この関数は後々共通モジュールに移動する
 *
 * @param iconId アイコンのリソースID
 * @param placeholderText テキストフィールドのプレースホルダー用文字列
 * */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInputField(iconId: Int, placeholderText: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center, // この行を追加
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = iconId),
            contentDescription = null,
            modifier = Modifier.size(36.dp))
        Spacer(modifier = Modifier.width(16.dp))
        OutlinedTextField(value = "",
            onValueChange = {},
            placeholder = { Text(placeholderText) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                containerColor = ForLine,
            ))
    }
}

/**
 * ユーザーが任意で選択できるオプションエリアの描画処理
 * */
@Composable
fun UserOptionArea(){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center, // この行を追加
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(checked = false,
                onCheckedChange = {},
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Remember Login Info")
        }
    }
}

/**
 * ログインボタン部の描画処理
 * */
@Composable
fun LoginArea(){
    Column(Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = { /*TODO*/ },
            Modifier.width(325.dp),) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Forget Login Info?")
    }
}