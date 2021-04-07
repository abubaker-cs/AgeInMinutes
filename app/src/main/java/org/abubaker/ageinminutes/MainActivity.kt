package org.abubaker.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import org.abubaker.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    // Binding Object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // It will load activity_main.xml view
        setContentView(R.layout.activity_main)

        // Select Button from XML file
        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        // onClick Event
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }

    }

    // DatePicker
    fun clickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH) // Caution: Month starts form index-0
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(
                    this,
                    "The chosen year is $selectedYear, the month is $selectedMonth and the day is $selectedDayOfMonth",
                    Toast.LENGTH_LONG

                ).show()

                // Main Logic - Convert date in mintues
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                binding.tvSelectedDate.setText(selectedDate)

                /**
                 * SimpleDateFormat() is a concrete class for formatting and parsing dates in a locale-sensitive manner.
                 * It allows for formatting (date → text), parsing (text → date), and normalization.
                 * URL: https://developer.android.com/reference/java/text/SimpleDateFormat
                 */
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)


            },
            year,
            month,
            day
        ).show()
    }


}