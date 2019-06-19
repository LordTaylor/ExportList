package com.lordtaylor.exportlist.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lordtaylor.exportlist.R
import com.lordtaylor.exportlist.model.ExportItem

class ExportListFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel

    lateinit var items: List<ExportItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.export_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        viewModel.getAllItems().observe(this, Observer {
            items = it
        })
    }


}