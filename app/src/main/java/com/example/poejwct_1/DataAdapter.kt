package com.example.poejwct_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

class DataAdapter(private val dataList: List<DataItem>, private val fragmentActivity: FragmentActivity) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val typeLabel: TextView = view.findViewById(R.id.type_label)
        val marksLabel: TextView = view.findViewById(R.id.marks_label)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
        val editButton: Button = view.findViewById(R.id.edit_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.typeLabel.text = item.type
        holder.marksLabel.text = item.marks

        holder.deleteButton.setOnClickListener {
            DataManager.dataList.removeAt(position)
            notifyItemRemoved(position)
            Toast.makeText(fragmentActivity, "Mark deleted", Toast.LENGTH_SHORT).show()

        }

        holder.editButton.setOnClickListener {
            val dataItem = dataList[position]
            val editFragment = EditFragment.newInstance(dataItem, position)
            val fragmentManager = fragmentActivity.supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, editFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }


    }

    override fun getItemCount(): Int = dataList.size
}
