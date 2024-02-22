package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
}

class PostRepositoryInMemoryImpl : PostRepository {
    public var post = Post(
        id = 1,
        author = "ГПБОУ ВО БТПИТ",
        content = "@string/Post1",
        published = "21 февраля в 19:12",
        likedByMe = false
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post
    }
}