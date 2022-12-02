package org.sopt.sample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.sample.data.Repo
import org.sopt.sample.databinding.LayoutGithubRepoBinding
import org.sopt.sample.databinding.LayoutRecyclerTextBinding

class RepoAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater by lazy { LayoutInflater.from(context) }
    private var repoList: List<Repo> = emptyList()

    class RepoViewHolder(
        private val binding: LayoutGithubRepoBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onRepoBind(data: Repo) {
            binding.imgGithub.setImageDrawable(binding.root.context.getDrawable(data.image))
            binding.txtGithubRepoName.text = data.name
            binding.txtGithubRepoAuthor.text = data.author
        }
    }

    class TextViewHolder(
        private val binding: LayoutRecyclerTextBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onTextBind(data: Repo) {
            binding.rowText.text = data.name
        }
    }

    //getItemViewType의 리턴값 Int가 viewType으로 넘어온다.
    //viewType으로 넘어오는 값에 따라 viewHolder 처리해주기!
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            Repo.TEXT_TYPE -> {
                val binding = LayoutRecyclerTextBinding.inflate(inflater, parent, false)
                TextViewHolder(binding)
            }
            Repo.REPO_TYPE -> {
                val binding = LayoutGithubRepoBinding.inflate(inflater, parent, false)
                RepoViewHolder(binding)
            }
            else -> {
                throw ClassCastException("Unknown viewType Error")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return repoList[position].type
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (repoList[position].type) {
            Repo.REPO_TYPE -> {
                (holder as RepoViewHolder).onRepoBind(repoList[position])
            }
            Repo.TEXT_TYPE -> {
                (holder as TextViewHolder).onTextBind(repoList[position])
            }
        }
    }

    override fun getItemCount() = repoList.size

    fun setRepoList(repoList: List<Repo>) {
        this.repoList = repoList.toList()
        notifyItemInserted(0)
    }

}