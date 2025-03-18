package com.example.fvbh

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var score: Long = 0
    var inc: Long = 1

    private lateinit var btn_tap: Button
    private lateinit var btn_boost: ImageView
    private lateinit var text_score: TextView
    private lateinit var text_inc: TextView

    private lateinit var shared: SharedPreferences
    private lateinit var sharedEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        shared = getSharedPreferences("main", 0)
        sharedEditor = shared.edit()
        load()

        btn_tap = findViewById(R.id.btn_tap)
        btn_boost = findViewById(R.id.btn_boost)
        text_score = findViewById(R.id.text_score)
        text_inc = findViewById(R.id.text_inc)


        btn_tap.setOnClickListener{ add()}
        btn_boost.setOnClickListener{
            save()
            startActivity(Intent(this, BoostActivity::class.java))
        }
    }

    private fun add(){
        score += inc
        text_score.text = String.format(getString(R.string.text_score_text), score)
    }

    override fun onResume(){
        super.onResume()
        load()

        text_score.text = String.format(getString(R.string.text_score_text), score)
        text_inc.text = String.format(getString(R.string.text_inc_text), inc)
    }

    private fun save(){
        sharedEditor.putLong("score", score)
        sharedEditor.putLong("inc", inc)
        sharedEditor.commit()
    }

    private fun load(){
        score = shared.getLong("score", 0)
        inc = shared.getLong("inc", 1)
    }

    override fun onStop() {
        save()
        super.onStop()
    }
}