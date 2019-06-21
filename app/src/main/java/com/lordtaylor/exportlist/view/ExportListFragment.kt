package com.lordtaylor.exportlist.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lordtaylor.exportlist.R
import com.lordtaylor.exportlist.model.ExportItem
import com.lordtaylor.exportlist.view.dialogs.AppDialog
import com.lordtaylor.exportlist.view.dialogs.NormalDialog
import kotlinx.android.synthetic.main.export_list_fragment.*
import com.lordtaylor.exportlist.view.dialogs.AppDialog.*
import com.lordtaylor.exportlist.view.dialogs.DateDialog
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class ExportListFragment : Fragment(), FilterDialogListener {


    private val TAG = "ExportListFragment"


    private lateinit var viewModel: SharedViewModel

    lateinit var items: List<ExportItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.export_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(SharedViewModel::class.java)
        viewModel.getAllItems().observe(this, object : Observer<List<ExportItem>> {
            override fun onChanged(t: List<ExportItem>?) {
                items = t!!
                initTable()
            }


        })


    }

    private fun initTable() {
        item_table.removeAllViews()
        var par = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1.0f)
        par.setMargins(5, 5, 5, 5)



        var name = Button(context)
        name.text = getText(R.string.button_name)
        name.background = resources.getDrawable(R.drawable.drawable_round_button)
        name.layoutParams = par
        name.setOnClickListener { nameButtonClick(Type.NAME) }
        var date = Button(context)
        date.text = getText(R.string.button_date)
        date.background = resources.getDrawable(R.drawable.drawable_round_button)
        date.layoutParams = par
        date.setOnClickListener { dateButtonClick(Type.DATE) }
        var user = Button(context)
        user.text = getText(R.string.button_user)
        user.background = resources.getDrawable(R.drawable.drawable_round_button)
        user.layoutParams = par
        user.setOnClickListener { userButtonClick(Type.USER) }
        var location = Button(context)
        location.text = getText(R.string.button_location)
        location.background = resources.getDrawable(R.drawable.drawable_round_button)
        location.layoutParams = par
        location.setOnClickListener { locationButtonClick(Type.LOCATION) }
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
            name.background = resources.getDrawable(R.drawable.drawable_round_text_view)
            name.text = it.name
            name.layoutParams = par
            var date = TextView(context)
            date.text = it.date
            date.background = resources.getDrawable(R.drawable.drawable_round_text_view)
            date.layoutParams = par
            var user = TextView(context)
            user.text = it.user
            user.background = resources.getDrawable(R.drawable.drawable_round_text_view)
            user.layoutParams = par
            var location = TextView(context)
            location.text = it.location
            location.background = resources.getDrawable(R.drawable.drawable_round_text_view)
            location.layoutParams = par
            tr.addView(name)
            tr.addView(date)
            tr.addView(user)
            tr.addView(location)
            item_table.addView(tr)
        }


    }

    private fun nameButtonClick(type: Type) {
        var dialog = NormalDialog()
        fragmentManager?.beginTransaction()!!.addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(android.R.id.content, dialog).commit()
        dialog.setCallBackListener(this)
        dialog.setType(type)
    }

    private fun dateButtonClick(type:Type) {
        var dialog = DateDialog()
        fragmentManager?.beginTransaction()!!.addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(android.R.id.content, dialog).commit()
        dialog.setCallBackListener(this)
        dialog.setType(type)
    }

    private fun userButtonClick(type: Type) {
        var dialog = NormalDialog()
        fragmentManager?.beginTransaction()!!.addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(android.R.id.content, dialog).commit()
        dialog.setCallBackListener(this)
        dialog.setType(type)
    }

    private fun locationButtonClick(type: Type) {
        var dialog = NormalDialog()
        fragmentManager?.beginTransaction()!!.addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(android.R.id.content, dialog).commit()
        dialog.setCallBackListener(this)
        dialog.setType(type)
    }

    override fun onPositiveClick(dialog: AppDialog) {

        viewModel.searchForName(dialog.getType(), dialog.getFilter())
            .observe(this, object : Observer<List<ExportItem>> {
                override fun onChanged(t: List<ExportItem>?) {
                    items = t!!
                    initTable()
                }
            })
    }

    override fun onNegativeClick() {
        viewModel.getAllItems().observe(this, object : Observer<List<ExportItem>> {
            override fun onChanged(t: List<ExportItem>?) {
                items = t!!
                initTable()
            }
        })
    }

}