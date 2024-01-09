package com.hal.cloudseeker.repository.login

import com.hal.cloudseeker.arch.login.LoginArch
import javax.inject.Inject

/**
 * ログイン画面のリポジトリクラス
 *
 * @param loginRDS ログイン画面のリモートデータソースクラス
 * @param loginLDS ログイン画面のローカルデータソースクラス
 * */
class LoginRepository @Inject constructor(
    private val loginRDS: LoginRDS,
    private val loginLDS: LoginLDS
) {

    /**
     * ログイン情報を設定する
     *
     * @param email メールアドレス
     * @param password パスワード
     * */
    fun setLoginCredentials(email: String, password: String) {
        loginLDS.setLoginCredentials(email, password)
    }

    /**
     * ログイン情報を取得する
     *
     * @return ログイン情報
     * */
    fun getLoginCredentials(): LoginArch.LoginCredential {
        return loginLDS.getLoginCredentials()
    }

    /**
     * ログイン情報を保存するかどうかを設定する
     *
     * @param isSavingCredential ログイン情報を保存するかどうか
     * */
    fun setIsSavingLoginCredentials(isSavingCredential: Boolean){
        loginLDS.setIsSavingCredential(isSavingCredential)
    }

    /**
     * ログイン情報を保存するかどうかを取得する
     *
     * @return ログイン情報を保存するかどうか
     * */
    fun getIsSavingLoginCredentials(): Boolean {
        return loginLDS.getIsSavingCredential()
    }

    /**
     * GCPへログインする
     *
     * @return ログイン結果(成功:true/失敗:false)
     * */
    fun loginToGCP(): Boolean{
        val loginCredential = getLoginCredentials()
        val email = loginCredential.email
        val password = loginCredential.password

        return loginRDS.loginToGCPByOAuth(email, password)
    }

    /**
     * ログイン情報を保存する
     *
     * @return 保存結果(成功:true/失敗:false)
     * */
    fun saveLoginCredential() : Boolean{
        return loginLDS.saveLoginCredential()
    }
}