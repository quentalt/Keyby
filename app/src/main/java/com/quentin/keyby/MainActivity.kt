package com.quentin.keyby

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.preference.PreferenceManager
import com.quentin.keyby.R


class MainActivity : AppCompatActivity() {

    private lateinit var themeRadioGroup: RadioGroup
    private lateinit var radioLight: RadioButton
    private lateinit var radioDark: RadioButton
    private lateinit var applyButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        themeRadioGroup = findViewById(R.id.themeRadioGroup)
        radioLight = findViewById(R.id.radioLight)
        radioDark = findViewById(R.id.radioDark)
        applyButton = findViewById(R.id.applyButton)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val isDarkTheme = sharedPreferences.getBoolean("isDarkTheme", false)
        if (isDarkTheme) {
            radioDark.isChecked = true
        } else {
            radioLight.isChecked = true
        }

        applyButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("isDarkTheme", radioDark.isChecked)
            editor.apply()
        }
    }
}
