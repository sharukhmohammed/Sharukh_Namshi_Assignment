package com.namshi.sharukh.modules.secondScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.namshi.sharukh.databinding.FragmentSecondBinding
import com.namshi.sharukh.models.Image
import com.namshi.sharukh.modules.common.ActionListener
import com.namshi.sharukh.modules.firstScreen.MainViewModel
import com.namshi.sharukh.network.response.Carousel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ProductGridFragment : Fragment(), ActionListener {

    companion object {
        fun newInstance(): ProductGridFragment {
            val args = Bundle()
            val fragment = ProductGridFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: ProductGridAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getProductList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            gridRecycler.adapter = ProductGridAdapter(this@ProductGridFragment).also { adapter = it }
        }

        viewModel.productList.observe(viewLifecycleOwner, ::setData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(image: Image) {

    }

    private fun setData(data: Carousel?) {
        if (data == null) {

        } else {
            adapter.setData(data.images)
        }
    }
}