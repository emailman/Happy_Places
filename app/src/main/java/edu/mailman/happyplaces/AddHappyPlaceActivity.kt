package edu.mailman.happyplaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.mailman.happyplaces.databinding.ActivityAddHappyPlaceBinding

class AddHappyPlaceActivity : AppCompatActivity() {

    private var binding: ActivityAddHappyPlaceBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddHappyPlaceBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarAddPlace)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding?.toolbarAddPlace?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}