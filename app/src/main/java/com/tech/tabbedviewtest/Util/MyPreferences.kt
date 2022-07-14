package com.tech.umr.Util

import android.content.Context
import android.content.SharedPreferences

class MyPreferences(applicationContext: Context) {
    private val APP_PREF = "MySharedPref"
    private val email="email"
    private val id="id"
    private val username="username"
    private val tokent="token"
    private val mobileno="mobile"
    private val password="pass"

    private val imageProfile="profileimage"


    private val preferences: SharedPreferences =
        applicationContext.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)


    var emailId: String?
        get() = preferences.getString(email, "")
        set(value) = preferences.edit().putString(email, value).apply()

    var name: String?
        get() = preferences.getString(username, "")
        set(value) = preferences.edit().putString(username, value).apply()
    var profileImage: String?
        get() = preferences.getString(imageProfile, "")
        set(value) = preferences.edit().putString(imageProfile, value).apply()
    var mobile: String?
        get() = preferences.getString(mobileno, "")
        set(value) = preferences.edit().putString(mobileno, value).apply()

    var pass: String?
        get() = preferences.getString(password, "")
        set(value) = preferences.edit().putString(password, value).apply()

    var token: String?
        get() = preferences.getString(tokent, "")
        set(value) = preferences.edit().putString(tokent, value).apply()


    var userId: String?
        get() = preferences.getString(id,"")
        set(value) = preferences.edit().putString(id, value).apply()


    fun clearMyPreferences(context: Context) {
        val sharedPreferences =
            context.getSharedPreferences(APP_PREF, Context.MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()
        myEdit.putString(token, "")
        myEdit.putString(mobile, "")
        myEdit.putString(userId, "")
        myEdit.putString(emailId, "")
        myEdit.putString(name, "")
        myEdit.putString(profileImage, "")
        myEdit.clear()
        myEdit.commit()
    }
}