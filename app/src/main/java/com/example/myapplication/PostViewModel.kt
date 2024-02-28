package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private val empty = Post( //Дата объект
    id = 0,
    author = " ",
    content = " ",
    published = " ",
    likedByMe = false,
    likeCount = 0,
    repByMe = false,
    repCount = 0,
    viewCount = 0
)

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    val edited = MutableLiveData(empty)
    fun save() {
        edited.value?.let {
            repository.save(it)
        }
        edited.value = empty
    }
    fun edit(post: Post) {
        edited.value = post
    }
    fun changeContent(content: String) {
        edited.value?.let {
            val text = content.trim()
            if (it.content == text) {
                return
            }
            edited.value = it.copy(content = text)
        }
    }
    fun likeById(id: Int) = repository.likeById(id)
    fun repById(id: Int) = repository.repById(id)
    fun removeByID(id: Int) = repository.removeById(id)
}

