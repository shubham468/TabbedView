package com.tech.tabbedviewtest

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.tech.tabbedviewtest.Util.NetworkCheck
import com.tech.tabbedviewtest.databinding.ActivityLoginBinding
import com.tech.umr.Util.prefs

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signup.text = "Not have an account, signup now"
        binding.signup.setOnClickListener {
            val intent = Intent(this.applicationContext, SignupActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
            NetworkCheck.hideKeyboardFrom(this, it)
            if (validator()) {
                val email = binding.tvEmailText.editText?.text.toString()
                val pass = binding.tvPasswordText.editText?.text.toString()

                if (email == prefs.emailId && pass == prefs.pass) {
                    binding.progressCircular.progressCircular.visibility = View.GONE

                    Toast.makeText(this, "Welcome ${prefs.name}", Toast.LENGTH_LONG).show()
                    val intent = Intent(this.applicationContext, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                } else {
                    binding.progressCircular.progressCircular.visibility = View.GONE
                    val snak =
                        Snackbar.make(binding.root, "Invalid Id or Password", Snackbar.LENGTH_SHORT)
                    snak.setTextColor(Color.RED)
                    snak.show()
                }
            }
        }

        if (prefs.emailId.isNullOrEmpty() && prefs.pass.isNullOrEmpty()) {
            binding.test.visibility = View.GONE
        } else {
            binding.test.visibility = View.VISIBLE
        }


        binding.test.setOnClickListener {
            binding.tvEmailText.editText?.setText(prefs.emailId)
            binding.tvPasswordText.editText?.setText(prefs.pass)

        }

    }

    private fun validator(): Boolean {
        if (binding.tvEmailText.editText?.text.toString() == "") {
            binding.tvEmailText.error = "Please enter email"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(binding.tvEmailText.editText?.text.toString())
                .matches()
        ) {
            binding.tvEmailText.error = "Please enter valid email"
            return false
        } else if (binding.tvPasswordText.editText?.text.toString() == "") {
            binding.tvPasswordText.error = "Please enter password"
            return false
        }/* else if (binding.tvPasswordText.editText.toString().length > 4) {
            binding.tvPasswordText.error = "Please enter valid password"
            return false
        }*/
        return true
    }

//    fun AppCompatActivity.hideKeyboard() {
//        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
//    }

//    fun Fragment.hideKeyboard() {
//        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
//    }
}