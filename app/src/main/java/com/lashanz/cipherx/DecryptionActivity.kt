package com.lashanz.cipherx
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast


class DecryptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectmethod)

        // Find your buttons by their IDs
        val caesarciperButton: Button = findViewById(R.id.caesarcipertbutton)
        val tripledesButton: Button = findViewById(R.id.tripledesbutton)
        val rsaButton3: Button = findViewById(R.id.rsatbutton3)
        val rsaButton4: Button = findViewById(R.id.rsatbutton4)

        val imageView4: ImageView = findViewById(R.id.imageView4)

        // Set a click listener for the Caesar Cipher button
        caesarciperButton.setOnClickListener {

            //Open the CaesarCipherActivity when the button is clicked
            val intent = Intent(this, activity_caesarcipher2::class.java)
            startActivity(intent)
        }

        tripledesButton.setOnClickListener {
            // Handle button click and navigate to another activity
            val intent = Intent(this, ViginereCipherDecripit::class.java)
            startActivity(intent)
        }

        rsaButton3.setOnClickListener {
            val intent = Intent(this, RailFenseDecrypt::class.java)
            startActivity(intent)
        }

        rsaButton4.setOnClickListener {
            val intent = Intent(this, RailFenseDecrypt::class.java)
            startActivity(intent)
        }



        imageView4.setOnClickListener {
            onBackPressed() // Go back to the previous page
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
