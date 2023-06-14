package org.d3if0126.asessment.ui.hitung

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if0126.asessment.R
import org.d3if0126.asessment.databinding.HitungFragmentBinding
import org.d3if0126.asessment.db.HasilDb
import org.d3if0126.asessment.model.HasilPersegiPanjang

class HitungFragment : Fragment() {


    private lateinit var binding: HitungFragmentBinding

    private val viewModel: HitungViewModel by lazy {
        val db = HasilDb.getInstance(requireContext())
        val factory = HitungViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = HitungFragmentBinding.inflate(layoutInflater, container, false)
        binding.bagikanButton.setOnClickListener { shareData() }
        setHasOptionsMenu(true)
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_hitungFragment_to_historiFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_aboutFragment
                )
                return true
            }
            R.id.menu_rumus -> {
                findNavController().navigate(
                    R.id.action_hitungFragment_to_rumusFragment
                )
                return true
            }
    }
        return super.onOptionsItemSelected(item) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener { hitungPersegiPanjang() }
        viewModel.getHasilPersegiPanjang().observe(requireActivity(), { showResult(it) })
    }

    private fun hitungPersegiPanjang() {

        val panjang = binding.panjangInp.text.toString()
        if (TextUtils.isEmpty(panjang)) {
            Toast.makeText(context, R.string.panjang_invalid, Toast.LENGTH_LONG).show()
            return
        }
        val lebar = binding.lebarInp.text.toString()
        if (TextUtils.isEmpty(lebar)) {
            Toast.makeText(context, R.string.lebar_invalid, Toast.LENGTH_LONG).show()
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
        binding.bagikanButton.visibility = View.VISIBLE
    }

    private fun shareData() {

        val message = getString(R.string.bagikan_template,
            binding.panjangInp.text,
            binding.lebarInp.text,
            binding.LuasTextView.text,
            binding.KelilingTextView.text )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) { startActivity(shareIntent)
        }
    }
}