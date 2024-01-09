package com.hal.cloudseeker.repository.login

import javax.inject.Inject

/**
 * ログイン画面のリモートデータソースクラス
 * */
class LoginRDS @Inject constructor(){
    /**
     * GCPへOAuth認証でログインする
     *
     * @param email メールアドレス
     * @param password パスワード
     * @return ログイン結果(成功:true/失敗:false)
     * */
    fun loginToGCPByOAuth(email: String, password: String): Boolean {
        return true
    }
}