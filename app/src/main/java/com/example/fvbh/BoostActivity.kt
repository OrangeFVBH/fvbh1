package com.example.fvbh

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fvbh.boosts.BoostView

class BoostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_boost)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<LinearLayout>(R.id.boosts_layout).removeAllViews()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.boosts_layout, BoostView.newInstance(
            "Буст +", 1, 100, 1, 0
        ), "boost 0")
        transaction.add(R.id.boosts_layout, BoostView.newInstance(
            "Буст +-", 2, 500, 50, 1
        ), "boost 1")
        transaction.add(R.id.boosts_layout, BoostView.newInstance(
            "Бусти", 3, 1000, 100, 2
        ), "boost 2")


        transaction.commit()
    }
}