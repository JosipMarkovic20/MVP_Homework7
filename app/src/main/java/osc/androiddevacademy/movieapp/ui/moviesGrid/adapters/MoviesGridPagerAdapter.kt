package osc.androiddevacademy.movieapp.ui.moviesGrid.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import osc.androiddevacademy.movieapp.ui.moviesGrid.fragments.MoviesGridFavoriteFragment
import osc.androiddevacademy.movieapp.ui.moviesGrid.fragments.MoviesGridFragment
import osc.androiddevacademy.movieapp.ui.moviesGrid.fragments.MoviesGridTopRatedFragment

class MoviesGridPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> MoviesGridFragment()
            1 -> MoviesGridTopRatedFragment()
            2 -> MoviesGridFavoriteFragment()
            else -> MoviesGridFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "Popular"
        } else if (position == 1) {
            title = "Top rated"
        }else if (position == 2){
            title = "Favorites"
        }
        return title
    }

    override fun getCount() = 3
}