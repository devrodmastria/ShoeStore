package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoe_gallery.ShoeListViewModel

class ShoeDetailFragment : Fragment() {

    private val sharedViewModel by activityViewModels<ShoeListViewModel>()
    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        val inputFieldName: EditText = binding.editShoeName
        val inputFieldSize: EditText = binding.editSize
        val inputFieldBrand: EditText = binding.editBrand
        val inputFieldInfo: EditText = binding.editDescription

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.shoe_detail_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId){
                    R.id.option_cancel -> {
                        findNavController().popBackStack()
                        true
                    }

                    R.id.option_save -> {

                        if (inputFieldName.text.toString().isNotEmpty()){

                            // input validation for shoe size
                            val shoeSizeString = inputFieldSize.text.toString()
                            var shoeSize = 0.0
                            if (shoeSizeString.isNotEmpty()){
                                shoeSize = inputFieldSize.text.toString().toDouble()
                            }

                            val newShoeItem = Shoe(inputFieldName.text.toString(),
                                shoeSize,
                                inputFieldBrand.text.toString(),
                                inputFieldInfo.text.toString(),
                                mutableListOf())

                            sharedViewModel.addShoe(newShoeItem)
                            findNavController().popBackStack()
                        }

                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

}