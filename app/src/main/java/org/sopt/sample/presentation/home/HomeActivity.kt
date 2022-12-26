package org.sopt.sample.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.sample.R
import org.sopt.sample.base.BindingActivity
import org.sopt.sample.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : BindingActivity<ActivityHomeBinding>(R.layout.activity_home) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addListeners()
        changeFragment(R.id.menu_home)
    }

    private fun addListeners() {
        binding.bnvHome.setOnItemSelectedListener {
            changeFragment(it.itemId)
            true
        }
    }

    private fun changeFragment(menuItemId: Int) {
        val targetFragment = getFragment(menuItemId)

        supportFragmentManager.beginTransaction()
            .replace(R.id.home_container, targetFragment) // navigation 클릭시 Fragment 매번 새로 생성됨
            .commitAllowingStateLoss()
    }

    private fun getFragment(menuItemId: Int): Fragment {
        val fragment = when (menuItemId) {
            R.id.menu_home -> HomeFragment()
            R.id.menu_search -> SearchFragment()
            R.id.menu_gallery -> GalleryFragment()
            else -> throw IllegalArgumentException("not found menu item")
        }
        return fragment
    }
}