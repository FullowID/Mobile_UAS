package com.uas.hololiveviewer.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.uas.hololiveviewer.databinding.FragmentVideoBinding
import com.google.android.material.divider.MaterialDividerItemDecoration
import com.uas.hololiveviewer.R
import com.uas.hololiveviewer.ui.live.UpcomingListener

class VideoFragment : Fragment() {
    private val viewModel: VideoViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentVideoBinding.inflate(inflater)
        viewModel.getHololiveData()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.rvVideo.adapter = VideoAdapter(VideoListener { holo ->
            viewModel.onHololiveItemClicked(holo)
            findNavController()
                .navigate(R.id.action_videoFragment_to_videoDetailFragment)
        })

        (activity as AppCompatActivity).supportActionBar?.title = "Video List"
        binding.rvVideo.addItemDecoration(
            MaterialDividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )

        return binding.root
    }
}