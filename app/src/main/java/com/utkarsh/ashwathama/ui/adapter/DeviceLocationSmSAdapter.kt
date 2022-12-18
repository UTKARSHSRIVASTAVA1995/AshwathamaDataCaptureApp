package com.utkarsh.ashwathama.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utkarsh.ashwathama.R
import com.utkarsh.ashwathama.data.models.DeviceSmSResponseModelItem

class DeviceLocationSmSAdapter : RecyclerView.Adapter<DeviceLocationSmSAdapter.ViewHolder>() {

    var listItems: ArrayList<DeviceSmSResponseModelItem> = ArrayList()


    fun RecyclerAdapter(item: ArrayList<DeviceSmSResponseModelItem>) {
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
                R.layout.device_sms_recycler_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val deviceid = view.findViewById(R.id.custmstDeviceidSms) as TextView
        private val sms = view.findViewById(R.id.custsmsTextSmS) as TextView


        fun bind(deviceSmSLogs: DeviceSmSResponseModelItem, position: Int) {
            deviceid.text = "IMEI = " + deviceSmSLogs.custmstDeviceid
            sms.text = "SMS = " + deviceSmSLogs.custsmsText


        }
    }
}