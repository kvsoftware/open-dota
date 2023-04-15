package com.kvsoftware.opendota.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataConverter {
    @TypeConverter
    fun fromStringList(stringList: List<String>?): String? {
        if (stringList == null) return null
        return Gson().toJson(stringList)
    }

    @TypeConverter
    fun toStringList(string: String?): List<String>? {
        if (string == null) return null
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>?>() {}.type
        return gson.fromJson<List<String>>(string, type)
    }
}