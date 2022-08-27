package com.sariaydinalparslan.countrylist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sariaydinalparslan.countrylist.R
import com.sariaydinalparslan.countrylist.model.Country
import com.sariaydinalparslan.countrylist.util.downloadfromUrl
import com.sariaydinalparslan.countrylist.util.placeholderProgressBar
import com.sariaydinalparslan.countrylist.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.fragment_country.view.*
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CViewHolder>() {

    class CViewHolder(val view: View) : RecyclerView.ViewHolder(view){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country,parent,false)
        return CViewHolder(view)
    }
    override fun onBindViewHolder(holder: CViewHolder, position: Int) {
        holder.view.feedName.text= countryList[position].countryName
        holder.view.feedRegion.text= countryList[position].regionName
        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.action12(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageview.downloadfromUrl(countryList[position].imageUrl, placeholderProgressBar(holder.view.context))

    }
    override fun getItemCount(): Int {
        return countryList.size
    }
    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}