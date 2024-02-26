package com.example.myapplication

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.PostCardBinding

class PostViewHolder (
    private val binding: PostCardBinding,
    private val onLikeListener: OnLikeListener ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            TxtName.text = post.author
            TxtStatus.text = post.published
            TxtPost.text = post.content
            LikesCount.text = post.likeCount.toString()
            RepCount.text = post.repCount.toString()
            ViewCount.text = post.viewCount.toString()
            imgLikes.setOnClickListener{
                onLikeListener(post)
            }
            imgLikes.setImageResource(
                if (post.likedByMe) R.drawable.icons8_heart_24_rounded else R.drawable.icons8_heart_24_outline
            )
        }
    }
}