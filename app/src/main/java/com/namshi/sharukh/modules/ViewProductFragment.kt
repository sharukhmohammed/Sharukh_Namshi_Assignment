package com.namshi.sharukh.modules

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.namshi.sharukh.base.BaseFragment
import com.namshi.sharukh.base.GlideApp
import com.namshi.sharukh.databinding.FragmentViewProductBinding
import com.namshi.sharukh.utils.onClick

/**
 * A simple [Fragment] subclass.
 * Use the [ViewProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewProductFragment : BaseFragment() {


    private var imageUrl: String? = null


    private var _binding: FragmentViewProductBinding? = null
    private val binding get() = _binding!!
    override val screenTitle: String?
        get() = "ViewProduct"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageUrl = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentViewProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlideApp.with(requireContext())
            .load(imageUrl)
            .into(binding.productImage)

        binding.root.onClick { activity?.onBackPressed() }
    }


    companion object {

        private const val ARG_PARAM1 = "param1"


        /**
         *
         * @param url URL of Product
         * @return A new instance of fragment ViewProductFragment.
         */
        @JvmStatic
        fun newInstance(url: String) =
            ViewProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, url)
                }
            }
    }
}