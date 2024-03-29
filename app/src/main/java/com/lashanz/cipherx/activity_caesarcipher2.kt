package com.lashanz.cipherx

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class activity_caesarcipher2 : AppCompatActivity() {

    private object CaesarCipher {
        private const val DEFAULT_SHIFT = 3

        fun decrypt(text: String, shift: Int = DEFAULT_SHIFT): String {
            if (text.isEmpty()) {
                // Handle empty input
                return ""
            }

            val result = StringBuilder()

            for (char in text) {
                if (char.isLetter()) {
                    val start = if (char.isUpperCase()) 'A' else 'a'
                    // Apply the modified Caesar Cipher decryption formula
                    result.append((start + (char - start - shift + 26) % 26).toChar())
                } else {
                    result.append(char)
                }
            }

            return result.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_decryption_layout)

        val decriptButton: Button = findViewById(R.id.decriptbutton)
        val copyButton: Button = findViewById(R.id.copybutton2)
        val imageView4: ImageView = findViewById(R.id.imageView4)
        val textInputEditText: TextInputEditText = findViewById(R.id.textInputEditText)
        val textView2: TextView = findViewById(R.id.textView2)

        decriptButton.setOnClickListener {
            val textToDecrypt = textInputEditText.text.toString()
            val decryptedText = CaesarCipher.decrypt(textToDecrypt)
            textView2.text = decryptedText
        }

        copyButton.setOnClickListener {
            val decryptedText = textView2.text.toString()

            // Copy to clipboard
            val clipboardManager =
                getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Decrypted Text", decryptedText)
            clipboardManager.setPrimaryClip(clipData)

            // Show a toast indicating successful copy
            Toast.makeText(this, "Decrypted text copied to clipboard", Toast.LENGTH_SHORT).show()
        }

        imageView4.setOnClickListener {
            onBackPressed() // Go back to the previous page
        }
    }
}
