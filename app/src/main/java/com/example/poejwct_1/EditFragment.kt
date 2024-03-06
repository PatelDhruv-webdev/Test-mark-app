package com.example.poejwct_1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment

class EditFragment : Fragment() {
    private var dataItem: DataItem? = null
    private var position: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dataItem = it.getParcelable(ARG_DATA_ITEM)
            position = it.getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit, container, false)

        val dropdownMenu = view.findViewById<Spinner>(R.id.dropdown_menu)
        val marksInput = view.findViewById<EditText>(R.id.marks_input)
        val updateButton = view.findViewById<Button>(R.id.update_button)

        dataItem?.let {
            marksInput.setText(it.marks)
            val adapter = dropdownMenu.adapter as ArrayAdapter<String>
            val position = adapter.getPosition(it.type)
            dropdownMenu.setSelection(position)
        }

        updateButton.setOnClickListener {
            dataItem?.let { item ->
                val updatedItem = DataItem(dropdownMenu.selectedItem.toString(), marksInput.text.toString())
                DataManager.dataList[position!!] = updatedItem
                fragmentManager?.popBackStack()
            }
        }

        return view
    }

    companion object {
        private const val ARG_DATA_ITEM = "dataItem"
        private const val ARG_POSITION = "position"

        fun newInstance(dataItem: DataItem, position: Int) =
            EditFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_DATA_ITEM, dataItem)
                    putInt(ARG_POSITION, position)
                }
            }
    }
}
