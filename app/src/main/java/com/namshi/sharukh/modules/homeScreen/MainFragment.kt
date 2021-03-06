package com.namshi.sharukh.modules.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.namshi.sharukh.base.BaseFragment
import com.namshi.sharukh.dataModels.Image
import com.namshi.sharukh.databinding.FragmentMainBinding
import com.namshi.sharukh.modules.common.ActionListener
import com.namshi.sharukh.modules.homeScreen.adapters.NamshiWidgetAdapter
import com.namshi.sharukh.modules.productGridScreen.ProductGridFragment
import com.namshi.sharukh.network.response.ApiResponse
import com.namshi.sharukh.network.response.HomeContent
import com.namshi.sharukh.utils.onClick
import com.namshi.sharukh.utils.showIf

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : BaseFragment(), ActionListener {


    companion object {
        fun newInstance(): MainFragment {
            val args = Bundle()
            val fragment = MainFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override val screenTitle: String
        get() = "Home Screen"

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: NamshiWidgetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NamshiWidgetAdapter(this, this)
        binding.mainRecycler.adapter = adapter
        binding.mainRefresh.setOnRefreshListener { viewModel.refreshMainScreen() }
        binding.errorLayout.onClick() { viewModel.refreshMainScreen() }
        viewModel.homeContentLiveData.observe(viewLifecycleOwner, ::setData)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setData(response: ApiResponse<HomeContent>) {
        val data = response.data

        binding.mainRefresh.isRefreshing = response.isLoading
        binding.errorLayout.showIf(response.exception != null && data == null)

        if (data != null)
            adapter.setData(data.content)
        else
            adapter.setData(listOf())
    }

    override fun onItemClick(image: Image) {
        activity?.addFragment(ProductGridFragment.newInstance(), true, addToBackStack = true)
    }
}