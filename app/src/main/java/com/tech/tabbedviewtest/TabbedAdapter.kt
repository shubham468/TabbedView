package com.tech.tabbedviewtest

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.tech.tabbedviewtest.Tabs.CategoryFragment
import com.tech.tabbedviewtest.Tabs.NewsFragment
import com.tech.tabbedviewtest.Tabs.SourceFragment

class TabbedAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getCount(): Int = 3
    override fun getItem(position: Int): Fragment {
        val fragment: Fragment
        when (position) {
            0 -> {
                fragment = NewsFragment()
                return fragment
            }
            1 -> {
                fragment = SourceFragment()
                return fragment
            }
            2 -> {
                fragment = CategoryFragment()
                return fragment
            }
            else -> {

                return NewsFragment()
            }
        }
    }


    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> {
                "News"
            }
            1 -> {
                "Sources"
            }
            2 -> {
                "Category"
            }
            else -> {

                "News"
            }
        }
    }
}