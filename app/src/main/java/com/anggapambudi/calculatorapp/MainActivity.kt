package com.anggapambudi.calculatorapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.graphics.toColorInt
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switchDarkMode.onClick {
            if (switchDarkMode.isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        //nomor
        text1.onClick { appendOnExpressions("1", true) }
        text2.onClick { appendOnExpressions("2", true) }
        text3.onClick { appendOnExpressions("3", true) }
        text4.onClick { appendOnExpressions("4", true) }
        text5.onClick { appendOnExpressions("5", true) }
        text6.onClick { appendOnExpressions("6", true) }
        text7.onClick { appendOnExpressions("7", true) }
        text8.onClick { appendOnExpressions("8", true) }
        text9.onClick { appendOnExpressions("9", true) }
        text0.onClick { appendOnExpressions("0", true) }
        textKoma.onClick { appendOnExpressions(".", true) }

        //operator
        textPlus.onClick { appendOnExpressions(" + ", false) }
        textMin.onClick { appendOnExpressions(" - ", false) }
        textKali.onClick { appendOnExpressions(" * ", false) }
        textBagi.onClick { appendOnExpressions(" / ", false) }
        textOpen.onClick { appendOnExpressions("(", false) }
        textClose.onClick { appendOnExpressions(")", false) }


        textClear.onClick {
            tvExpressions.text = ""
            tvResult.text = ""
        }

        backSpaceButton.onClick {
            val string = tvExpressions.text.toString()
            if (string.isNotEmpty()) {
                tvExpressions.text = string.substring(0, string.length-1)
            }
            tvResult.text = ""
        }

        textEquals.onClick {
            try {

                val expression = ExpressionBuilder(tvExpressions.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", " message : " + e.message)
            }
        }

    }

    fun appendOnExpressions(string: String, canClear: Boolean) {

        if (tvResult.text.isNotEmpty()) {
            tvExpressions.text = ""
        }

        if (canClear) {
            tvResult.text = ""
            tvExpressions.append(string)
        } else {
            tvExpressions.append(tvResult.text)
            tvExpressions.append(string)
            tvResult.text = ""
        }
    }
}