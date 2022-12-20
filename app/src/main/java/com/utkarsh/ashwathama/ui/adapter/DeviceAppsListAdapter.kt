package com.utkarsh.ashwathama.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utkarsh.ashwathama.R
import com.utkarsh.ashwathama.data.models.DeviceAppsResponseModelItem
import com.utkarsh.ashwathama.data.models.DeviceCallLogsResponseModelItem

class DeviceAppsListAdapter : RecyclerView.Adapter<DeviceAppsListAdapter.ViewHolder>() {

    var listItems: ArrayList<DeviceAppsResponseModelItem> = ArrayList()


    fun RecyclerAdapter(item: ArrayList<DeviceAppsResponseModelItem>) {
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
                R.layout.device_apps_list_recycler_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById(R.id.appName) as TextView
        private val imei = view.findViewById(R.id.Imei) as TextView


        fun bind(deviceApps: DeviceAppsResponseModelItem, position: Int) {
            name.text = deviceApps.custappApps
            imei.text = deviceApps.custmstdeviceId



        }
    }
}