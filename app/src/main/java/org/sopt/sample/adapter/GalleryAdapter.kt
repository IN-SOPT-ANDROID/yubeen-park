package org.sopt.sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import org.sopt.sample.R
import org.sopt.sample.data.remote.response.ResponseUser
import org.sopt.sample.databinding.LayoutGalleryRowBinding

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    private var userItems: List<ResponseUser.UserListInfo>? = emptyList()

    fun setItems(items: List<ResponseUser.UserListInfo>) {
        this.userItems = items
        notifyDataSetChanged()
    }

    class GalleryViewHolder(
        private val binding: LayoutGalleryRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ResponseUser.UserListInfo) {
            binding.tvFirstName.text = user.firstName
            binding.tvEmail.text = user.email
            binding.ivAvatar.load(user.avatar) {
                transformations(CircleCropTransformation())
                error(R.drawable.github)
            }
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