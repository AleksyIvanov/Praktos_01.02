package com.example.myapplication

import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Int) = repository.likeById(id)
    fun repById(id: Int) = repository.repById(id)
    fun removeByID(id: Int) = repository.removeById(id)
}