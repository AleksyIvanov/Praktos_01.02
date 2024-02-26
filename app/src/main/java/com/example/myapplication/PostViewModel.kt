package com.example.myapplication

import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun like(id: Int) = repository.likeById(id)
    fun rep(id: Int) = repository.repById(id)
}