package com.example.mustdo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.mustdo.presentation.list.ListActivity

class SplashActivity : AppCompatActivity() {
    private val DELAY_TIME = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed( {
            startActivity(Intent(this, ListActivity::class.java))
            finish()
        } , DELAY_TIME)
    }
}