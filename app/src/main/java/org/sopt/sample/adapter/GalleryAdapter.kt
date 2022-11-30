package org.sopt.sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.sample.R
import org.sopt.sample.data.remote.response.ResponseUserList
import org.sopt.sample.databinding.LayoutGalleryRowBinding

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    private var userItems: List<ResponseUserList.userListInfo>? = emptyList()

    fun setItems(items: List<ResponseUserList.userListInfo>) {
        this.userItems = items
        notifyDataSetChanged()
    }

    class GalleryViewHolder(
        private val binding: LayoutGalleryRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ResponseUserList.userListInfo) {
            binding.tvFirstName.text = user.first_name
            binding.tvEmail.text = user.email
            Glide.with(binding.root).load(user.avatar).error(R.drawable.github)
                .into(binding.ivAvatar)
        }
    }

    override fun getItemCount() = userItems?.size ?: 0

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        userItems?.let {
            holder.bind(it[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding =
            LayoutGalleryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }
}