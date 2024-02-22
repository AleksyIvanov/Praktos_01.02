package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //  @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var tekstik: TextView = findViewById(R.id.LikesCount)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->

            with(binding) {
                TxtName.text = post.author
                TxtStatus.text = post.published
                TxtPost.text = post.content
                imgLikes.setImageResource(
                    if (post.likedByMe)
                        R.drawable.icons8_heart_24_rounded
                    else
                        R.drawable.icons8_heart_24_outline
                )
                LikesCount.text=toStringNumb(post.likeCount)

            }
        }
        binding.imgLikes.setOnClickListener{
            viewModel.like()
        }
        var Rep: ImageButton = findViewById(R.id.imageButton2)
        var tekstikRep: TextView = findViewById(R.id.RepCount)
        var b = tekstikRep.text.toString().toInt()
        var CountRep = 0
        Rep.setOnClickListener {
            CountRep++
            if (CountRep === 1)
                b++
            else {
                CountRep = 0
                b--
            }
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




