package com.sariaydinalparslan.countrylist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sariaydinalparslan.countrylist.R
import com.sariaydinalparslan.countrylist.adapter.CountryAdapter
import com.sariaydinalparslan.countrylist.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        countrylist.layoutManager = LinearLayoutManager(context)
        countrylist.adapter = countryAdapter


        swipeRefreshLayout.setOnRefreshListener {
            countrylist.visibility = View.GONE
            feederror.visibility = View.GONE
            feedbar.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            swipeRefreshLayout.isRefreshing =false
        }
        observeLiveData()
    }
    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            countries-> countries?.let {
                countrylist.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
        }
        })
        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            error-> error?.let {
               if (it){
                   feederror.visibility = View.VISIBLE
               }else{
                   feederror.visibility = View.GONE
               }
            }
        })
        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            loading->loading?.let {
                if (it){
                    feedbar.visibility =View.VISIBLE
                    countrylist.visibility = View.GONE
                    feederror.visibility = View.GONE
                }else{
                    feedbar.visibility = View.GONE
                }
             }
        })
    }
}