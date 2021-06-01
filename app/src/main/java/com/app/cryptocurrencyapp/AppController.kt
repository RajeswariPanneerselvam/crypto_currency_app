package com.app.cryptocurrencyapp

import android.app.Application
import com.app.cryptocurrencyapp.model.CoinsModel

class AppController: Application() {


    override fun onCreate() {
        super.onCreate()
        instance=this
    }
    companion object {
      lateinit  var instance: AppController
      private set
    }

    var coinsList: ArrayList<CoinsModel.DataModel> = arrayListOf()


}