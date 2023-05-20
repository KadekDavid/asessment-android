package org.d3if0126.asessment.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface HasilDao {
    @Insert
fun insert(bmi: HasilEntity)

    @Query("SELECT * FROM hasil ORDER BY id DESC")
    fun getLastHasil(): LiveData<List<HasilEntity>>

    @Query("DELETE FROM hasil")
    fun clearData()
}