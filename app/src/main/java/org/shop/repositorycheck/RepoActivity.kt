package org.shop.repositorycheck

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.shop.repositorycheck.adapter.RepoAdapter
import org.shop.repositorycheck.databinding.ActivityRepoBinding
import org.shop.repositorycheck.model.Repo
import org.shop.repositorycheck.network.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding
    private lateinit var repoAdapter: RepoAdapter

    private var page = 0
    private var hasMore = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        val username = intent.getStringExtra("username") ?: return
        binding.usernameTextView.text = username

        repoAdapter = RepoAdapter {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it.htmlUrl))
            startActivity(intent)
        }

        val linearLayoutManager = LinearLayoutManager(this@RepoActivity)
        binding.repoRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = repoAdapter
        }

        // Paging 처리
        binding.repoRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            /**
             *  override 하게 되면 내가 짠 코드가 먼저 실행되고 super 메서드가 실행될 때 미리 정의된 빈 함수가 실행
             */
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // LayoutManager가 받아온 아이템 갯수
                val totalCount = linearLayoutManager.itemCount      // 1부터 시작
                // lastVisiblePosition 이 totalCount 보다 크거나 같으면 마지막 아이템을 보고 있다고 판단 가능
                val lastVisiblePosition =
                    linearLayoutManager.findLastCompletelyVisibleItemPosition()       // 0부터 시작

                if (lastVisiblePosition >= (totalCount - 1) && hasMore) {
                    page += 1
                    listRepo(username, page)
                }
            }
        })

        listRepo(username, page)
    }

    private fun listRepo(username: String, page: Int) {
        val githubService = APIClient.retrofit.create(GithubService::class.java)
        githubService.listRepos(username, page)
            .enqueue(object : Callback<List<Repo>> {
                override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                    Log.e("MainActivity", "List Repo: ${response.body().toString()}")
                    hasMore = response.body()?.count() == 30
                    repoAdapter.submitList(repoAdapter.currentList + response.body().orEmpty())
                }

                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {

                }
            })
    }
}