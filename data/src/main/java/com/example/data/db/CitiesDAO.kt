package com.example.data.db

import androidx.room.*
import com.example.data.model.FavoriteCity
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface CitiesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(favoriteCity: FavoriteCity)

    @Delete
    fun deleteCity(favoriteCity: FavoriteCity)

    @Query("SELECT * FROM cities")
    fun getAllCities(): Flowable<List<FavoriteCity>>

}