package com.diaa.newsfinder.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.diaa.newsfinder.databinding.FragmentHomeBinding
import com.diaa.newsfinder.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "HomeFragment"

class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val horizontalAdapter: HorizontalNewsAdapter by inject()
    private val verticalAdapter: VerticalNewsAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.horizontalRecyclerviewSection.horizontalRc.apply {
            adapter = horizontalAdapter
        }

        binding.rc.apply {
            adapter = verticalAdapter
        }

        viewModel.horizontalItems.observe(viewLifecycleOwner) {
            horizontalAdapter.clear()
            horizontalAdapter.setItems(it)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            showLoader(it)
        }

        viewModel.verticalItems.observe(viewLifecycleOwner) {
//            verticalAdapter.clear()
            verticalAdapter.setItems(it)
        }

        binding.rc.isNestedScrollingEnabled = false

        binding.nestedScrollView.viewTreeObserver.addOnScrollChangedListener {
            val view =
                binding.nestedScrollView.getChildAt(binding.nestedScrollView.childCount - 1) as View
            val diff: Int =
                view.bottom - (binding.nestedScrollView.height + binding.nestedScrollView.scrollY)
            if (diff == 0) {
                viewModel.getNewsDataByPage()
                Log.d(TAG, "onViewCreated:  your pagination code")
                // your pagination code
            }
        }


        viewModel.getInitData()

    }

}