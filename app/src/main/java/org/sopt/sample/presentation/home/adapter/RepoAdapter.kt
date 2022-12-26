package org.sopt.sample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import org.sopt.sample.R
import org.sopt.sample.data.remote.response.ResponseUser
import org.sopt.sample.databinding.LayoutGalleryRowBinding
import org.sopt.sample.presentation.home.adapter.GalleryAdapter


class RepoAdapter : ListAdapter<ResponseUser.UserListInfo, RepoAdapter.RepoViewHolder>(
    FollowerDiffCallback
) {
    private var userItems: List<ResponseUser.UserListInfo> = emptyList()

    fun setItems(items: List<ResponseUser.UserListInfo>) {
        this.userItems = items
        submitList(items)
    }

    class RepoViewHolder(
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoAdapter.RepoViewHolder {
        val binding =
            LayoutGalleryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoAdapter.RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoAdapter.RepoViewHolder, position: Int) {
        userItems.let {
            holder.bind(it[position])
        }
    }

}

object FollowerDiffCallback : DiffUtil.ItemCallback<ResponseUser.UserListInfo>() {
    override fun areItemsTheSame(
        oldItem: ResponseUser.UserListInfo,
        newItem: ResponseUser.UserListInfo
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ResponseUser.UserListInfo,
        newItem: ResponseUser.UserListInfo
    ): Boolean {
        return oldItem == newItem
    }
}
