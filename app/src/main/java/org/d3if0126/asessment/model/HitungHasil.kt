package org.d3if0126.asessment.model

import org.d3if0126.asessment.db.HasilEntity

fun HasilEntity.hasilHitung(): HasilPersegiPanjang {

    val luas = panjang * lebar
    val keliling  = 2 * (panjang + lebar)

    return HasilPersegiPanjang(luas, keliling)
}