package com.udacity.shoestore.shoe_gallery

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView

import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private val sharedViewModel by activityViewModels<ShoeListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        sharedViewModel.setLoginComplete()

        sharedViewModel.shoeLiveDataList.observe(viewLifecycleOwner) { shoeList ->
            for (item in shoeList){
                Timber.wtf("-->> Shoe Item %s", item.name)
                binding.shoeLinearLayout.addView(generateShoeItemLayout(item))
            }
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.option_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId == R.id.option_logout) {
                    logout()
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.fabAddShoe.setOnClickListener {
            addShoe()
        }



        return binding.root
    }

    private fun addShoe(){
        val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
        findNavController().navigate(action)
    }

    private fun logout(){
        sharedViewModel.resetLogin()
        val action = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
        findNavController().navigate(action)
    }

    private fun generateShoeItemLayout(shoe: Shoe): View {

        val textString = "${shoe.name} * ${shoe.size} * ${shoe.company} * ${shoe.description}"

        val textView = TextView(binding.root.context)
        textView.text = textString
        textView.textSize = 20F
        textView.setTextColor(resources.getColor(R.color.darkText, resources.newTheme()))

        // layout height allows enough space for two lines, if needed
        val layoutDetails = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 140)
        textView.layoutParams = layoutDetails

        return textView
    }




}