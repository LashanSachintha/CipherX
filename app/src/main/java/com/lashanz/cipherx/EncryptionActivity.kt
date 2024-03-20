package com.lashanz.cipherx
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class EncryptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selectmethod)

        // Find your buttons by their IDs
        val caesarcipertButton: Button = findViewById(R.id.caesarcipertbutton)
        val tripledesButton: Button = findViewById(R.id.tripledesbutton)
        val rsaButton3: Button = findViewById(R.id.rsatbutton3)
        val rsaButton4: Button = findViewById(R.id.rsatbutton4)
        val rsaButton5: Button = findViewById(R.id.rsatbutton5)
        val imageView4: ImageView = findViewById(R.id.imageView4)

        // Set a click listener for the Caesar Cipher button
        caesarcipertButton.setOnClickListener {
             //Open the CaesarCipherActivity when the button is clicked
            val intent = Intent(this, activity_caesarcipher1::class.java)
            startActivity(intent)
        }

        tripledesButton.setOnClickListener {
            // Handle button click and navigate to another activity
            val intent = Intent(this, ViginereCipher::class.java)
            startActivity(intent)
        }

        rsaButton3.setOnClickListener {
            showToast("RSA Button 3 clicked")
            // Handle button click and navigate to another activity
        }

        rsaButton4.setOnClickListener {
            showToast("RSA Button 4 clicked")
            // Handle button click and navigate to another activity
        }

        rsaButton5.setOnClickListener {
            showToast("RSA Button 5 clicked")
            // Handle button click and navigate to another activity
        }

        imageView4.setOnClickListener {
            onBackPressed() // Go back to the previous page
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}
