package com.app.cryptocurrencyapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.cryptocurrencyapp.apiservice.ApiClient
import com.app.cryptocurrencyapp.apiservice.ApiInterface
import com.app.cryptocurrencyapp.model.CoinsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinViewModel : ViewModel() {
    private var data:MutableLiveData<ArrayList<CoinsModel.DataModel>>? = null

    //we will call this method to get the data
    fun getHeroes(): MutableLiveData<ArrayList<CoinsModel.DataModel>>{
        //if the list is null
        if (data == null) {
            data = MutableLiveData<ArrayList<CoinsModel.DataModel>>()
            //we will load it asynchronously from server in this method
            getData()
        }

        //finally we will return the list
      return data as MutableLiveData<ArrayList<CoinsModel.DataModel>>
    }

    private fun getData() {
        /*Create handle for the RetrofitInstance interface*/
        val service: ApiInterface = ApiClient.getRetrofitInstance()?.create(
            ApiInterface::class.java
        )!!
        val call: Call<CoinsModel> = service.getCoinsList()
        call.enqueue(object : Callback<CoinsModel> {
            override fun onResponse(
                call: Call<CoinsModel>,
                response: Response<CoinsModel>
            ) {
                data!!.setValue(response.body()!!.data)

               AppController.instance.coinsList= response.body()!!.data!!

            }

            override fun onFailure(call: Call<CoinsModel>, t: Throwable) {

            }
        })
    }
}