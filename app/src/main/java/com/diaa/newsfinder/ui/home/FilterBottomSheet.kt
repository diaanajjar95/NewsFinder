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
        fillCountry(binding.countriesRadioGroup.checkedRadioButtonId)
        fillCategory(binding.categoryRadioGroup.checkedRadioButtonId)

        binding.countriesRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            fillCountry(checkedId)
        }

        binding.categoryRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            fillCategory(checkedId)
        }

        binding.applyBtn.setOnClickListener {
            listener?.onApply(Pair(country, category))
        }

        binding.clearBtn.setOnClickListener {
            listener?.onClear()
        }
    }

    private fun fillCategory(checkedRadioButtonId: Int) {
        when (checkedRadioButtonId) {
            R.id.sport_radio_button -> {
                category = binding.sportRadioButton.text.toString()
            }
            R.id.business_radio_button -> {
                category = binding.businessRadioButton.text.toString()
            }
            R.id.health_radio_button -> {
                category = binding.healthRadioButton.text.toString()
            }
        }
    }

    private fun fillCountry(checkedRadioButtonId: Int) {
        when (checkedRadioButtonId) {
            R.id.us_radio_button -> {
                country = binding.usRadioButton.text.toString()
            }
            R.id.ae_radio_button -> {
                country = binding.aeRadioButton.text.toString()
            }
            R.id.jo_radio_button -> {
                country = binding.joRadioButton.text.toString()
            }
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