package com.jokosupriyanto.filmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.lifecycle.Observer
import com.jokosupriyanto.filmapp.ui.main.ViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val viewModel by lazy {ViewModel()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setClickListener()
        observeViewModel()
    }

    private fun setClickListener() {
        findViewById<SearchView>(R.id.search_movie).setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i(TAG, "onQueryTextSubmit: Submit")
                if (query.toString().trim().isNotEmpty()) viewModel.fetchDetails(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun observeViewModel() {
        viewModel.getDetail().observe(this, Observer {
            it?.forEach {
                Log.i(TAG, "observeViewModel: $it")
            }
        })


    }
}