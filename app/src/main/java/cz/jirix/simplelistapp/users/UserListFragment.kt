package cz.jirix.simplelistapp.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import cz.jirix.simplelistapp.R
import cz.jirix.simplelistapp.model.Progress
import cz.jirix.simplelistapp.model.User
import kotlinx.android.synthetic.main.fragment_user_list.*

class UserListFragment : Fragment() {

    private var columnCount = 1
    private val usersAdapter = UserRecyclerViewAdapter { user -> onUserClicked(user) }
    private val viewModel: UserListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_users.layoutManager = when {
            columnCount <= 1 -> LinearLayoutManager(context)
            else -> GridLayoutManager(context, columnCount)
        }
        list_users.adapter = usersAdapter

        button_refresh.setOnClickListener { onRefreshClicked() }

        viewModel.getUsers().observe(viewLifecycleOwner) { users ->
            onUsersUpdated(users)
        }
        viewModel.getLoadingProgress().observe(viewLifecycleOwner) {
            button_refresh.showProgress(it.state == Progress.LOADING)
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.cancelRefresh()
    }

    private fun onUsersUpdated(users: List<User>) {
        usersAdapter.setData(users)
        users.isEmpty().let { empty ->
            text_empty_list.visibility = if (empty) View.VISIBLE else View.GONE
            list_users.visibility = if (empty) View.GONE else View.VISIBLE
        }
    }

    private fun onUserClicked(user: User) {
        viewModel.removeUser(user)
    }

    private fun onRefreshClicked() {
        viewModel.refreshUsersFromApi()
    }
}
