package com.utkarsh.ashwathama.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utkarsh.ashwathama.R
import com.utkarsh.ashwathama.data.models.DeviceCallLogsResponseModelItem

class DeviceCallLogsListAdapter : RecyclerView.Adapter<DeviceCallLogsListAdapter.ViewHolder>() {

    var listItems: ArrayList<DeviceCallLogsResponseModelItem> = ArrayList()


    fun RecyclerAdapter(item: ArrayList<DeviceCallLogsResponseModelItem>) {
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
                R.layout.device_call_logs_recycler_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById(R.id.custmstName) as TextView
        private val number = view.findViewById(R.id.custmstNumber) as TextView
        private val type = view.findViewById(R.id.custmstCalltype) as TextView
        private val date = view.findViewById(R.id.custmstDate) as TextView

        fun bind(deviceCallLogs: DeviceCallLogsResponseModelItem, position: Int) {
            name.text = "Name = " + deviceCallLogs.custcallName
            number.text = "Number = " + deviceCallLogs.custcallNumber
            if (deviceCallLogs.custcallType == null){
                type.text = "Call Type  = " + "No Data"
            }else{ type.text = "Call Type  = " + deviceCallLogs.custcallType }
            date.text = "Date = " + deviceCallLogs.custcallDate


        }
    }
}