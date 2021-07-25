package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.calculatorapp.databinding.ActivityMainBinding
import java.lang.ArithmeticException

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

    private fun removeZeroAfterDecimalPoint( value : String) : String{
        var result= value
        if (value.contains(".0")){
            result = value.substring(0, value.length-2)
        }
        return result
    }

    fun onEqual(view : View){
        if(lastNumeric){
            var tvValue = binding.tvInput.text.toString()
            var prefix = ""
            try {
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    var (one,two)=tvValue.split("-")
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    binding.tvInput.text = removeZeroAfterDecimalPoint((one.toDouble()-two.toDouble()).toString())
                }
                else if(tvValue.contains("+")){
                    var (one,two)=tvValue.split("+")
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    binding.tvInput.text = removeZeroAfterDecimalPoint((one.toDouble()+two.toDouble()).toString())
                }
                else if(tvValue.contains("*")){
                    var (one,two)=tvValue.split("*")
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    binding.tvInput.text = removeZeroAfterDecimalPoint((one.toDouble()*two.toDouble()).toString())
                }
                else if(tvValue.contains("/")){
                    var (one,two)=tvValue.split("/")
                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }
                    binding.tvInput.text = removeZeroAfterDecimalPoint((one.toDouble()/two.toDouble()).toString())
                }

            }
            catch (e : ArithmeticException){
                e.printStackTrace()
            }
        }
    }

}