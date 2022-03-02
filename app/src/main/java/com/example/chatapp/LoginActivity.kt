package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_login)

        button_loginActivity_btt.setOnClickListener {
            val username = username_loginActivity_txt.text.toString()
            // Logic pick firebase db password for desired username.

            Log.d("LoginActivity", "Username: $username")
        }

        have_account_login.setOnClickListener {
            Log.d("LoginActivity", "User already have an acount")

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}