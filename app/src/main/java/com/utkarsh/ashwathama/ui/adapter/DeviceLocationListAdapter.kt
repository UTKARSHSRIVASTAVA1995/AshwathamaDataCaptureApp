package com.utkarsh.ashwathama.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utkarsh.ashwathama.R
import com.utkarsh.ashwathama.data.models.DeviceLocationResponseModelItem

class DeviceLocationListAdapter : RecyclerView.Adapter<DeviceLocationListAdapter.ViewHolder>() {

    var listItems: ArrayList<DeviceLocationResponseModelItem> = ArrayList()


    fun RecyclerAdapter(item: ArrayList<DeviceLocationResponseModelItem>) {
        this.listItems = item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.bind(item, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.device_location_recycler_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val locationId = view.findViewById(R.id.custlocationId) as TextView
        private val deviceid = view.findViewById(R.id.custmstDeviceid) as TextView
        private val locationLatitude = view.findViewById(R.id.custlocationLatitude) as TextView
        private val locationLongitude = view.findViewById(R.id.custlocationLongitude) as TextView

        fun bind(deviceCallLogs: DeviceLocationResponseModelItem, position: Int) {
            deviceid.text = "IMEI = " + deviceCallLogs.custmstDeviceid
            locationId.text = "Location Id = " + deviceCallLogs.custlocationId
            locationLatitude.text = "Latitude = " + deviceCallLogs.custlocationLatitude
            locationLongitude.text = "Longitude = " + deviceCallLogs.custlocationLongitude


        }
    }
}