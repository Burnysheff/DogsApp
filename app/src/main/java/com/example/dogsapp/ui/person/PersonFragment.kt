package com.example.dogsapp.ui.person

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dogsapp.CourseActivity
import com.example.dogsapp.databinding.FragmentPersonBinding
import com.example.dogsapp.sender.Sender
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


class PersonFragment : Fragment() {

    private var _binding: FragmentPersonBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var list: LinearLayout
    private lateinit var texter: TextView

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPersonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        list = binding.line
        texter = binding.textView

        fill()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun course(n: JsonNode) {
        val main = Intent(super.getContext(), CourseActivity::class.java)
        main.putExtra("title", n["title"].toString())
        main.putExtra("text", n["text"].toString())
        main.putExtra("username", n["user"]["username"].toString())
        main.putExtra("email", n["user"]["email"].toString())
        main.putExtra("theme", n["theme"][0]["title"].toString().drop(1).dropLast(1))
        main.putExtra("url", n["preview"][0]["image"].toString().drop(1).dropLast(1))
        startActivity(main)
    }

    @SuppressLint("InflateParams", "SetTextI18n")
    private fun fill() {
        val objectMapper = jacksonObjectMapper()

        Thread {
            val bd = Sender.getAll().body().string()
            val jsonBody = objectMapper.readValue(bd, JsonNode::class.java)

            activity?.runOnUiThread {
                for (course in jsonBody) {
                    if (course["preview"][0]["image"].toString().length < 50) {
                        continue
                    }
                    val image = ImageView(this.context)
                    var loader = course["preview"][0]["image"].toString()
                    loader = loader.drop(1)
                    loader = loader.dropLast(1)
                    Glide.with(this)
                        .load(loader)
                        .override(300, 350)
                        .dontAnimate()
                        .centerCrop()
                        .into(image)

                    val text = TextView(this.context)
                    text.text = course["title"].toString()
                    text.layoutParams = ViewGroup.LayoutParams(300, 170)

                    val lay = LinearLayout(this.context)
                    lay.orientation = LinearLayout.VERTICAL
                    lay.addView(image)
                    lay.addView(text)
                    lay.layoutParams = ViewGroup.LayoutParams(310, 530)

                    lay.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(v: View) {
                            course(course)
                        }
                    })

                    list.addView(lay)
                }
            }
        }.start()
    }
}