package com.zoom_machine.yandex_mapkit

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.location.LocationServices
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.runtime.ui_view.ViewProvider
import com.zoom_machine.yandex_mapkit.databinding.FragmentMapBinding


class MapFragment : Fragment() {
    private var binding: FragmentMapBinding? = null

    override fun onStart() {
        super.onStart()
        binding?.mapView?.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        MapKitFactory.initialize(this.requireContext())
        return inflater.inflate(R.layout.fragment_map, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)

        binding?.run {
            mapView.map?.move(
                CameraPosition(Point(59.945933, 30.320045), 11.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 5F),
                null
            )
            val mark = View(requireContext()).apply {
                background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_geo_mark)
            }
            val pins = getPins()
            pins.forEach {
                mapView.map?.mapObjects?.addPlacemark(
                    it,
                    ViewProvider(mark)
                )
            }
            buttonFindMe.setOnClickListener {
                handlingClickButtonFindMe(mark)
            }
            buttonBackArrow.setOnClickListener {
                findNavController().navigate(R.id.action_mapFragment_to_mainScreenFragment)
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation(
        mark: View,
        handlingUserLocation: (mark: View, point: Point) -> Unit
    ) {
        val context = requireContext()
        LocationServices.getFusedLocationProviderClient(context)
            .lastLocation
            .addOnSuccessListener {
                handlingUserLocation(mark, Point(it.latitude, it.longitude))
            }
            .addOnCanceledListener {
            }
            .addOnFailureListener {
            }
    }

    private fun handlingUserLocation(mark: View, point: Point) {
        binding?.run {
            mapView?.map.isIndoorEnabled = true
            mapView?.map?.move(
                CameraPosition(Point(point.latitude, point.longitude), 11.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 5F),
                null
            )
            mapView.map?.mapObjects?.addPlacemark(
                point,
                ViewProvider(mark)
            )
        }
    }

    private fun getPermission() {
        val permissions = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        ActivityCompat.requestPermissions(requireActivity(), permissions, 0)
    }

    private fun thereAreNoPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    }

    private fun handlingClickButtonFindMe(mark: View) {
        if (thereAreNoPermission()) {
            getPermission()
            if (thereAreNoPermission()) {
                Toast.makeText(
                    requireContext(),
                    R.string.need_permission,
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            getUserLocation(mark, ::handlingUserLocation)
        }
    }

    private fun getPins(): List<Point> {
        return listOf(
            Point(59.945933, 30.320045),
            Point(59.900000, 30.220000),
            Point(59.850000, 30.230000),
            Point(59.870000, 30.350000),
            Point(59.970000, 30.280000),
            Point(59.990000, 30.350000),
            Point(59.983000, 30.410000),
            Point(60.000000, 30.270000),
            Point(60.050000, 30.250000),
            Point(59.946933, 30.230045),

            )
    }

    override fun onStop() {
        super.onStop()
        binding?.mapView?.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}