package com.example.myapplication

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.PostCardBinding

class PostViewHolder (
    private val binding: PostCardBinding,
    private val listener: PostsAdapter.Listener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(post: Post) {
        binding.apply {
            TxtName.text = post.author
            TxtStatus.text = post.published
            TxtPost.text = post.content
            LikesCount.text = post.likeCount.toString()
            RepCount.text = post.repCount.toString()
            ViewCount.text = post.viewCount.toString()
            imgLikes.setOnClickListener{
                listener.onLike(post)
            }
            imgRepst.setOnClickListener{
                listener.onRepost(post)
            }
            imgLikes.setImageResource(
                if (post.likedByMe) R.drawable.icons8_heart_24_rounded else R.drawable.icons8_heart_24_outline
            )
            imgMenu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.menuRemove -> {
                                listener.onRemove(post)
                                true
                            }
                            R.id.menuEdit ->{
                                listener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
            }
        }
    }
}