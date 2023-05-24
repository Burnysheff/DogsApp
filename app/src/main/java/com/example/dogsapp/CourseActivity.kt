package com.example.dogsapp

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dogsapp.databinding.CourseBinding
import com.example.dogsapp.databinding.EntryBinding
import com.example.dogsapp.ui.autor.AuthorFragment
import com.example.dogsapp.ui.login.LoginFragment
import com.example.dogsapp.ui.plan.PlanFragment

class CourseActivity : AppCompatActivity()  {
    private lateinit var binding: CourseBinding

    private lateinit var author: TextView
    private lateinit var plan: TextView
    private lateinit var click: Button

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        author = binding.author
        plan = binding.plan
        click = binding.click

        click.setOnClickListener {
            click()
        }

        val authorView = author
        authorView.movementMethod = LinkMovementMethod.getInstance()
        authorView.setOnClickListener {
            author()
        }
        val linkTextView = plan
        linkTextView.movementMethod = LinkMovementMethod.getInstance()
        linkTextView.setOnClickListener {
            plan()
        }

        val fragment = PlanFragment()
        supportFragmentManager.beginTransaction().add(R.id.course, fragment).commit()
        plan.textSize = 18F

        supportActionBar?.hide()
    }

    private fun author() {
        author.textSize = 18F
        plan.textSize = 16F

        val fragment = AuthorFragment()
        supportFragmentManager.beginTransaction().add(R.id.course, fragment).commit()
    }
    private fun plan() {
        plan.textSize = 18F
        author.textSize = 16F

        val fragment = PlanFragment()
        supportFragmentManager.beginTransaction().add(R.id.course, fragment).commit()
    }

    private fun click() {
        val main = Intent(this, ContentActivity::class.java)
        startActivity(main)
    }
}