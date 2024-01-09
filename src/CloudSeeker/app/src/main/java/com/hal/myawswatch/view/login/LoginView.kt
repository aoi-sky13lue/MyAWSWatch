package com.hal.cloudseeker.view.login

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hal.cloudseeker.viewmodel.login.LoginViewModel
import com.hal.cloudseeker.view.login.ui.theme.CloudSeekerTheme
import com.hal.cloudseeker.utils.SysUiUtils
import dagger.hilt.android.AndroidEntryPoint

/**
 * LoginActivityクラス
 * */
@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    /**
     * アクティビティのライフサイクル内で初めて呼び出されるメソッド。
     *
     * このメソッドでは、[CloudSeekerTheme] を使用してUIのテーマを適用します。
     * また、[Surface] コンテナを使用してテーマからの背景色を適用し、[Draw] 関数を呼び出してUIの内容をレンダリングします。
     *
     * @param savedInstanceState アクティビティの前のインスタンスの状態情報。初回起動時はnull。
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //スプラッシュ画面にてシステムバーを非表示にしたため戻す
        //スプラッシュ画面側で表示を戻すと、一瞬描画が乱れるためこちらで行う
        SysUiUtils().showSystemBars(window)
        setContent {
            CloudSeekerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
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
    CloudSeekerTheme(darkTheme = false) {
        Draw()
    }
}

/**
 * プレビュー(Dark Theme)処理
 * */
@Preview(name = "Dark Theme", showBackground = false)
@Composable
fun DarkThemePreview(){
    CloudSeekerTheme(darkTheme = true) {
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 48.dp),
    ){
        Text(text = "Sign in to your account",
            style = TextStyle(fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                shadow = Shadow(
                    color = MaterialTheme.colorScheme.onBackground,
                    offset = Offset(4f, 4f),
                    blurRadius = 8f
                )
            ))
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
 *
 * @param vm ログイン画面のViewModel
 * */
@Composable
fun UserFieldArea(vm: LoginViewModel = viewModel()){
    val email by vm.email.collectAsState()
    val password by vm.password.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserInputField(placeholderText = "Email",
            changeAction = {vm.setEmail(it)},
            value = email)
        Spacer(modifier = Modifier.height(24.dp))
        UserInputField(placeholderText = "Password",
            changeAction = {vm.setPassword(it)},
            value = password)
    }
}

/**
 * アイコン及びテキストフィールドの描画処理
 * TODO: この関数は後々共通モジュールに移動する
 *
 * @param placeholderText テキストフィールドのプレースホルダー用文字列
 * @param changeAction テキストフィールドの値変更時に呼び出される処理
 * @param value テキストフィールドの値
 * */
@Composable
fun UserInputField(placeholderText: String,
                   changeAction: (String) -> Unit,
                   value: String){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(value = value,
            onValueChange = {changeAction(it)},
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
fun UserOptionArea(vm: LoginViewModel = viewModel()){
    val isSavingCredential by viewModel<LoginViewModel>().isSavingCredential.collectAsState()
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
            Checkbox(checked = isSavingCredential,
                onCheckedChange = {vm.setIsSavingCredential(it)},
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
fun LoginArea(vm: LoginViewModel = viewModel()){
    Column(Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        ElevatedButton(
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 16.dp,
                pressedElevation = 32.dp,
                disabledElevation = 0.dp
            ),
            modifier = Modifier.fillMaxWidth(0.9f),
            onClick = { vm.onLoginButtonClicked() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ),) {
            Text(text = "Login")
        }
    }
}