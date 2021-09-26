package com.namshi.sharukh.modules.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.namshi.sharukh.databinding.FragmentAboutBinding
import com.namshi.sharukh.utils.onClick


class AboutFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(): AboutFragment {
            val args = Bundle()

            val fragment = AboutFragment()
            fragment.arguments = args
            return fragment
        }

        internal const val TAG = "AboutFragment"
    }

    private var _binding: FragmentAboutBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.aboutMoreAction.onClick {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/sharukhmohammed/Sharukh_Namshi_Assignment"))
            startActivity(browserIntent)
        }
    }
}