package org.d3if0126.asessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import org.d3if0126.asessment.databinding.ActivityMainBinding
import org.d3if0126.asessment.model.HasilPersegiPanjang
import org.d3if0126.asessment.model.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener { hitungPersegiPanjang() }
        viewModel.getHasilPersegiPanjang().observe(this, { showResult(it) })
    }
    private fun hitungPersegiPanjang() {

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
            viewModel.hitungPersegiPanjang(
            panjang.toInt(),
            lebar.toInt()
        )
    }
    private fun showResult(result: HasilPersegiPanjang?) {
        if (result == null) return
        binding.LuasTextView.text = getString(R.string.luas_x, result.luas)
        binding.KelilingTextView.text = getString(R.string.keliling_x, result.keliling)
    }
}