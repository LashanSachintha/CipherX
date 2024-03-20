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
import java.util.Locale


class RailFenseEncrypt : AppCompatActivity() {

    private object RailFenceCipher {
        fun encrypt(text: String, key: Int): String {
            val rail = Array(key) { CharArray(text.length) }
            var dirDown = false
            var row = 0

            for (i in text.indices) {
                if (row == 0 || row == key - 1) {
                    dirDown = !dirDown
                }

                rail[row][i] = text[i]
                row += if (dirDown) 1 else -1
            }

            var result = ""
            for (i in 0 until key) {
                for (j in text.indices) {
                    if (rail[i][j] != '\u0000') {
                        result += rail[i][j]
                    }
                }
            }

            return result
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encryption_layout)

        val encryptButton: Button = findViewById(R.id.encriptbutton)
        val copyButton: Button = findViewById(R.id.copybutton2)
        val imageView4: ImageView = findViewById(R.id.imageView4)
        val textInputEditText: TextInputEditText = findViewById(R.id.textInputEditText)
        val textView2: TextView = findViewById(R.id.textView2)

        encryptButton.setOnClickListener {
            val textToEncrypt = textInputEditText.text.toString()
            val encryptedText = RailFenceCipher.encrypt(textToEncrypt, 3) // Change the depth value as needed
            textView2.text = encryptedText
        }

        copyButton.setOnClickListener {
            val encryptedText = textView2.text.toString()

            // Copy to clipboard
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
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