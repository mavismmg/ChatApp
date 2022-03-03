package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        FirebaseApp.initializeApp(applicationContext)

        emailMatchesVerification()

        button_registerActivity_btt.setOnClickListener {
            userRegister()
        }

        already_have_account.setOnClickListener {
            Log.d("RegisterActivity", "Try to show login activity")

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun emailMatchesVerification() : Unit {
        email_registerActivity_txt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(matchEmailVerification: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Logic here
            }

            override fun onTextChanged(matchEmailVerification: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Logic here
            }

            override fun afterTextChanged(matchEmailVerification: Editable?) {
                if (android.util.Patterns.EMAIL_ADDRESS.matcher((email_registerActivity_txt.text.toString())).matches()) {
                    Log.d("Login", "E-mail matches")
                }
                else {
                    Log.d("Login", "Input e-mail invalid")
                    email_registerActivity_txt.error = "Invalid e-mail"
                }
            }

        })
    }

    private fun userRegister() : Unit {
        val email = email_registerActivity_txt.text.toString()
        val password = password_registerActivity_txt.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please provide a e-mail and password", Toast.LENGTH_SHORT).show()

            return
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Log.d("Register", "Successfully created user with uid: ${it.result.user!!.uid}")
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    return@addOnCompleteListener
                }
            }
            .addOnFailureListener {
                Log.d("Register", "Failed to create user: ${it.message}")

                when (it.message) {
                    "The email address is badly formatted." -> {
                        Toast.makeText(this, "Please provide a valid e-mail", Toast.LENGTH_SHORT).show()
                    }
                    "The given password is invalid. [ Password should be at least 6 characters ]" -> {
                        Toast.makeText(
                            this,
                            "Password should be at least 6 characters",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        Toast.makeText(
                            this,
                            "Please provide a valid e-mail or password",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
    }
}