package com.UAS.hololiveviewer.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.UAS.hololiveviewer.R
import com.UAS.hololiveviewer.databinding.FragmentCharactersBinding
import com.google.android.material.divider.MaterialDividerItemDecoration

class CharactersFragment : Fragment() {
    private val viewModel: CharactersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCharactersBinding.inflate(inflater)
        viewModel.getCharactersData()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.rv.adapter = CharactersAdapter(CharactersListener( { chara ->
            viewModel.onCharactersItemClicked(chara)
            findNavController()
                .navigate(R.id.action_charactersFragment_to_charactersDetailFragment)
        }))

        (activity as AppCompatActivity).supportActionBar?.title = "Data Vtuber Hololive"
        binding.rv.add.ItemDecoration(
            MaterialDividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        return binding.root
    }
}