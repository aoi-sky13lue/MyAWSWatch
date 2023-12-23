package com.hal.myawswatch.repository.login

import com.hal.myawswatch.arch.login.LoginArch
import javax.inject.Inject

/**
 * ログイン画面のローカルデータソースクラス
 * */
class LoginLDS @Inject constructor(){
    private lateinit var loginCredential : LoginArch.LoginCredential
    private var isSavingCredential : Boolean = false

    /**
     * ログイン情報を設定する
     *
     * @param email メールアドレス
     * @param password パスワード
     * */
    fun setLoginCredentials(email: String, password: String) {
        loginCredential = LoginArch.LoginCredential(email, password)
    }

    /**
     * ログイン情報を取得する
     *
     * @return ログイン情報
     * */
    fun getLoginCredentials(): LoginArch.LoginCredential {
        return loginCredential
    }

    /**
     * ログイン情報を保存するかどうかを設定する
     *
     * @param isSavingCredential ログイン情報を保存するかどうか
     * */
    fun setIsSavingCredential(isSavingCredential: Boolean) {
        this.isSavingCredential = isSavingCredential
    }

    /**
     * ログイン情報を保存するかどうかを取得する
     *
     * @return ログイン情報を保存するかどうか
     * */
    fun getIsSavingCredential(): Boolean {
        return isSavingCredential
    }
}