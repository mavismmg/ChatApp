package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_register)

        button_registerActivity_btt.setOnClickListener {
            val email = email_registerActivity_txt.text.toString()
            val password = password_registerActivity_txt.text.toString()

            Log.d("RegisterActivity", "Email is: $email")
            Log.d("RegisterActivity", "Password: $password")
        }

        already_have_account.setOnClickListener {
            Log.d("RegisterActivity", "Try to show login activity")

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}