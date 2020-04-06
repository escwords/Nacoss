package com.words.nacoss.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.words.nacoss.R
import com.words.nacoss.databinding.FragmentDetailBinding
import com.words.nacoss.util.InjectorUtil
import com.words.nacoss.viewModel.DetailViewModel



class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private val mdetailViewModel: DetailViewModel by viewModels {
        InjectorUtil.provideDetailViewModelFactory(args.staffId,requireActivity())

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

     val binding = FragmentDetailBinding.inflate(inflater,container,false).apply {
         lifecycleOwner = viewLifecycleOwner
         detailViewModel = mdetailViewModel

     }
        // setHasOptionsMenu(true)
        return  binding.root
    }

}
