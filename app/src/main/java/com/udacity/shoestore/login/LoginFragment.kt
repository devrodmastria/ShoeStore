package com.udacity.shoestore.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.shoe_gallery.ShoeListViewModel

class LoginFragment : Fragment() {

    private val sharedViewModel by activityViewModels<ShoeListViewModel>()
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel

        // returning users can skip login and onboarding
        sharedViewModel.loginCompleted.observe(viewLifecycleOwner) { loginCompleted ->
            if (loginCompleted){
                skipOnboarding()
            }
        }

        // alternative way to skip login
//        binding.button.setOnClickListener {
//            if (sharedViewModel.loginCompleted.value == true) {
//                skipOnboarding()
//            }
//        }

        viewModel.loginCorrect.observe(viewLifecycleOwner) { validLogin ->

            if (validLogin) {
                startOnboarding()
            } else {
                Toast.makeText(binding.root.context, "Invalid login", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.input_email.value = binding.editEmailAddress.text.toString()

        return binding.root
    }

    private fun startOnboarding(){
        val action = LoginFragmentDirections.actionLoginFragmentToOnboardingFragment(viewModel.user_email.toString())
        findNavController().navigate(action)
    }

    private fun skipOnboarding(){
        val action = LoginFragmentDirections.actionLoginFragmentToShoeListFragment()
        findNavController().navigate(action)
    }

}