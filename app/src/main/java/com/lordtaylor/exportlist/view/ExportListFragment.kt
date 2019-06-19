package com.lordtaylor.exportlist.view

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lordtaylor.exportlist.R
import com.lordtaylor.exportlist.model.ExportItem
import kotlinx.android.synthetic.main.export_list_fragment.*

class ExportListFragment : Fragment() {
    private val TAG = "ExportListFragment"

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
            initTable()
        })


    }

    private fun initTable() {
        item_table.removeAllViews()
        var par = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f)
        par.setMargins(5,5,5,5)

        var name = Button(context)
        name.text = getText(R.string.button_name)
        name.layoutParams = par
        name.setOnClickListener { nameButtonClick(it) }
        var date = Button(context)
        date.text = getText(R.string.button_date)
        date.layoutParams = par
        date.setOnClickListener { dateButtonClick(it) }
        var user = Button(context)
        user.text = getText(R.string.button_user)
        user.layoutParams = par
        user.setOnClickListener { userButtonClick(it) }
        var location = Button(context)
        location.text = getText(R.string.button_location)
        location.layoutParams = par
        location.setOnClickListener { locationButtonClick(it) }
        var row = TableRow(context)

        row.weightSum = 4.0f
        row.layoutParams = par
        row.addView(name)
        row.addView(date)
        row.addView(user)
        row.addView(location)
        item_table.addView(row)
        items.forEach {
            var tr = TableRow(this.context)
            tr.weightSum = 4.0f
            tr.gravity = Gravity.CENTER
            var name = TextView(this.context)
            name.text = it.name
            name.layoutParams = par
            var date = TextView(context)
            date.text = it.date
            date.layoutParams = par
            var user = TextView(context)
            user.text = it.user
            user.layoutParams = par
            var location = TextView(context)
            location.text = it.location
            location.layoutParams = par
            tr.addView(name)
            tr.addView(date)
            tr.addView(user)
            tr.addView(location)
            item_table.addView(tr)
        }


    }

    private fun nameButtonClick(view: View?) {
    }
    private fun dateButtonClick(view: View?) {
    }
    private fun userButtonClick(view: View?) {
    }
    private fun locationButtonClick(view: View?) {
    }


}