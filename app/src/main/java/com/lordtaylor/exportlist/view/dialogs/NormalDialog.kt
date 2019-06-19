package com.lordtaylor.exportlist.view.dialogs

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.lordtaylor.exportlist.R
import kotlinx.android.synthetic.main.popup_normal_filter.*
import java.lang.ClassCastException
import com.lordtaylor.exportlist.view.dialogs.AppDialog.*

class NormalDialog : DialogFragment(),AppDialog {

    private lateinit var type: Type
    private val TAG = "NormalDialog"
    private lateinit var listerner: FilterDialogListener
    lateinit var text: EditText


    fun setCallBackListener(context: Fragment?) {
        try {
            listerner = context as FilterDialogListener
        } catch (e: ClassCastException) {
            Log.e(TAG, "error message ${e.localizedMessage}")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.popup_normal_filter, container, false)

        text = view.findViewById(R.id.edittext_filter)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text = edittext_filter
        button_cancle.setOnClickListener {
            fragmentManager?.popBackStack()
            listerner.onNegativeClick()
        }
        button_ok.setOnClickListener {
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
        return text.text.toString()
    }

}