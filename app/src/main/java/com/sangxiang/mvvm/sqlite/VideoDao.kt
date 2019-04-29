package com.sangxiang.mvvm.sqlite

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import java.util.*

@Dao
interface VideoDao {
    /**
     * Selects all [ListWeatherEntry] entries after a give date, inclusive. The LiveData will
     * be kept in sync with the database, so that it will automatically notify observers when the
     * values in the table change.
     *
     * @param date A [Date] from which to select all future weather
     * @return [LiveData] list of all [ListWeatherEntry] objects after date
     */
    @Query("SELECT * FROM video ")
    fun getAll(): LiveData<List<VideoEntity>>


    @Query("SELECT * FROM video WHERE id = :id")
    fun getById(id: Int): LiveData<VideoEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(video: VideoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(video: List<VideoEntity>)

    @Query("DELETE FROM video WHERE id = :id")
    fun delete(id: Int)

}