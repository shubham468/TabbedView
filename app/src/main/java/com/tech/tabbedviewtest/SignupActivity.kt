package com.tech.tabbedviewtest

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tech.tabbedviewtest.databinding.ActivitySignupBinding
import com.tech.umr.Util.prefs

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    private val handler by lazy {
        Handler(Looper.myLooper()!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.signupButton.setOnClickListener {

            if (validator()) {
                binding.progressCircular.progressCircular.visibility = View.VISIBLE
                handler.postDelayed({

                }, 1500)

                prefs.emailId = binding.tvEmailText.editText?.text.toString()
                prefs.pass = binding.tvPasswordText.editText?.text.toString()
                prefs.mobile = binding.tvMobile.editText?.text.toString()
                binding.progressCircular.progressCircular.visibility = View.GONE
                val intent = Intent(this.applicationContext, Login::class.java)
                startActivity(intent)
            }


        }


    }


    private fun validator(): Boolean {
        binding.tvMobile.error = ""
        binding.tvCPasswordText.error = ""
        binding.tvEmailText.error = ""
        binding.tvPasswordText.error = ""


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
        } else if (binding.tvCPasswordText.editText?.text.toString() != binding.tvPasswordText.editText?.text.toString()) {
            binding.tvCPasswordText.error = "Password mismatch"
            return false
        } else if (binding.tvMobile.editText.toString() == "") {
            binding.tvMobile.error = "Please enter mobile no"
            return false
        } else if (binding.tvMobile.editText.toString().length in 10..12) {
            binding.tvMobile.error = "Please enter valid mobile no"
            return false
        }
        return true
    }
}