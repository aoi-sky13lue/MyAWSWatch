package com.hal.myawswatch.viewmodel.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ログイン画面のViewModelクラス
 * */
class LoginViewModel: ViewModel() {
    private val _email = MutableStateFlow("")
    private val _password = MutableStateFlow("")

    /**
     * ログイン用メールアドレス
     * */
    val email: StateFlow<String> = _email.asStateFlow()

    /**
     * ログイン用パスワード
     * */
    val password: StateFlow<String> = _password.asStateFlow()

    /**
     * メールアドレスを更新する
     *
     * @param newEmail 新しいメールアドレス
     * */
    fun setEmail(newEmail: String?){
        _email.value = newEmail ?: ""
    }

    /**
     * パスワードを更新する
     *
     * @param newPassword 新しいパスワード
     * */
    fun setPassword(newPassword: String?) {
        _password.value = newPassword ?: ""
    }
}