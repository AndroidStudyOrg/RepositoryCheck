package org.shop.repositorycheck

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.shop.repositorycheck.databinding.ActivityRepoBinding

class RepoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRepoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }


    }
}