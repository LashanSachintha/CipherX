package com.lashanz.cipherx

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val encriptButton: Button = findViewById(R.id.encriptbutton)
        val decriptButton: Button = findViewById(R.id.decriptbutton)

        encriptButton.setOnClickListener {
            // Start the EncryptionActivity
            val intent = Intent(this, EncryptionActivity::class.java)
            startActivity(intent)
        }

        decriptButton.setOnClickListener {
            // Start the DecryptionActivity
            val intent = Intent(this, DecryptionActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
