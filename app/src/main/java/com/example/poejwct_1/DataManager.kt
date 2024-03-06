package com.example.poejwct_1

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object DataManager {
    var dataList = mutableListOf<DataItem>()

    fun saveData(context: Context) {
        val sharedPreferences = context.getSharedPreferences("com.example.poejwct_1", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(dataList)
        editor.putString("dataList", json)
        editor.apply()
    }

    fun loadData(context: Context) {
        val sharedPreferences = context.getSharedPreferences("com.example.poejwct_1", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("dataList", null)
        val type = object : TypeToken<MutableList<DataItem>>() {}.type
        dataList = gson.fromJson(json, type) ?: mutableListOf()
    }
}
