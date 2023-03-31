package org.d3if0126.asessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import org.d3if0126.asessment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { hitungLuas() }
    }
    private fun hitungLuas() {

        val panjang = binding.panjangInp.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(this, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val lebar = binding.lebarInp.text.toString()
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(this, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val luas = panjang.toInt() * lebar.toInt()

        binding.LuasTextView.text = getString(R.string.luas_x, luas)
    }
}