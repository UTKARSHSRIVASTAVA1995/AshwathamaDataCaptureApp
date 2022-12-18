package com.utkarsh.ashwathama.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utkarsh.ashwathama.R
import com.utkarsh.ashwathama.data.models.DeviceContactsResponseModelItem

class DeviceContactsAdapter : RecyclerView.Adapter<DeviceContactsAdapter.ViewHolder>() {

    var listItems: ArrayList<DeviceContactsResponseModelItem> = ArrayList()


    fun RecyclerAdapter(item: ArrayList<DeviceContactsResponseModelItem>) {
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
                R.layout.device_contacts_recycler_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val custcontactNumber = view.findViewById(R.id.custcontactNumberss) as TextView
        private val custcontactName = view.findViewById(R.id.custcontactNamee) as TextView


        fun bind(deviceSmSLogs: DeviceContactsResponseModelItem, position: Int) {
            custcontactNumber.text = "Name = " + deviceSmSLogs.custcontactNumber
            custcontactName.text = "Number = " + deviceSmSLogs.custcontactName

        }
    }
}