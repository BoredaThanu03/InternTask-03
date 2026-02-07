package com.example.internshiptracker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        // RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTasks)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val tasks = listOf(
            Task("UI Design", "Due today", "PENDING"),
            Task("API Integration", "Due today", "PENDING"),
            Task("Database Setup", "Due today", "PENDING"),
            Task("Testing & Bug Fixing", "Due today", "PENDING"),
            Task("Documentation", "Due today", "PENDING")
        )

        recyclerView.adapter = TaskAdapter(tasks)

        // 🔥 THIS IS REQUIRED
        val btnProfile = findViewById<Button>(R.id.btnProfile)
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
