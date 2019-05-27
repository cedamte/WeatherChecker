package com.example.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.data.BuildConfig

@Entity(tableName = BuildConfig.TABLE_NAME, indices = [Index("name", unique = true)])
data class FavoriteCity(
                @PrimaryKey(autoGenerate = true)
                val cityId: Int? = null,
                val name: String,
                val isFavorite: Boolean,
                val temperatureInKelvin: Float,
                val condition: String,
                val windSpeed: String,
                val windDegrees: Float)