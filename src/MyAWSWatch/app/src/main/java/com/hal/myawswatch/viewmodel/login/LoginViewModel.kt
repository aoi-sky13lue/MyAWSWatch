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
        newEmail.let {
            if(it == null)
                return@let
            _email.value = it
            loginRepository.setLoginCredentials(it, _password.value)
        }
    }

    /**
     * パスワードを更新する
     *
     * @param newPassword 新しいパスワード
     * */
    fun setPassword(newPassword: String?) {
        newPassword.let {
            if(it == null)
                return@let
            _password.value = it
            loginRepository.setLoginCredentials(_email.value, it)
        }
    }
}