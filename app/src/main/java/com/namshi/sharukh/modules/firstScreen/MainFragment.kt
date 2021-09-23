package com.namshi.sharukh.modules.firstScreen

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.namshi.sharukh.databinding.FragmentFirstBinding
import com.namshi.sharukh.models.NamshiWidget
import com.namshi.sharukh.modules.firstScreen.adapters.NamshiWidgetAdapter
import com.namshi.sharukh.network.response.ContentResponse

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment() {

    companion object {
        fun newInstance(): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: NamshiWidgetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.mainRecycler.apply {
            adapter = NamshiWidgetAdapter().apply {
                this@MainFragment.adapter = this
            }
        }

        viewModel.content.observe(viewLifecycleOwner, ::setData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setData(data: ContentResponse?) {

        //TransitionManager.beginDelayedTransition(binding.root)
        if (data == null) {
            adapter.setData(listOf())
        } else {
            val filtered = data.content
            adapter.setData(filtered)

        }

    }
}