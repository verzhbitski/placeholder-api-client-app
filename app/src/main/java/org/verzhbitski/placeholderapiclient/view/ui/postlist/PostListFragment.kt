package org.verzhbitski.placeholderapiclient.view.ui.postlist

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
import kotlinx.android.synthetic.main.post_list_fragment.*
import org.verzhbitski.placeholderapiclient.databinding.PostListFragmentBinding
import org.verzhbitski.placeholderapiclient.view.adapter.PostListAdapter

private const val ARG_USER_ID = "userId"

class PostListFragment : Fragment() {

    companion object {
        fun newInstance(userId: Int) =
            PostListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_USER_ID, userId)
                }
            }
    }

    private var userId = 0

    private lateinit var viewDataBinding: PostListFragmentBinding

    private lateinit var postListAdapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userId = it.getInt(ARG_USER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = PostListFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = ViewModelProviders.of(this@PostListFragment,
                PostListViewModelFactory(userId)
            ).get(PostListViewModel::class.java)
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
        postListRecyclerView.layoutManager = linearLayoutManager
        postListRecyclerView.addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        postListAdapter = PostListAdapter(ArrayList())
        postListRecyclerView.adapter = postListAdapter
    }

    private fun setupObservers() {
        viewDataBinding.viewModel?.postList?.observe(viewLifecycleOwner, Observer {
            postListAdapter.update(it)
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
