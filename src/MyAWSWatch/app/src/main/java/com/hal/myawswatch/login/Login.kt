package com.hal.myawswatch.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.hal.myawswatch.login.ui.theme.MyAWSWatchTheme
import com.hal.myawswatch.utils.SysUiUtils

/**
 * LoginActivityクラス
 * */
class LoginActivity : ComponentActivity() {
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
        //スプラッシュ画面にてシステムバーを非表示にしたため戻す
        //スプラッシュ画面側で表示を戻すと、一瞬描画が乱れるためこちらで行う
        SysUiUtils().showSystemBars(window)
        setContent {
            MyAWSWatchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Draw()
                }
            }
        }
    }
}

/**
 * 本画面全体の描画処理
 * */
@Composable
fun Draw() {
    Column(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)){
        Header()
        Divider(color = MaterialTheme.colorScheme.onSurface, thickness = 1.5.dp)
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Contents()
        }
    }
}

/**
 * プレビュー(Light Theme)処理
 * */
@Preview(name = "Light Theme", showBackground = true)
@Composable
fun LightThemePreview() {
    MyAWSWatchTheme(darkTheme = false) {
        Draw()
    }
}

/**
 * プレビュー(Dark Theme)処理
 * */
@Preview(name = "Dark Theme", showBackground = false)
@Composable
fun DarkThemePreview(){
    MyAWSWatchTheme(darkTheme = true) {
        Draw()
    }
}

/**
 * 本画面のヘッダ部分の描画処理
 * */
@Composable
fun Header(){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth().padding(vertical = 48.dp)
    ){
        Text(text = "Sign in to your account",
            style = TextStyle(fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary))
    }
}

/**
 * 本画面のコンテンツ部分の描画処理
 * */
@Composable
fun Contents(){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f)
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(32.dp))
            UserFieldArea()
            Spacer(modifier = Modifier.height(36.dp))
            UserOptionArea()
            Spacer(modifier = Modifier.height(36.dp))
            LoginArea()
        }
    }
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
        UserInputField(placeholderText = "Enter your email")
        Spacer(modifier = Modifier.height(24.dp))
        UserInputField(placeholderText = "Enter your password")
    }
}

/**
 * アイコン及びテキストフィールドの描画処理
 * TODO: この関数は後々共通モジュールに移動する
 *
 * @param iconId アイコンのリソースID
 * @param placeholderText テキストフィールドのプレースホルダー用文字列
 * */
@Composable
fun UserInputField(placeholderText: String){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(value = "",
            onValueChange = {},
            Modifier.fillMaxWidth(0.95f),
            placeholder = { Text(placeholderText) },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.tertiaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                focusedBorderColor = MaterialTheme.colorScheme.onTertiaryContainer,
                unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            )
        )
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
            Text(text = "Save Your Account", color = MaterialTheme.colorScheme.primary)
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
        ElevatedButton(
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 16.dp,
                pressedElevation = 32.dp,
                disabledElevation = 0.dp
            ),
            modifier = Modifier.fillMaxWidth(0.9f).fillMaxHeight(0.5f),
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),) {
            Text(text = "Login")
        }
    }
}