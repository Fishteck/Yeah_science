package android.example.yeahscience.ui.spaceXInfo

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
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_space_x_info.*

@AndroidEntryPoint
class SpaceXInfoFragment : Fragment(R.layout.fragment_space_x_info) {

    private val vmSpaceXCompanyInfo : CompanyInfoVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(
                R.id.action_spaceXInfoFragment_to_spaceXRocketsFragment,
            )
        }
    }

    fun initObserver() {
        vmSpaceXCompanyInfo.info.observe(this,  {

            when(it.status) {
                Resource.Status.SUCCESS -> {
                    /*if (it.data != null) {
                        //textRocket.text = it.data.companyName
                    }*/
                }
                Resource.Status.ERROR ->
                showToast(it.message)

            }
        })
    }
}