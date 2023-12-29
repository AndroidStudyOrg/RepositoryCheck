package org.shop.repositorycheck.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.shop.repositorycheck.databinding.ItemUserBinding
import org.shop.repositorycheck.model.User

class UserAdapter(val onClick: (User) -> Unit) :
    ListAdapter<User, UserAdapter.UserViewHolder>(diffUtil) {
    inner class UserViewHolder(private val viewBinding: ItemUserBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(item: User) {
            viewBinding.usernameTextView.text = item.username
            viewBinding.root.setOnClickListener {
                onClick(item)
            }
        }
    }

    /**
     *  처음 만들어졌을 때 ViewHolder가 없거나 부족하면 새로 만드는 함수
     *  Adapter에는 context가 없음. 대신에 파라미터로 넘어오는 parent가 View이므로 parent.context를 넣어준다
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**
     *  현재 바인드 될 아이템을 불러올 수 있음
     */
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}