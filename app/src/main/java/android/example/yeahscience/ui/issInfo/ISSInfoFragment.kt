package android.example.yeahscience.ui.issInfo



import androidx.fragment.app.Fragment
import android.example.yeahscience.R
import android.example.yeahscience.utils.Resource
import android.example.yeahscience.utils.showToast
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ISSInfoFragment : Fragment(R.layout.fragment_iss_info), OnMapReadyCallback{

    private lateinit var map : GoogleMap
    private lateinit var googleMapsFragment: SupportMapFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        googleMapsFragment = childFragmentManager.findFragmentById(R.id.fragment_iss_info_map) as SupportMapFragment
        googleMapsFragment.getMapAsync(this)
        initObserver()
    }

    private val vmLocation : ISSViewModel by viewModels()

    private fun initObserver() {
        vmLocation.currentLocation.observe(viewLifecycleOwner, {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    val maxLongitude = 84.0
                    if (it.data != null) {
                        val latitude = it.data.issCurrentLocation.latitude.toDouble()
                        val longitude =  it.data.issCurrentLocation.longitude.toDouble()
                        var coordinates = LatLng(0.0, 0.0)


                        if (latitude>= maxLongitude) {
                            coordinates = LatLng(maxLongitude, longitude)
                        } else {
                            coordinates = LatLng(latitude, longitude)
                        }

                        map
                            .addMarker(
                                MarkerOptions()
                                    .position(coordinates)
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.iss_icon))
                            )
                        map.moveCamera(CameraUpdateFactory.newLatLng(coordinates))
                    }
                }
                Resource.Status.ERROR ->
                    showToast(it.message)

            }
        })
    }

    override fun onMapReady(p0: GoogleMap?) {
        if (p0 != null) {
            map = p0
        }
    }


}