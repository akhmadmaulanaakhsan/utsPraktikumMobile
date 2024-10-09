package com.example.utsprakmobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.utsprakmobile.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSignIn.setOnClickListener {
            if (isLoginValid()) {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun isLoginValid(): Boolean {
        val usernameInput = binding.etUsername.text.toString()

        // Dapatkan data pengguna dari SharedPreferences
        val sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val savedUsername = sharedPref.getString("username", "")

        // Perform login validation
        return if (usernameInput == savedUsername) {
            true
        } else {
            Toast.makeText(this, "Invalid login credentials.", Toast.LENGTH_SHORT).show()
            false
        }
    }
}