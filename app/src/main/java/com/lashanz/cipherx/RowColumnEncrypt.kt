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
import kotlin.math.ceil


class RowColumnEncrypt : AppCompatActivity() {

    private object RowColumnCipher {
        fun rowColumnTranspositionEncrypt(plainText: String, key: String): String {
            val columns = key.length
            val rows = (plainText.length + columns - 1) / columns
            val matrix = Array(rows) { CharArray(columns) { ' ' } }

            // Fill the matrix with the plain text
            plainText.forEachIndexed { index, c ->
                matrix[index / columns][index % columns] = c
            }

            // Generate the output
            val output = StringBuilder()
            key.map { it.toString().toInt() }.forEach { column ->
                for (row in 0 until rows) {
                    output.append(matrix[row][column])
                }
            }

            return output.toString()
        }






    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.row_column_encryption_layout)

        val encryptButton: Button = findViewById(R.id.encriptbutton)
        val copyButton: Button = findViewById(R.id.copybutton2)
        val imageView4: ImageView = findViewById(R.id.imageView4)
        val textInputEditText: TextInputEditText = findViewById(R.id.textInputEditText)
        val textView2: TextView = findViewById(R.id.textView2)
        val key: TextInputEditText = findViewById(R.id.textInputEditText2)

        encryptButton.setOnClickListener {
            val keyText = key.text.toString()
            val textToEncrypt = textInputEditText.text.toString()
            val encryptedText = RowColumnCipher.rowColumnTranspositionEncrypt(textToEncrypt, keyText)
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
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}