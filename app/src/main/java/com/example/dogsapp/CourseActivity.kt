package com.example.dogsapp

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dogsapp.databinding.CourseBinding
import com.example.dogsapp.ui.autor.AuthorFragment
import com.example.dogsapp.ui.plan.PlanFragment

class CourseActivity : AppCompatActivity()  {
    private lateinit var binding: CourseBinding

    private lateinit var title: TextView
    private lateinit var theme: TextView
    private lateinit var author: TextView
    private lateinit var plan: TextView
    private lateinit var click: Button
    private lateinit var image: ImageView

    private lateinit var username: String
    private lateinit var email: String
    private lateinit var video: String
    private lateinit var text: String

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        author = binding.author
        plan = binding.plan
        click = binding.click
        image = binding.imageView2
        title = binding.textView6
        theme = binding.textView7

        val extras = intent.extras
        val url = extras!!.getString("url")

        Glide.with(this)
            .load(url)
            .override(300, 350)
            .dontAnimate()
            .centerCrop()
            .into(image)
        title.text = extras.getString("title")
        theme.text = extras.getString("theme")
        username = extras.getString("username")!!
        email = extras.getString("email")!!
        video = extras.getString("url")!!
        text = extras.getString("text")!!

        val fragment = PlanFragment()
        supportFragmentManager.beginTransaction().add(R.id.course, fragment).commit()
        plan.textSize = 18F

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

        click.setOnClickListener {
         click()
        }

        supportActionBar?.hide()
    }

    private fun author() {
        author.textSize = 18F
        plan.textSize = 16F

        val bundle = Bundle()
        bundle.putString("username", username)
        bundle.putString("email", email)
        val fragment = AuthorFragment()
        fragment.arguments = bundle
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

        main.putExtra("title", title.text)
        main.putExtra("url", video)
        main.putExtra("text", text)

        startActivity(main)
    }
}