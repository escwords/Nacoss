package com.words.nacoss.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.words.nacoss.adapters.StaffListAdapter
import com.words.nacoss.dataSource.Staff
import com.words.nacoss.databinding.FragmentHomeBinding
import com.words.nacoss.ui.MainActivity
import com.words.nacoss.util.InjectorUtil
import com.words.nacoss.viewModel.HomeViewModel


class HomeFragment : Fragment() {

    //here we provide the viewModel object from injectorUtil
    private val homeViewModel: HomeViewModel by viewModels {
        InjectorUtil.provideHomeViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this

        val adapter = StaffListAdapter()
        binding.staffList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    //this function upload the adapter staffList
    private fun subscribeUi(adapter: StaffListAdapter) {
        homeViewModel.staffList.observe(viewLifecycleOwner , Observer { staffs ->
           // Toast.makeText(this.context,"$staffs",Toast.LENGTH_LONG).show()
            adapter.submitList(staffs)
        })
    }


}
