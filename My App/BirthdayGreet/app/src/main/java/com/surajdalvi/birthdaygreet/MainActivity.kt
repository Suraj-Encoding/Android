package com.surajdalvi.birthdaygreet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createBirthdayCard(view: View) {

        // Extract name from nameInput
        //  val name = nameInput.editableText.toString()  // Gives an error so use another method
        val name= findViewById<EditText>(R.id.nameInput) .editableText.toString()

        Toast.makeText(this, "Creating Birthday Card",Toast.LENGTH_LONG).show()
        // Toast -> Small bar display when button click

        // Open new activity
        val intent = Intent(this, BirthdayGreetingActivity::class.java)
        // intent.putExtra("name",name)
        intent.putExtra(BirthdayGreetingActivity.NAME_EXTRA,name)
        startActivity(intent)

    }


}