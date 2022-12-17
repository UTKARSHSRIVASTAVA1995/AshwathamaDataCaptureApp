package com.utkarsh.ashwathama.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utkarsh.ashwathama.R
import com.utkarsh.ashwathama.data.models.DeviceDetailsListResponseModelItem

class DeviceDetailsListAdapter : RecyclerView.Adapter<DeviceDetailsListAdapter.ViewHolder>() {

    var listItems: ArrayList<DeviceDetailsListResponseModelItem> = ArrayList()


    fun RecyclerAdapter(item: ArrayList<DeviceDetailsListResponseModelItem>) {
        this.listItems = item
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listItems[position]
        holder.bind(item, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.device_list_recycler_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val base = view.findViewById(R.id.custmstBase) as TextView
        private val deviceId = view.findViewById(R.id.custmstDeviceid) as TextView
        private val board = view.findViewById(R.id.custmstBoard) as TextView
        private val buildId = view.findViewById(R.id.custmstBuildid) as TextView
        private val bulduser = view.findViewById(R.id.custmstBulduser) as TextView
        private val host = view.findViewById(R.id.custmstHost) as TextView
        private val incremental = view.findViewById(R.id.custmstIncremental) as TextView
        private val brand = view.findViewById(R.id.custmstMbrand) as TextView
        private val model = view.findViewById(R.id.custmstModel) as TextView
        private val sdk = view.findViewById(R.id.custmstSdk) as TextView
        private val type = view.findViewById(R.id.custmstType) as TextView
        private val version = view.findViewById(R.id.custmstVersion) as TextView
        fun bind(deviceList: DeviceDetailsListResponseModelItem, position: Int) {
            base.text = "Base = " + deviceList.custmstBase
            deviceId.text = "IMEI = " + deviceList.custmstDeviceid
            board.text = "Board = " + deviceList.custmstBoard
            buildId.text = "Build = " + deviceList.custmstBuildid
            bulduser.text = "User = " + deviceList.custmstBulduser
            host.text = "Host = " + deviceList.custmstHost
            incremental.text = "Incremental = " + deviceList.custmstIncremental
            brand.text = "Mobile Brand = " + deviceList.custmstMbrand
            model.text = "Mobile Model = " + deviceList.custmstModel
            sdk.text = "Android Version = " + deviceList.custmstSdk
            type.text = "Type = " + deviceList.custmstType
        }
    }
}