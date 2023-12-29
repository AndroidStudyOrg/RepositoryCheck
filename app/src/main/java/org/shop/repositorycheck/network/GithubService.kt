package org.shop.repositorycheck.network

import org.shop.repositorycheck.model.Repo
import org.shop.repositorycheck.model.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {
    @GET("/users/{username}/repos")
    fun listRepos(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Query("page") page: Int
    ): Call<List<Repo>>

    @GET("/search/users")
    fun searchUsers(@Query("q") query: String): Call<UserDto>
}