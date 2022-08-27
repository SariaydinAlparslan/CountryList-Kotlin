package com.sariaydinalparslan.countrylist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.sariaydinalparslan.countrylist.R
import com.sariaydinalparslan.countrylist.util.downloadfromUrl
import com.sariaydinalparslan.countrylist.util.placeholderProgressBar
import com.sariaydinalparslan.countrylist.viewmodel.CountryViewModel
import com.sariaydinalparslan.countrylist.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_country.*

class CountryFragment : Fragment() {
    private var countryUuid = 0
    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid =CountryFragmentArgs.fromBundle(it).countryUuid

        }
        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)


        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            country ->country?.let {
                textName.text = country.countryName
                textCapital.text = country.capitalName
                textCurrency.text = country.currencyName
                textLanguage.text = country.languageName
                textRegion.text = country.regionName
            context?.let{
                countryImage.downloadfromUrl(country.imageUrl, placeholderProgressBar(it))
            }


        }
        })

    }

}