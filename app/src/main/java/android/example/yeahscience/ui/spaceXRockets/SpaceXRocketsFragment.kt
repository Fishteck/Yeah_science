package android.example.yeahscience.ui.spaceXRockets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.example.yeahscience.R
import android.example.yeahscience.utils.Resource
import android.example.yeahscience.utils.showToast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpaceXRocketsFragment : Fragment(R.layout.fragment_space_x_rockets), RocketsAdapter.RocketItemListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RocketsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val rocketsVM : RocketsVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView(view)
        initObserver()
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.fragment_spaceX_rockets_RV)
        layoutManager = LinearLayoutManager(view.context)
        recyclerAdapter = RocketsAdapter(this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = layoutManager
    }

    private fun initObserver() {
        rocketsVM.rockets.observe(viewLifecycleOwner,  Observer{
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    if (!it.data.isNullOrEmpty()) {
                       recyclerAdapter.setItems(it.data)
                    } else {
                        showToast(it.message)
                    }
                }
                Resource.Status.ERROR ->
                    showToast(it.message)


            }
        })
    }

    override fun onClickItemListener(id: String) {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_spaceXRocketsFragment_to_spaceXRocketDetailFragment,
            bundleOf("id" to id)
        )
    }
}