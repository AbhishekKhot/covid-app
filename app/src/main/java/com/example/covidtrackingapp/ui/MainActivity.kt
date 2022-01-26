package com.example.covidtrackingapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtrackingapp.R
import com.example.covidtrackingapp.adapter.CovidDataAdapter
import com.example.covidtrackingapp.model.Regional
import com.example.covidtrackingapp.other.Resource
import com.example.covidtrackingapp.repository.CovidRepository
import com.example.covidtrackingapp.viewmodel.CovidViewModel
import com.example.covidtrackingapp.viewmodel.CovidViewModelProviderFactory
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    lateinit var rvAdapter:CovidDataAdapter
    lateinit var tempArrayList:ArrayList<Regional>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title="Search here..."

        tempArrayList = arrayListOf<Regional>()



        val repository = CovidRepository()
        val viewModelProviderFactory = CovidViewModelProviderFactory(repository)
        val viewModel = ViewModelProvider(this,viewModelProviderFactory).get(CovidViewModel::class.java)
        setupRecyclerView()

        viewModel.breakingNews.observe(this, Observer { response ->
            when(response){
                is Resource.Success -> {
                    progressBar.visibility = View.INVISIBLE
                    response.data?.let {
                        rvAdapter.differ.submitList(it.data.regional)
                        tempArrayList.addAll(rvAdapter.differ.currentList)
                    }
                }
                is Resource.Error -> {
                    progressBar.visibility = View.INVISIBLE
                    response.message?.let { message ->
                        Toast.makeText(this,"An error occurred: $message",Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
       // searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if(query != null){
            filterRecyclerView(query)
        }
        return true
    }


    private fun filterRecyclerView(text:String){
        val temp: MutableList<Regional> = ArrayList()
        for (d in rvAdapter.differ.currentList) {
            if (d.loc.toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))||d.loc.equals(text)) {
                temp.add(d)
            }
        }
        if(temp.isEmpty()){
            rvAdapter.differ.submitList(rvAdapter.differ.currentList)
        }else{
            rvAdapter.differ.submitList(temp)
        }
    }

    private fun setupRecyclerView() {
        rvAdapter = CovidDataAdapter()
        recyclerView_home.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}