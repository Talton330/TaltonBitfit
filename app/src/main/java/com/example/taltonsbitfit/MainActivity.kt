package com.example.taltonsbitfit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listData = ArrayList<Model>()
        val db = DBHelper(this, null)
        val rView = findViewById<RecyclerView>(R.id.recyclerView)
        rView.layoutManager = LinearLayoutManager(this)

        val addFoodBtn:Button = findViewById(R.id.food_input)

            val cursor = db.getFood()
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    listData.add(
                        Model(
                            cursor.getString(cursor.getColumnIndex("food_name")),
                        cursor.getInt(cursor.getColumnIndex("calorie_count"))
                        )
                    )
                    val adapter = RVAdapter(listData)
                    rView.adapter = adapter
                }
            }

        addFoodBtn.setOnClickListener{
            val intent = Intent(this, UserInputActivity::class.java)
            startActivity(intent)
        }

        }
    }
