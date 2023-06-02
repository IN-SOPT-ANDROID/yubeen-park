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


class RepoAdapter : ListAdapter<ResponseUser.UserListInfo, RepoAdapter.RepoViewHolder>(
    FollowerDiffCallback
) {
    class RepoViewHolder(
        private val binding: LayoutGalleryRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ResponseUser.UserListInfo) {
            binding.user = user
            binding.ivAvatar.load(user.avatar) {
                transformations(CircleCropTransformation())
                error(R.drawable.github)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding =
            LayoutGalleryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}

//ListAdapter
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
