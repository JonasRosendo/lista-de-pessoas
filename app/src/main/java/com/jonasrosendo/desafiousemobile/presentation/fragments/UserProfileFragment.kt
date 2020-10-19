package com.jonasrosendo.desafiousemobile.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jonasrosendo.desafiousemobile.R
import com.jonasrosendo.desafiousemobile.domain.entities.User
import com.jonasrosendo.desafiousemobile.presentation.activity.MainActivity
import com.jonasrosendo.desafiousemobile.presentation.commons.load
import com.jonasrosendo.desafiousemobile.presentation.viewmodels.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_user_profile.*

class UserProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private val args: UserProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserDetails(args.userId)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.user.observe(viewLifecycleOwner, {
            setComponentsValues(it)
            (requireActivity() as MainActivity).supportActionBar?.title = it.name
        })

    }

    private fun setComponentsValues(user: User) {
        iv_user_profile_photo.load(user.photo)
        tv_profile_name.text = user.name
        tv_profile_email.text = user.email
        tv_profile_about.text = user.about
    }
}