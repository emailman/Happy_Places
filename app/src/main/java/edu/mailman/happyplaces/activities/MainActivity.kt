package edu.mailman.happyplaces.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.mailman.happyplaces.database.DatabaseHandler
import edu.mailman.happyplaces.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.fabAddHappyPlace?.setOnClickListener {
            val intent =
                Intent(this@MainActivity, AddHappyPlaceActivity::class.java)
            startActivity(intent)
        }
        getHappyPlacesListFromLocalDB()
    }

    private fun getHappyPlacesListFromLocalDB() {
        val dbHandler = DatabaseHandler(this)
        val getHappyPlaceList = dbHandler.getHappyPlacesList()
        if (getHappyPlaceList.size > 0) {
            for (item in getHappyPlaceList) {
                Log.i("HappyPlacesTitle", item.title)
                Log.i("HappyPlaces Description", item.description)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}