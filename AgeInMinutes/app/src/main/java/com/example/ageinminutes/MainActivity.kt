package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Main Code Written Below
        binding.btnDatePicker.setOnClickListener{view ->
            Toast.makeText(this,"Button Works",Toast.LENGTH_LONG).show()
            clickDatePicker(view)
        }
    }
    fun clickDatePicker(view: View){
        val myCalendar= Calendar.getInstance()
        val year= myCalendar.get(Calendar.YEAR)
        val month= myCalendar.get(Calendar.MONTH)
        val day= myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{
                view, selectedYear, selectedMonth, selectedDayOfMonth ->
//                    Logic for Converting Date into AgeInMin
                    val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
                    binding.tvSelectedDate.setText(selectedDate)
                    val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
                    val selDate= sdf.parse(selectedDate)
                    val selDateInMin = selDate!!.time/60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    val currentDateInMin= currentDate!!.time/60000
                    val diffInMin=currentDateInMin-selDateInMin
                    binding.tvSelectedDateInMinutes.setText(diffInMin.toString())

        },year,month,day )
        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}