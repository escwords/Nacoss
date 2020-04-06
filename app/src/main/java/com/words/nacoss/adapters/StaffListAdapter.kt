package com.words.nacoss.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.words.nacoss.dataSource.Staff
import com.words.nacoss.databinding.StaffCardviewBinding
import com.words.nacoss.ui.HomeFragmentDirections

/********************************************
*  staffListAdapter.kt
 *  This class uses recycler view and viewHolder to draw view for each data in the
 *  List.
* ******************************************/
class StaffListAdapter
    : ListAdapter<Staff, RecyclerView.ViewHolder>(StaffDiffUtil()) {

   /* interface OnItemListener {
        fun listener(staff: Staff)
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StaffViewHolder(
            StaffCardviewBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val staff = getItem(position)
        (holder as StaffViewHolder).bind(staff)
    }

    class StaffViewHolder(private val binding: StaffCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //From the init part of this code the clickListener is set.
            init {
                binding.setListener { view ->
                    //let is used to call navigateToDetailScreen
                    binding.staff?.let { staff ->
                        navigateToDetailScreen(view,staff)
                    }
                }
            }
        private fun navigateToDetailScreen(view: View, staff: Staff) {

            val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(staff.id)
            view.findNavController().navigate(directions)
        }

        fun bind(staff: Staff) {
            binding.apply {
                this.staff = staff
                executePendingBindings()
            }

        }
    }
}
class StaffDiffUtil : DiffUtil.ItemCallback<Staff>() {

    override fun areItemsTheSame(oldItem: Staff, newItem: Staff): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Staff, newItem: Staff): Boolean {
        return oldItem == newItem
    }

}

