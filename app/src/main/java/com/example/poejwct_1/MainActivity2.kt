package com.example.poejwct_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataAdapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val type = intent.getStringExtra("type") ?: ""
        val marks = intent.getStringExtra("marks") ?: ""
        DataManager.dataList.add(DataItem(type, marks))

        // Pass 'this' as the second argument to provide the activity context
        dataAdapter = DataAdapter(DataManager.dataList, this)
        recyclerView.adapter = dataAdapter

        val backButton = findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val resetButton = findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            // Clear the data list and notify the adapter
            DataManager.dataList.clear()
            dataAdapter.notifyDataSetChanged()

            val fragmentManager = supportFragmentManager
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

            for (fragment in fragmentManager.fragments) {
                fragmentManager.beginTransaction().remove(fragment).commit()
            }

            Toast.makeText(this, "Data reset", Toast.LENGTH_SHORT).show()
        }

        val thirdActivityButton = findViewById<Button>(R.id.about_me_button)
        thirdActivityButton.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            val type = it.getStringExtra("type") ?: ""
            val marks = it.getStringExtra("marks") ?: ""
            DataManager.dataList.add(DataItem(type, marks))
            dataAdapter.notifyItemInserted(DataManager.dataList.size - 1)
        }
    }
}
