package com.lordtaylor.exportlist.view.dialogs

import androidx.fragment.app.DialogFragment

interface AppDialog {
    fun getFilter(): String
    fun getType(): Type

    interface FilterDialogListener {
        fun onPositiveClick(dialog: AppDialog)
        fun onNegativeClick()
    }

    enum class Type {
        NAME,

        USER,

        LOCATION,

        DATE

    }
}