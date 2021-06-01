package com.app.cryptocurrencyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.app.cryptocurrencyapp.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var bottomNavigationView: BottomNavigationView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView=findViewById(R.id.bottomNavigationView)


        val priceFragment= PriceFragment()
        val favoritesFragment= FavoritesFragment()
        val portfolioFragment= PortfolioFragment()
        val newsFragment= NewsFragment()
        val investFragment= InvestFragment()

        setCurrentFragment(priceFragment)

        bottomNavigationView?.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.prices->setCurrentFragment(priceFragment)
                R.id.favorites->setCurrentFragment(favoritesFragment)
                R.id.portfolio->setCurrentFragment(portfolioFragment)
                R.id.news->setCurrentFragment(newsFragment)
                R.id.invest->setCurrentFragment(investFragment)
            }
            true
        }

    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout,fragment)
            commit()
        }

}
