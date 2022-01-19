package com.davydikes.aboutminsk.screen.main

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import com.davydikes.aboutminsk.R
import com.davydikes.aboutminsk.databinding.FragmentPlacesBinding
import com.davydikes.aboutminsk.support.*
import com.davydikes.aboutminsk.models.Place
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.davydikes.aboutminsk.support.navigateSafe
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentMain : NavigationFragment<FragmentPlacesBinding>(R.layout.fragment_places) {

    override val viewBinding: FragmentPlacesBinding by viewBinding()

    private val viewModel: ViewModelMain by viewModel()

    private val adapter = RecyclerViewAdapterPlaces(
        onClick = ::onItemClick
    )

    private fun onItemClick(place: Place) {
        findNavController().navigateSafe(FragmentMainDirections.toPlaceDetails(place))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerViewPlace.adapter = adapter
        viewBinding.indicatorProgress.visibility = View.VISIBLE
        viewModel.placesLiveData.observe(this.viewLifecycleOwner){
            if (it.isNotEmpty()){
                viewBinding.indicatorProgress.visibility = View.GONE
            } else{
                viewModel.importPlaces()
            }
            adapter.submitList(it)
        }
    }

    override fun onInsetsReceived(top: Int, bottom: Int, hasKeyboard: Boolean) {
        viewBinding.toolbarPlaces.setVerticalMargin(marginTop = top)
        viewBinding.recyclerViewPlace.setPadding(0, 0, 0, bottom)
    }

    override val backPressedCallback: OnBackPressedCallback
        get() = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.logout()
                findNavController().popBackStack()
            }
        }
}

/*
{"id_locale": 30,
"id": 1,
"name": "Minsk",
"lang": 2,
"logo": "https://krokapp.by/media/cities/c5fa77a4-eb04-40c8-9659-11ea334236b1.png",
"last_edit_time": 1508924729,
"visible": true,
"city_is_regional": true,
"region": "Minsk region"}
 */
