package com.jonasrosendo.desafiousemobile.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.jonasrosendo.desafiousemobile.R
import com.jonasrosendo.desafiousemobile.domain.entities.User
import com.jonasrosendo.desafiousemobile.presentation.commons.load
import com.jonasrosendo.desafiousemobile.presentation.fragments.PeopleFragment
import com.jonasrosendo.desafiousemobile.presentation.fragments.PeopleFragmentDirections
import de.hdodenhof.circleimageview.CircleImageView

class UsersAdapter(private val users: ArrayList<User>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_users_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    fun update(newUsers: List<User>) {
        if (users.isNotEmpty()) users.clear()
        notifyDataSetChanged()
        for ((position, user) in newUsers.withIndex()) {
            add(user, position)
        }
    }

    private fun add(user: User, position: Int) {
        users.add(position, user)
        notifyItemInserted(position)
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val userPhoto = view.findViewById<CircleImageView>(R.id.iv_user_image)
        private val tvName = view.findViewById<TextView>(R.id.tv_user_name)
        private val tvEmail = view.findViewById<TextView>(R.id.tv_user_email)
        private val clItemLayout = view.findViewById<ConstraintLayout>(R.id.cl_item_users_layout)

        fun bind(user: User) {
            userPhoto.load(user.photo.toString().trim())
            tvName.text = user.name?.trim()
            tvEmail.text = user.email?.trim()
            showVerificationStatus(user)

            clItemLayout.setOnClickListener {
                val action = PeopleFragmentDirections.actionPeopleFragmentToUserProfileFragment(user.id)
                view.findNavController().navigate(action)
            }
        }

        private fun showVerificationStatus(user: User) {
            user.isVerified?.let {
                if (it) {
                    tvName.setCompoundDrawablesWithIntrinsicBounds(
                        null,
                        null,
                        ContextCompat.getDrawable(
                            view.context,
                            R.drawable.ic_check
                        ),
                        null
                    )
                }
            }
        }
    }
}