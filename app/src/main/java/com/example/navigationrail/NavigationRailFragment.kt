package com.example.navigationrail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.navigationrail.databinding.FragmentHomeBinding
import com.example.navigationrail.databinding.FragmentNavigationRailBinding

class NavigationRailFragment : Fragment() {

    private var _binding: FragmentNavigationRailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNavigationRailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFragment(FileFragment())
        setNavRail()
        setListener()
    }

    private fun setListener() {
        binding.apply {
            buttonHide.setOnClickListener {
                showHideNav(true)
            }

            buttonShow.setOnClickListener {
                showHideNav(false)
            }
        }
    }

    private fun setNavRail() {
        binding.navigationRail.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.files -> {
                    loadFragment(FileFragment())
                    true
                }
                R.id.images -> {
                    loadFragment(ImagesFragment())
                    true
                }
                R.id.music -> {
                    loadFragment(MusicFragment())
                    true
                }
                else -> false
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.frameLayout,fragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    private fun showHideNav(condition: Boolean) {
        binding.apply {
            if (condition) {
                navigationRail.visibility = View.GONE
                ivHide.visibility = View.GONE
                buttonHide.visibility = View.GONE

                ivShow.visibility = View.VISIBLE
                buttonShow.visibility = View.VISIBLE
                ivContainer.visibility = View.VISIBLE
            } else {
                navigationRail.visibility = View.VISIBLE
                ivHide.visibility = View.VISIBLE
                buttonHide.visibility = View.VISIBLE

                ivShow.visibility = View.GONE
                buttonShow.visibility = View.GONE
                ivContainer.visibility = View.GONE
            }
        }
    }
}