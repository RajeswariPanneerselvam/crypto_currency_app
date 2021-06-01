package com.app.cryptocurrencyapp.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.app.cryptocurrencyapp.AppController
import com.app.cryptocurrencyapp.CoinViewModel
import com.app.cryptocurrencyapp.R
import com.app.cryptocurrencyapp.adapter.PriceAdapter
import com.app.cryptocurrencyapp.apiservice.ApiClient
import com.app.cryptocurrencyapp.apiservice.ApiInterface
import com.app.cryptocurrencyapp.model.CoinsModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriceFragment : Fragment(){
    var recyclerView: RecyclerView?=null
    var swipeRefresh:SwipeRefreshLayout?=null
    var adapter: PriceAdapter?=null
    var coinsList:ArrayList<CoinsModel.DataModel>?=null
    var edit_search:EditText?=null

    fun PriceFragment() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_price_layout, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinsList=AppController.instance.coinsList

        recyclerView =  view.findViewById(R.id.recycler_coins)
        swipeRefresh=view.findViewById(R.id.swipeRefreshLayout)
        edit_search=view.findViewById(R.id.edit_search)


        recyclerView?.setLayoutManager(LinearLayoutManager(activity))

        recyclerView?.setHasFixedSize(true)

        edit_search?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }
            override fun afterTextChanged(editable: Editable) {
                filter(editable.toString())
            }
        })
        val model: CoinViewModel = ViewModelProviders.of(this).get(CoinViewModel::class.java)

        model.getHeroes().observe(
            requireActivity(),
            Observer<ArrayList<CoinsModel.DataModel>?> { coinList ->


                adapter = PriceAdapter(coinList , requireActivity())
                recyclerView?.setAdapter(adapter)

            })
       // getCoinsList()

        swipeRefresh?.setOnRefreshListener(OnRefreshListener {

            swipeRefresh?.setRefreshing(false)
            adapter = PriceAdapter(coinsList!! , requireActivity())
            recyclerView?.setAdapter(adapter)
           // getCoinsList()

        })
    }


    fun filter(text: String) {
        val coins= ArrayList<CoinsModel.DataModel>()

        for (item in coinsList!!) {
            if (item.name!!.toLowerCase().contains(text.toLowerCase())) {
                coins.add(item)
            }
        }
        adapter?.filterList(coins)
    }



    /*private fun getCoinsList() {
        val progressDoalog = ProgressDialog(activity)
        progressDoalog.setMessage("Loading....")
        progressDoalog.show()


        val service: ApiInterface = ApiClient.getRetrofitInstance()?.create(
            ApiInterface::class.java
        )!!
        val call: Call<CoinsModel> = service.getCoinsList()
        call.enqueue(object : Callback<CoinsModel> {
            override fun onResponse(
                call: Call<CoinsModel>,
                response: Response<CoinsModel>
            ) {
                progressDoalog.dismiss()
                coinsList = response.body()?.data!!

                adapter = PriceAdapter(coinsList!!, activity!!)
                recyclerView!!.adapter = adapter
            }

            override fun onFailure(call: Call<CoinsModel>, t: Throwable) {
                progressDoalog.dismiss()
                Toast.makeText(
                    activity,
                    "Something went wrong...Please try later!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
*/


    }




