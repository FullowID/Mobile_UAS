package com.uas.hololiveviewer.ui.channel

import android.net.wifi.p2p.WifiP2pManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.uas.hololiveviewer.R
import com.uas.hololiveviewer.databinding.FragmentChannelBinding

class ChannelFragment : Fragment() {
    private val viewModel: ChannelViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentChannelBinding.inflate(inflater)
        viewModel.getHololiveData()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.rvChannel.adapter = ChannelAdapter(ChListener { holo ->
            viewModel.onHololiveItemClicked(holo)
            findNavController()
                .navigate(R.id.action_nav_channels_to_channelDetailFragment)
        })

        (activity as AppCompatActivity).supportActionBar?.title = "Channel List"
        binding.rvChannel.addItemDecoration(
            MaterialDividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        return binding.root
    }
}