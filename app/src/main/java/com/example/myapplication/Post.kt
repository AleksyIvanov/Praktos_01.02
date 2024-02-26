package com.example.myapplication
data class Post(
    val id: Int,
    val author: String,
    val content: String,
    val published: String,
    val likedByMe: Boolean,
    var likeCount: Int,
    var repByMe: Boolean,
    var repCount: Int,
    var viewCount: Int
)