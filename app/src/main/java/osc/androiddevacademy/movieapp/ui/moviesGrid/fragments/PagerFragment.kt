package osc.androiddevacademy.movieapp.ui.moviesGrid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import osc.androiddevacademy.movieapp.R
import osc.androiddevacademy.movieapp.ui.moviesGrid.adapters.MoviesGridPagerAdapter

class PagerFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result = inflater.inflate(R.layout.pager, container, false)
        val pager = result.findViewById(R.id.viewPager) as ViewPager
        val tabLayout = result.findViewById(R.id.tabLayout)as TabLayout


        pager.adapter = buildAdapter()

        tabLayout.setupWithViewPager(pager)

        return result
    }

    private fun buildAdapter(): PagerAdapter {
        return MoviesGridPagerAdapter(childFragmentManager)
    }
}