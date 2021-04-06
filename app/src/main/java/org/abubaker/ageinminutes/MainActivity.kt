package org.abubaker.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // It will load activity_main.xml view
        setContentView(R.layout.activity_main)

        // Select Button from XML file
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        // onClick Event
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
            // Toast.makeText(this, "Button works", Toast.LENGTH_LONG).show()
        }


    }

    // DatePicker
    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth -> },
            year,
            month,
            day
        ).show()
    }


}