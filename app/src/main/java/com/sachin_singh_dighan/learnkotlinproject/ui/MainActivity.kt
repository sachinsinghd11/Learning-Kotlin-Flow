package com.sachin_singh_dighan.learnkotlinproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sachin_singh_dighan.learnkotlinproject.R
import com.sachin_singh_dighan.learnkotlinproject.ui.retrofit.single.SingleNetworkCallActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSingleNetworkCallActivity(view: View) {
        startActivity(Intent(this@MainActivity, SingleNetworkCallActivity::class.java))
    }

}