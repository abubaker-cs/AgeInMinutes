package org.abubaker.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import org.abubaker.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    // Binding Object
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // It will load activity_main.xml view
        // setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

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

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                // Toast.makeText(
                //    this,
                //    "The chosen year is $selectedYear, the month is $selectedMonth and the day is $selectedDayOfMonth",
                //    Toast.LENGTH_LONG

                // ).show()

                // Main Logic - Convert date in mintues
                val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
                binding.tvSelectedDate.text = selectedDate

                /**
                 * SimpleDateFormat() is a concrete class for formatting and parsing dates in a locale-sensitive manner.
                 * It allows for formatting (date → text), parsing (text → date), and normalization.
                 * URL: https://developer.android.com/reference/java/text/SimpleDateFormat
                 */
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)

                /**
                 * From Milliseconds:
                 *
                 * seconds / 1000
                 * minutes /60000
                 *
                 */

                // !! = Force
                val selectedDateInMinutes = theDate!!.time / 60000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000

                // Calculate Difference
                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes
                val differenceInDays = differenceInMinutes / (60 * 24)

                /**
                 * After getting the difference of dates in MilliSeconds divide it by below variables
                 * to get the respective values.
                 *
                 * val seconds = 1000
                 * val minutes = 1000 * 60
                 * val hours = 1000 * 60 * 60
                 * val days = 1000 * 60 * 60 * 24
                 *
                 * Correct:
                 * difference / (60 * 24)
                 */

                binding.tvSelectedDateInMinutes.text = differenceInMinutes.toString()
                binding.tvSelectedDateInDays.text = differenceInDays.toString()


            },
            year,
            month,
            day
        )

        /**
         * We will disable selection of future dates by using datePicker.setMaxDate()
         */

        // 86400000 = milliseconds of 1 day
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }

}