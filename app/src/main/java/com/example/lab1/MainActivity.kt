package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {



    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val data: Intent? = result.data
        if (data != null) { // Check that we have data returned
            val string1 = data.getStringExtra("string1") // 'string1' needs to match the key we used when we put the string in the Intent
            val string2 = data.getStringExtra("string2")

            // Log the value of the strings for easier debugging
            Log.i("MainActivity", "string1: $string1")
            Log.i("MainActivity", "string2: $string2")
        } else {
            Log.i("MainActivity", "Returned null data from AddCardActivity")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        val question = findViewById<TextView>(R.id.question)
        val answer = findViewById<TextView>(R.id.answer)
        val icon = findViewById<ImageView>(R.id.imageView3)

        question.setOnClickListener {
            question.visibility = View.INVISIBLE
            answer.visibility = View.VISIBLE
        }

        answer.setOnClickListener {
            question.visibility = View.VISIBLE
            answer.visibility = View.INVISIBLE
        }



        icon.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            resultLauncher.launch(intent)
        }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
