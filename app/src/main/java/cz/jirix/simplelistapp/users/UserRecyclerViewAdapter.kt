package cz.jirix.simplelistapp.users


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import cz.jirix.simplelistapp.R
import cz.jirix.simplelistapp.model.User
import kotlinx.android.synthetic.main.list_item_user.view.*

class UserRecyclerViewAdapter(
    private val listener: ((User) -> Unit)?
) : RecyclerView.Adapter<UserRecyclerViewAdapter.UserViewHolder>() {

    private val data = mutableListOf<User>()
    private val onItemClickListener: View.OnClickListener

    init {
        onItemClickListener = View.OnClickListener { v ->
            val item = v.tag as User
            listener?.invoke(item)
        }
    }

    fun setData(users: List<User>) {
        with(data) {
            clear()
            addAll(users)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    inner class UserViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.item_user_name
        private val phone: TextView = view.item_user_phone
        private val isMaster: TextView = view.item_user_is_master
        private val photo: ImageView = view.item_user_photo

        fun bind(user: User) {
            view.tag = user
            view.setOnClickListener(onItemClickListener)

            name.text = user.alias
            phone.text = user.phone_number
            isMaster.text = isMaster.context.getString(
                if (user.is_master) R.string.list_label_master_user else R.string.list_label_regular_user
            )

            Glide
                .with(view.context)
                .load(user.picture_url)
                .centerCrop()
                .placeholder(android.R.drawable.progress_horizontal)
                .into(photo)
        }
    }
}
