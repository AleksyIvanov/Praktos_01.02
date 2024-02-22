package com.example.myapplication

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //  @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var tekstik: TextView = findViewById(R.id.LikesCount)
        var a = tekstik.text.toString().toInt()
        var count = 0

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                TxtName.text = post.author
                TxtStatus.text = post.published
                TxtPost.text = post.content
                imgLikes.setOnClickListener {
                    viewModel.like()
                    count++
                    tekstik.text = toStringNumb(a)
                }
                imgLikes.setImageResource(
                    if (post.likedByMe){
                        R.drawable.icons8_heart_24_rounded

                    } else {
                        R.drawable.icons8_heart_24_outline
                    }
                )

            }
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
            tekstikRep.text = toStringNumb(b)
        }
    }

    fun toStringNumb(count: Int): String
    {
        return when(count)
        {
            in 0..999 -> {
                count.toString()
            }
            in 1000..<1_000_000 -> {
                ((count/100).toFloat()/10).toString() + "k"
            }
            in 1_000_000..<1_000_000_000 -> {
                ((count/100000).toFloat()/10).toString() + "M"
            }
            else -> "более МЛРД"
        }
    }
}
class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.get()
    fun like() = repository.like()
}




