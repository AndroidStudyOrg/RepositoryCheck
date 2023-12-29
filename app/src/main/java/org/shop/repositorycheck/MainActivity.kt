package org.shop.repositorycheck

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.shop.repositorycheck.databinding.ActivityMainBinding
import org.shop.repositorycheck.model.Repo
import org.shop.repositorycheck.model.UserDto
import org.shop.repositorycheck.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val githubService = retrofit.create(GithubService::class.java)
        githubService.listRepos(resources.getString(R.string.github_token), "square")
            .enqueue(object : Callback<List<Repo>> {
                override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                    Log.e("MainActivity", "List Repo: ${response.body().toString()}")
                }

                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

                }
            })

        githubService.searchUsers("squar").enqueue(object : Callback<UserDto> {
            override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                Log.e("MainActivity", "Search User: ${response.body().toString()}")
            }

            override fun onFailure(call: Call<UserDto>, t: Throwable) {

            }
        })
    }
}