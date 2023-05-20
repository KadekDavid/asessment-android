package org.d3if0126.asessment.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hasil") data class HasilEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var panjang: Int,
    var lebar: Int
)