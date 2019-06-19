package com.lordtaylor.exportlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.lordtaylor.exportlist.view.ExportListFragment
import com.lordtaylor.exportlist.view.SharedViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container_r,ExportListFragment()).commit()
    }
}
