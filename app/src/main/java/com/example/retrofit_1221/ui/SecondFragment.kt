package com.example.retrofit_1221.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.example.retrofit_1221.databinding.FragmentSecondBinding
import com.example.retrofit_1221.viewModel.MarsViewModel
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class SecondFragment : Fragment() {

    private lateinit var binding : FragmentSecondBinding
    private val viewModel : MarsViewModel by activityViewModels()
    var idMars: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idMars = it.getString("id", "")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTerrainById(idMars).observe(viewLifecycleOwner, {
            it?.let {
                Glide.with(binding.imageView2)
                    .load(it.srcImg).centerCrop().into(binding.imageView2)
            binding.tvType.text = it.type
            binding.tvPrice.text = it.price.toString()
        }
        })
    }

  }

