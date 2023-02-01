package edu.mailman.happyplaces.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.withContext
import java.util.*

class GetAddressFromLatLng(context: Context,
                           private val lat: Double,
                           private val lng: Double) {
    private val geocoder = Geocoder(context, Locale.getDefault())
    private lateinit var addressListener: AddressListener

    private fun getAddress():String{
        try {
            // There may be multiple locations/places associated with the lat and lng,
            // we take the top/most relevant address
            val addressList:List<Address>?=geocoder.getFromLocation(lat,lng,1)

            if(!addressList.isNullOrEmpty()){
                val address:Address=addressList[0]
                val sb=StringBuilder()
                for(i in 0..address.maxAddressLineIndex){  //Returns the largest index currently in use to specify an address line.
                    sb.append(address.getAddressLine(i)+" ")
                }
                sb.deleteCharAt(sb.length-1)   //to remove the last " "

                return sb.toString()
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
        return ""
    }

    suspend fun launchBackgroundProcessForRequest() {
        val address=getAddress()

        withContext(Main){
            // Switch to Main thread to update the UI related values
            // from here on if we get a valid address
            if (address.isEmpty()) {
                addressListener.onError()
            } else {
                addressListener.onAddressFound(address)  //updating UI
            }
        }
    }

    fun setAddressListener(myAddressListener: AddressListener) {
        addressListener = myAddressListener
    }

    interface AddressListener {
        fun onAddressFound(address: String?)
        fun onError()
    }
}