package com.example.internshiptracker

import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.google.android.material.card.MaterialCardView

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // 🔍 Logcat proof
        Log.d("DashboardActivity", "Dashboard loaded successfully")

        val todayCard = findViewById<View>(R.id.cardToday)
            todayCard.setOnClickListener {

                // ✅ ALWAYS open TaskListActivity
                startActivity(Intent(this, TaskListActivity::class.java))

                // 🔍 Log navigation
                Log.d("DashboardActivity", "Navigated to TaskListActivity")

                // 🌐 Check internet ONLY for Firebase
                if (!isNetworkAvailable()) {
                    Toast.makeText(
                        this,
                        "No Internet - Data not synced",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e("NetworkError", "No internet connection")
                    return@setOnClickListener
                }

                // Firebase write (non-blocking)
                try {
                    val database = FirebaseDatabase.getInstance()
                    val usersRef = database.getReference("users")

                    val user = User(
                        name = "Thanu",
                        email = "thanu@email.com"
                    )

                    usersRef.push().setValue(user)
                        .addOnSuccessListener {
                            Log.d("FirebaseSuccess", "User data saved")
                        }
                        .addOnFailureListener { e ->
                            Log.e("FirebaseError", e.message.toString())
                        }

                } catch (e: Exception) {
                    Log.e("DashboardException", e.message.toString())
                }
            }

    }

    // 🌐 Helper function — MUST be outside onCreate
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        return network != null
    }
}
