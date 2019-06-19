package com.lordtaylor.exportlist.view.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.lordtaylor.exportlist.R
import java.lang.ClassCastException
import com.lordtaylor.exportlist.view.dialogs.AppDialog.*
import kotlinx.android.synthetic.main.popup_date_filter.*


class DateDialog : DialogFragment(), AppDialog {
    private var selectedDate: String = ""
    private val TAG = "DateDialog"
    private lateinit var type: Type
    private lateinit var calen: CalendarView
    private lateinit var listerner: FilterDialogListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.popup_date_filter, container, false)
        return view
    }

    fun setCallBackListener(context: Fragment?) {
        try {
            listerner = context as FilterDialogListener
        } catch (e: ClassCastException) {
            Log.e(TAG, "error message ${e.localizedMessage}")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calen = calendarView
        calen.setOnDateChangeListener { calendarView: CalendarView, y: Int, m: Int, d: Int ->
            var mm = if (m < 10) m else ("0$m")
            selectedDate = "${if(d>10)d else "0$d"}.${if (m > 10) m else ("0$m")}.$y"
        }
        calendar_button_cancle.setOnClickListener {
            fragmentManager?.popBackStack()
            listerner.onNegativeClick()
        }
        calendar_button_ok.setOnClickListener {
            fragmentManager?.popBackStack()
            listerner.onPositiveClick(this)
        }
    }

    fun setType(type: Type) {
        this.type = type
    }

    override fun getType(): Type {
        return type
    }

    override fun getFilter(): String {
        return selectedDate
    }
}