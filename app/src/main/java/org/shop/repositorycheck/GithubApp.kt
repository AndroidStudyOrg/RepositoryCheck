package org.shop.repositorycheck

import android.app.Application

class GithubApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // 앱이 시작될 때 APIClient에 컨텍스트를 설정
        APIClient.setContext(applicationContext)
    }
}