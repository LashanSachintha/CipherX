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

class ViginereCipher : AppCompatActivity() {

    private val key = "NIWATHARINDUPATHUM"

    private val alphabet = ('A'..'Z').toList()

    fun encrypt(plaintext: String): String {
        val processedPlaintext = preprocess(plaintext)
        val encryptedText = StringBuilder()

        for (i in processedPlaintext.indices) {
            val plaintextChar = processedPlaintext[i]
            val keyChar = key[i % key.length]
            val encryptedChar = if (plaintextChar.isLetter()) {
                encryptChar(plaintextChar, keyChar)
            } else {
                plaintextChar
            }
            encryptedText.append(encryptedChar)
        }

        return encryptedText.toString()
    }

    private fun preprocess(text: String): String {
        return text.filter { it.isLetter() || it.isWhitespace() }.toUpperCase()
    }

    private fun encryptChar(plaintextChar: Char, keyChar: Char): Char {
        val plaintextIndex = alphabet.indexOf(plaintextChar)
        val keyIndex = alphabet.indexOf(keyChar)
        return alphabet[(plaintextIndex + keyIndex) % alphabet.size]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encryption_layout)

        val encriptButton: Button = findViewById(R.id.encriptbutton)
        val copyButton: Button = findViewById(R.id.copybutton2)
        val imageView4: ImageView = findViewById(R.id.imageView4)
        val textInputEditText: TextInputEditText = findViewById(R.id.textInputEditText)
        val textView2: TextView = findViewById(R.id.textView2)

        encriptButton.setOnClickListener {
            val textToEncrypt = textInputEditText.text.toString()
            val encryptedText = encrypt(textToEncrypt)
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