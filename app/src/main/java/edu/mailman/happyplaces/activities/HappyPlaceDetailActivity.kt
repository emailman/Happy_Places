package edu.mailman.happyplaces.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.mailman.happyplaces.databinding.ActivityHappyPlaceDetailBinding
import edu.mailman.happyplaces.models.HappyPlaceModel

class HappyPlaceDetailActivity : AppCompatActivity() {
    private var binding: ActivityHappyPlaceDetailBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHappyPlaceDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        var happyPlaceModel: HappyPlaceModel? = null

        if (intent.hasExtra(MainActivity.EXTRA_PLACE_DETAILS)) {
            happyPlaceModel =
                intent.getSerializableExtra(MainActivity.EXTRA_PLACE_DETAILS)
                        as HappyPlaceModel
        }

        if (happyPlaceModel != null) {
            setSupportActionBar(binding?.toolbarHappyPlaceDetail)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = happyPlaceModel.title

            binding?.toolbarHappyPlaceDetail?.setNavigationOnClickListener {
                onBackPressedDispatcher.onBackPressed()
            }

            binding?.ivPlaceImage?.setImageURI(Uri.parse(happyPlaceModel.image))
            binding?.tvDescription?.text = happyPlaceModel.description
            binding?.tvLocation?.text = happyPlaceModel.location
            binding?.btnViewOnMap?.setOnClickListener {
                val intent = Intent(this, MapActivity::class.java)
                intent.putExtra(MainActivity.EXTRA_PLACE_DETAILS, happyPlaceModel)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}