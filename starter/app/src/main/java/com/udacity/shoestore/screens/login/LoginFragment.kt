package com.udacity.shoestore.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil
            .inflate(inflater, R.layout.login_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventAuth.observe(viewLifecycleOwner) { isSuccess: Boolean ->
            if (isSuccess) {
                onAuthSuccess()
                mainViewModel.onLoginSuccess()
            } else {
                onAuthError()
            }
        }

        binding.loginBtn.setOnClickListener {
            viewModel.onAuth(
                binding.emailField.text.toString(),
                binding.passwordField.text.toString()
            )

        }

        mainViewModel.eventLogin.observe(viewLifecycleOwner) { isLoggedIn: Boolean ->
            if (isLoggedIn) {
                onAuthComplete()
            }
        }
    }

    private fun onAuthSuccess() {
        Toast.makeText(
            requireContext(),
            getString(R.string.message_valid_credentials),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onAuthError() {
        Toast.makeText(
            requireContext(),
            getString(R.string.error_invalid_credentials),
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun onAuthComplete() {
        findNavController()
            .navigate(R.id.action_loginFragment_to_welcomeFragment)
    }

}