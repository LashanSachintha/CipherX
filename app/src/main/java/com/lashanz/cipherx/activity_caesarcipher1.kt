package com.lashanz.cipherx

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.ImageView
import android.widget.Toast

class activity_caesarcipher1 : AppCompatActivity() {

    private object CaesarCipher {
        fun encrypt(text: String, shift: Int): String {
            val result = StringBuilder()

            for (char in text) {
                if (char.isLetter()) {
                    val start = if (char.isUpperCase()) 'A' else 'a'
                    result.append((start + (char - start + shift) % 26).toChar())
                } else {
                    result.append(char)
                }
            }

            return result.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caesarcipher1)

        val encriptButton: Button = findViewById(R.id.encriptbutton)
        val copyButton: Button = findViewById(R.id.copybutton2)
        val imageView4: ImageView = findViewById(R.id.imageView4)
        val textInputEditText: TextInputEditText = findViewById(R.id.textInputEditText)
        val textView2: TextView = findViewById(R.id.textView2)

        encriptButton.setOnClickListener {
            val textToEncrypt = textInputEditText.text.toString()
            val encryptedText = CaesarCipher.encrypt(textToEncrypt, 3) // Change the shift value as needed
            textView2.text = encryptedText
        }

        copyButton.setOnClickListener {
            val encryptedText = textView2.text.toString()

            // Copy to clipboard
            val clipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Encrypted Text", encryptedText)
            clipboardManager.setPrimaryClip(clipData)

            // Show a toast indicating successful copy
            Toast.makeText(this, "Encrypted text copied to clipboard", Toast.LENGTH_SHORT).show()
        }
        imageView4.setOnClickListener {
            onBackPressed() // Go back to the previous page
        }
    }
}