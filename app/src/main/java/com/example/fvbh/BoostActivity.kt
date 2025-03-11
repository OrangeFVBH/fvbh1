package com.example.fvbh

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.fvbh.boosts.BoostView
import com.example.fvbh.boosts.models.ActiveBoost

class BoostActivity : AppCompatActivity() {
    private lateinit var shared: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_boost)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        shared = getSharedPreferences("main", 0)

        findViewById<LinearLayout>(R.id.boosts_layout).removeAllViews()
        BoostView.updateCountMoney(shared.getLong("score", 0))
        val transaction = supportFragmentManager.beginTransaction()
        val boost_count = resources.getInteger(R.integer.boost_count)
        for (i in 0 until boost_count) {
            val boost = ActiveBoost.load(i);
            transaction.add(R.id.boosts_layout, BoostView.newInstance(boost.id), "boost $i")
        }
        transaction.commit()
    }
}