package com.example.trial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var count=0
        button.setOnClickListener {
            count+=1
            textView.text=count.toString()
//            Toast.makeText(this@MainActivity,"You CLicked Me",Toast.LENGTH_SHORT).show()
        }
    }
}