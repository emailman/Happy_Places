package edu.mailman.happyplaces.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import edu.mailman.happyplaces.R
import edu.mailman.happyplaces.databinding.ActivityMapBinding
import edu.mailman.happyplaces.models.HappyPlaceModel

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private var binding: ActivityMapBinding? = null

    private var happyPlaceDetails: HappyPlaceModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        if (intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)) {
            happyPlaceDetails =
                intent.getSerializableExtra(MainActivity.EXTRA_PLACE_DETAILS) as
                        HappyPlaceModel
        }

        if (happyPlaceDetails != null) {
            setSupportActionBar(binding?.toolbarMap)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.title = happyPlaceDetails!!.title

            binding?.toolbarMap?.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            val supportMapFragment =
                supportFragmentManager.findFragmentById(R.id.map) as
                        SupportMapFragment
            supportMapFragment.getMapAsync(this)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val position = LatLng(happyPlaceDetails!!.latitude,
            happyPlaceDetails!!.longitude)
        googleMap.addMarker(MarkerOptions().position(position).
        title(happyPlaceDetails!!.location))
        val newLatLngZoom = CameraUpdateFactory.newLatLngZoom(position, 15f)
        googleMap.animateCamera(newLatLngZoom)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}