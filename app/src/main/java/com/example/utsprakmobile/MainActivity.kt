package com.example.utsprakmobile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.utsprakmobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSubmit.setOnClickListener {
            val name = binding.etName.text.toString()


            if (validateInputs(name)) {
                saveUserData(name)

                // Tampilkan pesan sukses registrasi
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()

                // Berpindah ke ActivityProfile dan mengirim data
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("name", name)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Enter your name first!.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun validateInputs(
        name: String,
    ): Boolean {
        return !(name.isEmpty())
    }

    // Fungsi untuk menyimpan data user di SharedPreferences
    private fun saveUserData(name: String) {
        val sharedPref = getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putString("name", name)
        editor.apply()
    }


}