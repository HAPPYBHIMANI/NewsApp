package com.app.newsapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.BuildConfig
import androidx.viewbinding.ViewBinding
import com.app.newsapp.base.ViewModelFactory
import com.app.newsapp.network.RemoteDataSource
import com.app.newsapp.repository.BaseRepository
import com.app.newsapp.utils.PreferenceUtils
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Matcher
import java.util.regex.Pattern

abstract class BaseActivity<VM : ViewModel, B : ViewBinding, R : BaseRepository> :
    AppCompatActivity() {

    protected lateinit var preferenceUtils: PreferenceUtils

    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected val remoteDataSource = RemoteDataSource()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        preferenceUtils = PreferenceUtils(this)
        binding = getActivityBinding(layoutInflater)
        val factory = ViewModelFactory(getActivityRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
//        return binding.root
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getActivityBinding(inflater: LayoutInflater): B

    abstract fun getActivityRepository(): R

    open fun logError(tag: String, message: String) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, message)
        }
    }

    open fun showSnackBar(message: String, view: View) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        snackbar.show()

    }

    open fun showToast(message: String) {
        Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show()
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

    open fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val matcher: Matcher
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        matcher = pattern.matcher(password)
        return matcher.matches()
    }

    open fun isMobileValid(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches()
    }

    open fun hideKeyBoard(view: View?) {
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}