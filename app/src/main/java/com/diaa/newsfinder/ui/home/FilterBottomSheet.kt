package com.diaa.newsfinder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.diaa.newsfinder.R
import com.diaa.newsfinder.databinding.BottomSheetFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetFilterBinding
    private var listener: OnButtonsClickListener? = null

    private var country: String? = null
    private var category: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.countriesToggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            // Respond to button selection
            country = when (checkedId) {
                R.id.usBtn -> {
                    "us"
                }
                R.id.aeBtn -> {
                    "ae"
                }
                R.id.joBtn -> {
                    "jo"
                }
                else -> {
                    null
                }
            }
        }

        binding.categoriesToggleButton.addOnButtonCheckedListener { toggleButton, checkedId, isChecked ->
            // Respond to button selection
            category = when (checkedId) {
                R.id.sportsBtn -> {
                    "sports"
                }
                R.id.businessBtn -> {
                    "business"
                }
                R.id.healthBtn -> {
                    "health"
                }
                else -> {
                    null
                }
            }
        }

        binding.applyBtn.setOnClickListener {
            listener?.onApply(Pair(country, category))
        }

        binding.clearBtn.setOnClickListener {
            listener?.onClear()
        }

    }

    fun addListener(listener: OnButtonsClickListener) {
        this.listener = listener
    }

    companion object {
        const val TAG = "FilterBottomSheet"
        fun newInstance(): FilterBottomSheet {
            return FilterBottomSheet().apply {

            }
        }
    }

    interface OnButtonsClickListener {
        fun onApply(filters: Pair<String?, String?>) // first one is country, second is Category
        fun onClear()
    }

}