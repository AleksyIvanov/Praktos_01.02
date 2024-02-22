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
        content = "15 февраля на базе 1 и 3 корпусов ГБПОУ ВО «БТПИТ» прошли торжественные митинги, посвященные 35-й годовщине со дня вывода советских войск из Республики Афганистан с поднятием государственного флага и возложением цветов к «Деревьям Памяти».\\nКаштаны были посажены на территории учебного корпуса № 1 и 3 в честь воинов-интернационалистов, которые учились в нашем техникуме.\\nСтуденты почтили память участников войн и конфликтов минутой молчания.",
        published = "21 февраля в 19:12",
        likedByMe = false,
        likeCount = 999,
        repByMe = false,
        repCount = 15
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
    override fun like() {
        if (!post.likedByMe) {
            post.likeCount++
        } else {
            post.likeCount--
        }
        post = post.copy(likedByMe = !post.likedByMe)
        data.value = post
    }
}