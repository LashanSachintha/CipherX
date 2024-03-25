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
import java.util.*


class RowColumnDecrypt : AppCompatActivity() {

    private object RailFenceCipher {
        fun decrypt(cipher: String, key: Int): String {
            val rail = Array(key) { CharArray(cipher.length) }
            var dirDown = false
            var row = 0

            for (i in cipher.indices) {
                if (row == 0 || row == key - 1) {
                    dirDown = !dirDown
                }

                rail[row][i] = '\n'
                row += if (dirDown) 1 else -1
            }

            var index = 0
            for (i in 0 until key) {
                for (j in cipher.indices) {
                    if (rail[i][j] == '\n' && index < cipher.length) {
                        rail[i][j] = cipher[index++]
                    }
                }
            }

            var result = ""
            row = 0
            dirDown = false
            for (i in cipher.indices) {
                if (row == 0 || row == key - 1) {
                    dirDown = !dirDown
                }

                if (rail[row][i] != '\n') {
                    result += rail[row][i]
                }

                row += if (dirDown) 1 else -1
            }

            return result
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.railfense_decryption_layout)

        val decryptButton: Button = findViewById(R.id.decriptbutton)
        val copyButton: Button = findViewById(R.id.copybutton2)
        val imageView4: ImageView = findViewById(R.id.imageView4)
        val textInputEditText: TextInputEditText = findViewById(R.id.textInputEditText)
        val textView2: TextView = findViewById(R.id.textView2)
        val Depth: TextInputEditText = findViewById(R.id.textInputEditText2)

        decryptButton.setOnClickListener {
            val Depth = Depth.text.toString().toIntOrNull()

            if (Depth == null) {
                showToast("Depth value can not be empty")
            } else {
                val textToDecrypt = textInputEditText.text.toString()
                val decryptedText = RailFenceCipher.decrypt(textToDecrypt, Depth) // Change the depth value as needed
                textView2.text = decryptedText
            }

        }

        copyButton.setOnClickListener {
            val decryptedText = textView2.text.toString()

            // Copy to clipboard
            val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Decrypted Text", decryptedText)
            clipboardManager.setPrimaryClip(clipData)

            // Show a toast indicating successful copy
            Toast.makeText(this, "Decrypted text copied to clipboard", Toast.LENGTH_SHORT).show()
        }
        imageView4.setOnClickListener {
            onBackPressed() // Go back to the previous page
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}