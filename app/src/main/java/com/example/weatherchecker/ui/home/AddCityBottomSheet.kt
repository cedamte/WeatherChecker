package com.example.weatherchecker.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherchecker.R
import com.example.weatherchecker.base.inflate
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.bottom_sheet_add_city.*

class AddCityBottomSheet : BottomSheetDialogFragment() {

    private lateinit var selectionListener: (Boolean) -> Unit


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.bottom_sheet_add_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSave.setOnClickListener { if (::selectionListener.isInitialized) selectionListener(true).also { dismiss() } }
        btnIgnore.setOnClickListener { if (::selectionListener.isInitialized) selectionListener(false)
            .also { dismiss() } }

    }

    companion object {
        fun newInstance(selectionListener: (Boolean) -> Unit): AddCityBottomSheet {
            val fragment = AddCityBottomSheet()
            fragment.selectionListener = selectionListener
            return fragment
        }
    }
}