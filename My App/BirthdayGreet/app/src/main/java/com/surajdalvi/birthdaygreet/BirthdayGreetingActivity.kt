package com.surajdalvi.birthdaygreet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView


class BirthdayGreetingActivity : AppCompatActivity() {

    // static variables  -> It is always a good practice to use companion object
    companion object{
        const val NAME_EXTRA = "name_extra"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday_greeting)

       // val name = intent.getStringExtra("name")
        val name = intent.getStringExtra(NAME_EXTRA)
        findViewById<TextView>(R.id.birthdayGreeting). text = "Happy Birthday\n$name!🎂"
    }
}