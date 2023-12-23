package com.hal.myawswatch.viewmodel.login

import androidx.lifecycle.ViewModel
import com.hal.myawswatch.repository.login.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * ログイン画面のViewModelクラス
 *
 * @param loginRepository ログイン画面のリポジトリクラス
 * */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _isSavingCredential = MutableStateFlow(false)

    /**
     * ログイン用メールアドレス
     * */
    val email: StateFlow<String> = _email.asStateFlow()

    /**
     * ログイン用パスワード
     * */
    val password: StateFlow<String> = _password.asStateFlow()

    /**
     * ログイン情報を保存するかどうか
     * */
    val isSavingCredential: StateFlow<Boolean> = _isSavingCredential.asStateFlow()

    /**
     * メールアドレスを更新する
     *
     * @param newEmail 新しいメールアドレス
     * */
    fun setEmail(newEmail: String){
        _email.value = newEmail
        loginRepository.setLoginCredentials(newEmail, _password.value)
    }

    /**
     * パスワードを更新する
     *
     * @param newPassword 新しいパスワード
     * */
    fun setPassword(newPassword: String) {
        _password.value = newPassword
        loginRepository.setLoginCredentials(_email.value, newPassword)
    }

    /**
     * ログイン情報を保存するかどうかを更新する
     *
     * @param isSavingCredential ログイン情報を保存するかどうか
     * */
    fun setIsSavingCredential(isSavingCredential: Boolean) {
        _isSavingCredential.value = isSavingCredential
        loginRepository.setIsSavingLoginCredentials(isSavingCredential)
    }

    /**
     * ログインボタン押下時の処理
     * */
    fun onLoginButtonClicked() {
        val isSavingCredential = loginRepository.getIsSavingLoginCredentials()
        val loginResult = loginRepository.loginToGCP()
        if(loginResult) {
            // ログイン成功時の処理
        } else {
            // ログイン失敗時の処理
        }

        if(isSavingCredential){
            // ログイン情報を保存する場合の処理
            val saveResult = loginRepository.saveLoginCredential()
        }
    }
}