package com.tesliukdev.fairgame.screens.setup

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tesliukdev.fairgame.R
import com.tesliukdev.fairgame.databinding.FragmentSelectGateWayBinding
import com.tesliukdev.fairgame.screens.rockpapscis.RPSActivity

/**
 * A placeholder fragment containing a simple view.
 */
class SelectGateWayFragment : Fragment() {
    private val viewModel: SelectGateWayViewModel by lazy { ViewModelProviders.of(this).get(SelectGateWayViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentSelectGateWayBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_select_gate_way, container, false)
        binding.viewModel = viewModel
        setListeners()
        return binding.root
    }

    private fun setListeners() {
        viewModel.selectedGateWay.observe(this, Observer { next() })
        viewModel.error.observe(this, Observer { showDialog(it) })
    }

    private fun next() {
       startActivity(RPSActivity.getInstance(requireContext()))
    }

    @Suppress("UNUSED_ANONYMOUS_PARAMETER")
    private fun showDialog(message: String?) {
        if (message == null) {
            return
        }
        AlertDialog.Builder(this.context)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { dialog, i -> onDialogClose() }
                .create()
                .show()

    }

    private fun onDialogClose() {
        viewModel.error.value = null
    }
}
