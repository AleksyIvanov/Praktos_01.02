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
            author = "ГПБОУ ВО БТПИТ",
            content = "20 февраля студенты 4 курса специальности «Дошкольное образование» Борисоглебского техникума промышленных и информационных технологий совместно с преподавателем Гребенниковой Ларисой Владимировной приняли участие в мероприятии, организованном в обособленном подразделении ВИРО им. Н.Ф. Бунакова в Борисоглебском городском округе ВО.\n" +
                    "Мастер-класс «Технология формирования положительной мотивации к познавательной активности дошкольников через интеллект-карты» проводила Стремкова Наталья Сергеевна, воспитатель из детского сада Новохоперского муниципального района. Она поделилась своим опытом работы.\n" +
                    "Студенты получили теоретические знания и практические умения в составлении и использовании интеллект-карт в работе воспитателя.",
            published = "21 февраля в 19:12",
            likedByMe = false,
            likeCount = 123,
            repByMe = false,
            repCount = 15,
            viewCount = 300
        ),
        Post(
            id = 1,
            author = "ГПБОУ ВО БТПИТ",
            content = "15 февраля на базе 1 и 3 корпусов ГБПОУ ВО «БТПИТ» прошли торжественные митинги, посвященные 35-й годовщине со дня вывода советских войск из Республики Афганистан с поднятием государственного флага и возложением цветов к «Деревьям Памяти».\n Каштаны были посажены на территории учебного корпуса № 1 и 3 в честь воинов-интернационалистов, которые учились в нашем техникуме.\nСтуденты почтили память участников войн и конфликтов минутой молчания.",
            published = "21 февраля в 19:12",
            likedByMe = false,
            likeCount = 150,
            repByMe = false,
            repCount = 25,
            viewCount = 240
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
                author = "ГПБОУ ВО БТПИТ",
                likedByMe = false,
                published = "Сейчас"
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