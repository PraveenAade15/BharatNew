package com.example.bharatnews.db

import androidx.room.TypeConverter


class Converters {
    @TypeConverter
    fun fromSource(source: com.example.bharatnews.models.Source):String{
        return source.name
    }
    @TypeConverter
    fun toSource(name:String): com.example.bharatnews.models.Source {
        return com.example.bharatnews.models.Source(name, name)

    }
}