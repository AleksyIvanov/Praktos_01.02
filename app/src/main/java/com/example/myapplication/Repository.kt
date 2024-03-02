package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.Date

interface PostRepository {
    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Int)
    fun repById(Id: Int)
    fun removeById (Id: Int)
    fun save(post: Post)
}

class PostRepositoryInMemoryImpl : PostRepository {
    private var posts = listOf(
        Post(
            id = 2,
            author = "БТПИТ",
            content = "И такое бывает",
            published = "23 февраля в 23:23",
            likedByMe = false,
            likeCount = 111,
            repByMe = false,
            repCount = 11,
            viewCount = 777
        ),
        Post(
            id = 1,
            author = "БТПИТ",
            content = "Природа",
            published = "11 февраля в 20:23",
            likedByMe = false,
            likeCount = 260,
            repByMe = false,
            repCount = 33,
            viewCount = 277
        ),
    )


    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else it.copy(likedByMe = !it.likedByMe)
        }
        data.value = posts
    }

    fun nextId(post: List<Post>): Int{
        var id = 1
        posts.forEach{it1 ->
            post.forEach{
                if(it.id == id) id = (it.id+1)
            }
        }
        return id
    }
    override fun repById(id: Int) {
        posts = posts.map {
            if (it.id != id) it else it.copy(repByMe = !it.repByMe)
        }
        data.value = posts
    }

    override fun removeById(id: Int) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0) {
            posts = listOf(post.copy(
                id = nextId(posts),
                author = " БТПИТ",
                likedByMe = false,
                published = "Секунду назад"
            ))+posts
            data.value = posts
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }
}