package com.app.newsapp.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.BuildConfig
import androidx.viewbinding.ViewBinding
import com.app.newsapp.base.ViewModelFactory
import com.app.newsapp.network.RemoteDataSource
import com.app.newsapp.repository.BaseRepository
import com.app.newsapp.utils.PreferenceUtils
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

abstract class BaseFragment<VM : ViewModel, B : ViewBinding, R : BaseRepository> : Fragment() {

    protected lateinit var userPreferences: PreferenceUtils
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    companion object {
        var baseFragment: Context? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = PreferenceUtils(requireContext())
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        baseFragment = requireContext()
        return binding.root
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getFragmentRepository(): R

    /**
     * to show error messages only in Snackbar
     */
    /*  open fun showSnackBar(msg: String?) {
          if (msg == null) return
          Snackbar.make(findViewById<View>(android.R.id.content), msg, Snackbar.LENGTH_LONG).show()
      }*/

    open fun showSnackBar(message: String, view: View) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    open fun showToast(message: String) {
        Toast.makeText(context, "" + message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Check Email is valid or not
     *
     * @param email - email
     * @return - true if email is valid else false
     */
    open fun isEmailValid(email: String?): Boolean {
        val regex =
            "[A-Z0-9a-z]+([._%+-]{1}[A-Z0-9a-z]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        val pattern = Pattern.compile(regex)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }

    open fun hideKeyBoard(view: View?) {
        if (view != null) {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    open fun logError(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }



}