package android.example.yeahscience.ui.spaceXRocketDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.example.yeahscience.R
import android.example.yeahscience.utils.Resource
import android.example.yeahscience.utils.showToast
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_space_x_rocket_detail.*

@AndroidEntryPoint
class SpaceXRocketDetailFragment : Fragment(R.layout.fragment_space_x_rocket_detail) {
    private val rocketVM : RocketDetailVM by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")?.let { rocketVM.start(it) }
        initObserver()
    }



    private fun initObserver() {
        rocketVM.rocket.observe(viewLifecycleOwner,  {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data != null) {
                        fragment_spaceX_rocket_detail_text.text = it.data.name
                    }
                }
                Resource.Status.ERROR ->
                   showToast(it.message)


            }
        })
    }
}