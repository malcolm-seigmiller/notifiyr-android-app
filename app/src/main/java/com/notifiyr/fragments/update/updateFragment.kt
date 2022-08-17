package com.notifiyr.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.notifiyr.R
import com.notifiyr.models.cccode
import com.notifiyr.viewmodel.ViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class updateFragment : Fragment() {

    private val args by navArgs<updateFragmentArgs>()

    private lateinit var mUserViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update, container, false)
        mUserViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        view.update_ccode.setText(args.CurrentCccode.cccode)

        view.update_button.setOnClickListener {
            updateItem()
        }
        view.delet_btn.setOnClickListener {
            deleteUser()
        }
        view.update_back_button.setOnClickListener {
            findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
        }
        return view
    }
    private fun updateItem() {
        val cccode = update_ccode.text.toString()

        if(inputCheck(cccode)){
            val updatedcccode = cccode(args.CurrentCccode.id, cccode)
            mUserViewModel.updateCccode(updatedcccode)
            Toast.makeText(requireContext(), "Updated Successfully!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
        }else{
            Toast.makeText(requireContext(), "Please enter a Cccode.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(cccode: String): Boolean{
        return !(TextUtils.isEmpty(cccode))
    }
    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteCccode(args.CurrentCccode)
            Toast.makeText(
                    requireContext(),
                    "Successfully removed: ${args.CurrentCccode.cccode}",
                    Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment2)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.CurrentCccode.cccode}?")
        builder.setMessage("Are you sure you want to delete ${args.CurrentCccode.cccode}?")
        builder.create().show()
    }

}