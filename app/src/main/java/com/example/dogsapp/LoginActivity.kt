package com.example.dogsapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.dogsapp.databinding.EntryBinding
import com.example.dogsapp.ui.login.LoginFragment


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: EntryBinding
    private lateinit var button: Button
    private lateinit var text: TextView

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = EntryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        button = binding.button
        text = binding.textView

        supportActionBar?.hide()
    }

    fun loginPage(view: View) {
        text.isVisible = false

        val fragment = LoginFragment()
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()

        button.setOnClickListener {
            fragment.entry()
        }
    }
}
