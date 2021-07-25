package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.calculatorapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    To activate ViewBinding
    private lateinit var binding: ActivityMainBinding

    var lastNumeric : Boolean =false
    var lastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//    To activate ViewBinding
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun onDigit(view:View){
        binding.tvInput.append((view as Button).text)
        lastNumeric=true
    }
    fun onClear(view : View){
        binding.tvInput.setText("")
        lastNumeric=false
        lastDot=false
    }

    fun onDecimalPoint(view : View){
        if (lastNumeric && !lastDot){
            binding.tvInput.append(".")
            lastNumeric=false
            lastDot=true
        }
    }

    fun onOperator(view : View){
         if(lastNumeric && !isOperatorAdded(binding.tvInput.text.toString())){
             binding.tvInput.append((view as Button).text)
             lastNumeric=false
             lastDot=false
         }
    }

    private fun isOperatorAdded(value : String) : Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("+") || value.contains("-") || value.contains("*") || value.contains("/")
        }
    }

}