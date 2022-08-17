package com.notifiyr.fragments.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.notifiyr.R
import com.notifiyr.fragments.update.updateFragmentArgs
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_view.*
import kotlinx.android.synthetic.main.fragment_view.view.*


class ViewFragment : Fragment() {

    private val args by navArgs<ViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view, container, false)
        view.text_1.text = args.currrentItem.cccode
        view.text_2.text = args.currrentItem.header
        view.text_3.text = args.currrentItem.body

        view.floatingActionButton_ab.setOnClickListener {
            findNavController().navigate(R.id.action_viewFragment_to_mainFragment)
        }
        return view
    }
}