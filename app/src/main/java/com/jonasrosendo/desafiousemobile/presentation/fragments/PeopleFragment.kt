package com.jonasrosendo.desafiousemobile.presentation.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.jonasrosendo.desafiousemobile.R
import com.jonasrosendo.desafiousemobile.presentation.adapters.UsersAdapter
import com.jonasrosendo.desafiousemobile.presentation.viewmodels.UserViewModel
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator

class PeopleFragment : Fragment(), TextWatcher {

    private val viewModel: UserViewModel by viewModels()
    private val peopleAdapter = UsersAdapter(arrayListOf())
    private lateinit var usersRecycler: RecyclerView
    private lateinit var tvError: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var searchBoxLayout: LinearLayout
    private lateinit var btRefresh: Button
    private lateinit var etSearch: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_people, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.pb_people)
        usersRecycler = view.findViewById(R.id.rv_people)
        tvError = view.findViewById(R.id.tv_people_error)
        searchBoxLayout = view.findViewById(R.id.ll_search_box)
        btRefresh = view.findViewById(R.id.bt_try_again)
        etSearch = view.findViewById(R.id.et_search)

        viewModel.getAllUsers()
        observeViewModel()

        usersRecycler.apply {
            adapter = peopleAdapter
            itemAnimator = SlideInUpAnimator().apply {
                addDuration = 300
            }
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        }

        btRefresh.setOnClickListener { viewModel.getAllUsers() }
        etSearch.addTextChangedListener(this)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val query: String? = etSearch.text.toString().trim()
        query?.let {
            if (it.isEmpty()) viewModel.getAllUsersOnLocalDatabase() else viewModel.getAllUsers(it)
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }

    private fun observeViewModel() {
        viewModel.users.observe(viewLifecycleOwner, {
            peopleAdapter.update(it.response)
        })

        viewModel.isUsersLoading.observe(viewLifecycleOwner, {
            onLoading(it)
        })

        viewModel.hasError.observe(viewLifecycleOwner, {
            onError(it)
        })

        viewModel.isDeviceConnected.observe(viewLifecycleOwner, {
            usersRecycler.visibility = if (!it)  View.GONE else View.VISIBLE
            onConnectionChecked(it)
        })
    }

    private fun onLoading(show: Boolean) {
        showProgressBar(show)
    }

    private fun onConnectionChecked(isConnected: Boolean) {
        onConnectionUnavailable(isConnected)
    }

    private fun onError(hasError: Boolean) {
        showUsersRecycler(!hasError)
        showProgressBar(!hasError)
        showErrorText(hasError)
    }

    private fun showUsersRecycler(show: Boolean) {
        usersRecycler.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showSearchBox(show: Boolean) {
        searchBoxLayout.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showErrorText(show: Boolean) {
        tvError.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showRefreshButton(show: Boolean) {
        btRefresh.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun onConnectionUnavailable(isConnected: Boolean) {
        tvError.visibility = if (isConnected) View.GONE else View.VISIBLE
        showRefreshButton(!isConnected)
        showSearchBox(isConnected)
        tvError.text = getString(R.string.no_connection)
    }
}