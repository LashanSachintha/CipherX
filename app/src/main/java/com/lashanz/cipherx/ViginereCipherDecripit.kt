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

class ViginereCipherDecripit : AppCompatActivity() {

    private val key = "NIWATHARINDUPATHUM"

    private val alphabet = ('A'..'Z').toList()

    fun decrypt(ciphertext: String): String {
        val processedCiphertext = preprocess(ciphertext)
        val decryptedText = StringBuilder()

        for (i in processedCiphertext.indices) {
            val ciphertextChar = processedCiphertext[i]
            val keyChar = key[i % key.length]
            val decryptedChar = if (ciphertextChar.isLetter()) {
                decryptChar(ciphertextChar, keyChar)
            } else {
                ciphertextChar
            }
            decryptedText.append(decryptedChar)
        }

        return decryptedText.toString()
    }

    private fun preprocess(text: String): String {
        return text.filter { it.isLetter() || it.isWhitespace() }.toUpperCase()
    }

    private fun decryptChar(ciphertextChar: Char, keyChar: Char): Char {
        val ciphertextIndex = alphabet.indexOf(ciphertextChar)
        val keyIndex = alphabet.indexOf(keyChar)
        val decryptedIndex = (ciphertextIndex - keyIndex + alphabet.size) % alphabet.size
        return alphabet[decryptedIndex]
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
            val decryptedText = decrypt(textToDecrypt)
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