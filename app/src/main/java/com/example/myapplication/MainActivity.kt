package com.example.myapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //  @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter {
            viewModel.like(it.id)
        }
        binding.list.adapter = adapter
        viewModel.data.observe(this) {posts ->
            adapter.submitList(posts)
        }
    }

    fun toStringNumb(count: Int): String
    {
        return when(count){
            in 0..<1_000 -> count.toString()
            in 1000..<1_100-> "1K"
            in 1_100..<10_000 -> ((count/100).toFloat()/10).toString() + "K"
            in 10_000..<1_000_000 -> (count/1_000).toString() + "K"
            in 1_000_000..<1_100_000 -> "1M"
            in 1_100_000..<10_000_000 -> ((count/100_000).toFloat()/10).toString() + "M"
            in 10_000_000..<1_000_000_000 -> (count/1_000_000).toString() + "M"
            else -> "more bilion"
        }
    }
}





