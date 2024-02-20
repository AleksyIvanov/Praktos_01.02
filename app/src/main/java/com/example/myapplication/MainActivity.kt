package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var Likes: ImageButton = findViewById(R.id.imageButton)
        var tekstik: TextView = findViewById(R.id.LikesCount)

        var a = tekstik.text.toString().toInt()
        var count = 0
        Likes.setOnClickListener {
            count++
            if (count === 1)
                a++
            else {
                count = 0
                a--
            }

            tekstik.text = toStringNumb(a)
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