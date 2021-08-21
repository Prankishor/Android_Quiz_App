package com.example.android.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false)

        /*
        //This is one way of doing it (navigation)

        binding.playButton.setOnClickListener{
        Navigation.findNavController(it).navigate(R.id.action_titleFragment_to_gameFragment)
        }

        //here, findNavController returns the parent of view(passed as it here). parent is the navHostFragment
        */

        //Or we can also do this
        binding.playButton.setOnClickListener {
            //We can directly make onclick listener from navigation
            //Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment)

            //but now we are using directions
            it.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }

        // 1 (MENU) to tell android that we will have menu associated with this fragment
        setHasOptionsMenu(true)

        return binding.root
    }

    //(2) MENU, just like layouts, we also inflate menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    //(3) Menu
    //When option in menu is selected, this function is called
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //by default we get the below function
        //return super.onOptionsItemSelected(item)

        //but there is a navigation UI function that can help us with navigation from options in menu
        return NavigationUI.onNavDestinationSelected(item!!,
        view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }
}