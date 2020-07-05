package com.example.motion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.motion.utils.logE
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarId)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _btn.setOnClickListener {
            "btn Click".logE()
        }
    }

    override fun onDestroy() {
        clearFindViewByIdCache()
        super.onDestroy()
    }
}
