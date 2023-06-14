package org.d3if0126.asessment.ui.rumus

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if0126.asessment.R
import org.d3if0126.asessment.databinding.FragmentRumusBinding
import org.d3if0126.asessment.network.ApiStatus

class RumusFragment : Fragment() {

    private lateinit var binding: FragmentRumusBinding
    private lateinit var myAdapter: RumusAdapter
    private val viewModel: RumusViewModel by lazy {
        ViewModelProvider(this)[RumusViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRumusBinding.inflate(inflater, container, false)
        myAdapter = RumusAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }

        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_rumus -> {
                viewModel.retrieveData() // Refresh the data when the menu item is clicked
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateProgress(status: ApiStatus) {
        when (status) {
            ApiStatus.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            ApiStatus.SUCCES -> {
                binding.progressBar.visibility = View.GONE
            }
            ApiStatus.FAILED -> {
                binding.progressBar.visibility = View.GONE
                binding.networkError.visibility = View.VISIBLE
            }
        }
    }
}
