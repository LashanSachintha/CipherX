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


class RowColumnDecrypt : AppCompatActivity() {

    private object RowColumnCipher {
        fun rowColumnDecrypt(cipherText: String, key: String): String {
            val numOfColumns = key.length
            val numOfRows = cipherText.length / numOfColumns
            val matrix = Array(numOfRows) { CharArray(numOfColumns) }
            var index = 0

            val sortedKey = key.toCharArray().sorted()

            for (char in sortedKey) {
                val colIndex = key.indexOf(char)
                for (row in 0 until numOfRows) {
                    matrix[row][colIndex] = cipherText[index]
                    index++
                }
            }

            val plainText = StringBuilder()

            for (row in 0 until numOfRows) {
                for (col in 0 until numOfColumns) {
                    plainText.append(matrix[row][col])
                }
            }

            return plainText.toString().trim()
        }




    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.row_column_decryption_layout)

        val encryptButton: Button = findViewById(R.id.encriptbutton)
        val copyButton: Button = findViewById(R.id.copybutton2)
        val imageView4: ImageView = findViewById(R.id.imageView4)
        val textInputEditText: TextInputEditText = findViewById(R.id.textInputEditText)
        val textView2: TextView = findViewById(R.id.textView2)
        val key: TextInputEditText = findViewById(R.id.textInputEditText2)

        encryptButton.setOnClickListener {
            val key = key.text.toString()

            if (key == null) {
                showToast("Depth value can not be empty")
            } else {
                val textToEncrypt = textInputEditText.text.toString()
                val encryptedText = RowColumnCipher.rowColumnDecrypt(textToEncrypt, key)
                textView2.text = encryptedText
            }


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
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}