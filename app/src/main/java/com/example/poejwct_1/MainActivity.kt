package com.example.poejwct_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DataManager.loadData(this)

        val dropdownMenu = findViewById<Spinner>(R.id.dropdown_menu)
        val marksInput = findViewById<EditText>(R.id.marks_input)
        val submitButton = findViewById<Button>(R.id.submit_button)

        val items = arrayOf("LAB", "TEST", "MIDTERM", "PROJECT1", "PROJECT2", "QUIZ1", "QUIZ2", "FINAL EXAM")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dropdownMenu.adapter = adapter

        submitButton.setOnClickListener {
            val marks = marksInput.text.toString()
            if (marks.isEmpty() || !marks.matches("\\d+".toRegex())) {
                Toast.makeText(this, "Please enter a valid number for marks", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("type", dropdownMenu.selectedItem.toString())
                intent.putExtra("marks", marks)
                startActivity(intent)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        DataManager.saveData(this)
    }
}
