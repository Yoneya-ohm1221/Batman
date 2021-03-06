package com.example.aloanmini.ui.fragment.homePager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aloanmini.ui.fragment.homePager.my_borrower.MyBorrowerFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyBorrowerFragment()
            1 -> MyDueFragment()
            2 -> FinishFragment()
            else -> Fragment()
        }
    }
}