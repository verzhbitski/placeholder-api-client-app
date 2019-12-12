package org.verzhbitski.placeholderapiclient.view.ui.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.user_list_fragment.*
import org.verzhbitski.placeholderapiclient.databinding.UserListFragmentBinding
import org.verzhbitski.placeholderapiclient.view.adapter.UserListAdapter

class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private lateinit var viewDataBinding: UserListFragmentBinding

    private val userListAdapter = UserListAdapter(ArrayList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = UserListFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = ViewModelProviders.of(this@UserListFragment).get(UserListViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupSwipeToRefresh()
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        userListRecyclerView.layoutManager = linearLayoutManager
        userListRecyclerView.addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        userListRecyclerView.adapter = userListAdapter
    }

    private fun setupObservers() {
        viewDataBinding.viewModel?.userList?.observe(viewLifecycleOwner, Observer {
            userListAdapter.update(it)
        })

        viewDataBinding.viewModel?.refreshing?.observe(viewLifecycleOwner, Observer {
            swipeRefreshLayout.isRefreshing = it
        })

        viewDataBinding.viewModel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun setupSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            viewDataBinding.viewModel?.fetch()
        }
    }
}
