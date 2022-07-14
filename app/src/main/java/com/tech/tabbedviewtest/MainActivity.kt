package com.tech.tabbedviewtest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout: TabLayout = findViewById(R.id.tabView)
        val viewpager: ViewPager = findViewById(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Source"));
        tabLayout.addTab(tabLayout.newTab().setText("Category"));



        viewpager.adapter = TabbedAdapter(supportFragmentManager)

        tabLayout.setupWithViewPager(viewpager)


    }
}