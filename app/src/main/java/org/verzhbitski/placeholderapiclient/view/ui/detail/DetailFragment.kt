package org.verzhbitski.placeholderapiclient.view.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.detail_fragment.*
import org.verzhbitski.placeholderapiclient.databinding.DetailFragmentBinding
import org.verzhbitski.placeholderapiclient.view.adapter.DetailAdapter

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewDataBinding: DetailFragmentBinding

    private lateinit var detailAdapter: DetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = DetailFragmentBinding.inflate(inflater, container, false).apply {
            viewModel = ViewModelProviders.of(this@DetailFragment).get(DetailViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }

        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
    }

    private fun setupViewPager() {
        detailAdapter = arguments?.getInt("userId")?.let { DetailAdapter(context, childFragmentManager, it) }!!
        viewPager.adapter = detailAdapter
    }
}
