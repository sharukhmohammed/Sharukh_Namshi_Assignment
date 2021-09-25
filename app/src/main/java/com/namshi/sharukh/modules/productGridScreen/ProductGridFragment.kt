package com.namshi.sharukh.modules.productGridScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.namshi.sharukh.base.BaseFragment
import com.namshi.sharukh.dataModels.Image
import com.namshi.sharukh.databinding.FragmentGridProductBinding
import com.namshi.sharukh.modules.common.ActionListener
import com.namshi.sharukh.modules.homeScreen.MainViewModel
import com.namshi.sharukh.modules.productViewScreen.ViewProductFragment
import com.namshi.sharukh.network.response.ApiResponse
import com.namshi.sharukh.network.response.CarouselContent
import com.namshi.sharukh.utils.onClick
import com.namshi.sharukh.utils.showIf

/**
 * Displays list of Products in 2 column grid
 */
class ProductGridFragment : BaseFragment(), ActionListener {

    companion object {
        fun newInstance(): ProductGridFragment {
            val args = Bundle()
            val fragment = ProductGridFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val screenTitle: String
        get() = "ProductGridFragment"

    private var _binding: FragmentGridProductBinding? = null

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
        _binding = FragmentGridProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gridRecycler.adapter = ProductGridAdapter(this@ProductGridFragment).also { adapter = it }
        binding.gridRefresh.setOnRefreshListener { viewModel.refreshProductScreen() }
        binding.errorLayout.onClick() { viewModel.refreshProductScreen() }
        viewModel.productListLiveData.observe(viewLifecycleOwner, ::setData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(image: Image) {
        activity?.addFragment(ViewProductFragment.newInstance(image.url))
    }

    private fun setData(response: ApiResponse<CarouselContent>) {
        val data = response.data
        binding.gridRefresh.isRefreshing = response.isLoading && data == null
        binding.errorLayout.showIf(response.exception != null && data == null)
        if (data != null)
            adapter.setData(data.images)
        else
            adapter.setData(listOf())
    }
}