package com.diaa.newsfinder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.diaa.newsfinder.R
import com.diaa.newsfinder.databinding.FragmentHomeBinding
import com.diaa.newsfinder.openWebBrowser
import com.diaa.newsfinder.ui.base.BaseFragment
import com.diaa.newsfinder.ui.home.adapters.HorizontalNewsAdapter
import com.diaa.newsfinder.ui.home.adapters.VerticalNewsAdapter
import com.diaa.newsfinder.ui.home.models.HorizontalNewsItem
import com.diaa.newsfinder.ui.home.models.VerticalNewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

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

        binding.searchEditText.doOnTextChanged { text, start, count, after ->
            binding.nestedScrollView.scrollTo(0, 0)
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                viewModel.searchInNewsApi(text.toString())
            }
        }

        binding.horizontalRecyclerviewSection.horizontalRc.apply {
            adapter = horizontalAdapter
        }

        binding.rc.apply {
            adapter = verticalAdapter
        }

        binding.filterImg.setOnClickListener {
            val filterBottomSheet = FilterBottomSheet.newInstance()

            filterBottomSheet.addListener(object : FilterBottomSheet.OnButtonsClickListener {
                override fun onApply(filters: Pair<String?, String?>) {
//                    viewModel.filterBy(filters.first, filters.second)
                }

                override fun onClear() {

                }
            })

            filterBottomSheet.show(childFragmentManager, FilterBottomSheet.TAG)
        }

        horizontalAdapter.setOnItemClickListener(object :
            HorizontalNewsAdapter.OnItemClickListener {
            override fun onItemClicked(view: View, item: HorizontalNewsItem, position: Int) {
                item.url?.let {
                    openWebBrowser(it)
                } ?: run {
                    showMessage(getString(R.string.no_url_avaliable))
                }
            }
        })

        verticalAdapter.setOnItemClickListener(object : VerticalNewsAdapter.OnItemClickListener {
            override fun onItemClicked(view: View, item: VerticalNewsItem, position: Int) {
                item.url?.let {
                    openWebBrowser(it)
                } ?: run {
                    showMessage(getString(R.string.no_url_avaliable))
                }
            }
        })

        viewModel.horizontalItems.observe(viewLifecycleOwner) {
            binding.searchSection.visibility = View.VISIBLE
            if (it.isNotEmpty()) {
                binding.showHorizontalSection = true
                horizontalAdapter.clear()
                horizontalAdapter.setItems(it)
            } else {
                binding.showHorizontalSection = false
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            showLoader(it)
        }

        viewModel.message.observe(viewLifecycleOwner) {
            showMessage(it)
        }

        viewModel.verticalItems.observe(viewLifecycleOwner) {
            if (it.isEmpty() && verticalAdapter.itemCount == 0) {
                binding.showVerticalSection = false
            } else {
                binding.showVerticalSection = true
                verticalAdapter.setItems(it)
            }
        }

        binding.rc.isNestedScrollingEnabled = false
        binding.nestedScrollView.viewTreeObserver.addOnScrollChangedListener {
            val view =
                binding.nestedScrollView.getChildAt(binding.nestedScrollView.childCount - 1) as View
            val diff: Int =
                view.bottom - (binding.nestedScrollView.height + binding.nestedScrollView.scrollY)
            if (diff == 0) {
                viewModel.getNewsDataByPage()
            }
        }
    }

}