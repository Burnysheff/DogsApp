package com.example.dogsapp.ui.registration

import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.dogsapp.LoginActivity
import com.example.dogsapp.MainActivity
import com.example.dogsapp.R
import com.example.dogsapp.databinding.FragmentRegistrationBinding
import com.example.dogsapp.sender.Sender
import com.example.dogsapp.ui.login.LoginFragment
import kotlin.math.log

class RegistrationFragment: Fragment() {
    private var _binding: FragmentRegistrationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var login: TextView
    private lateinit var checker: CheckBox

    private lateinit var name: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var repeater: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        login = binding.backLogin
        checker = binding.checkBox

        name = binding.nameReg
        email = binding.regEmail
        password = binding.regPassword
        repeater = binding.regPasswordRepeat

        val linkTextView = login
        linkTextView.movementMethod = LinkMovementMethod.getInstance()
        linkTextView.setOnClickListener {
            getLogin()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getLogin() {
        val fragment = LoginFragment()
        val manager = requireActivity().supportFragmentManager
        manager.beginTransaction().replace(R.id.container, fragment).commit()

        val button: Button? = activity?.findViewById<Button>(R.id.button)
        button?.setOnClickListener { fragment.entry() }
    }

    fun entry() {
        Thread {
            val checker = Sender.reg(name.text, password.text, email.text)
            if (checker) {
                val main = Intent(super.getContext(), MainActivity::class.java)
                startActivity(main)
            }
        }.start()
    }
}
