package com.namshi.sharukh.modules.firstScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.namshi.sharukh.base.BaseActivity
import com.namshi.sharukh.databinding.FragmentFirstBinding
import com.namshi.sharukh.models.Image
import com.namshi.sharukh.modules.common.ActionListener
import com.namshi.sharukh.modules.firstScreen.adapters.NamshiWidgetAdapter
import com.namshi.sharukh.modules.secondScreen.ProductGridFragment
import com.namshi.sharukh.network.response.ApiResponse
import com.namshi.sharukh.network.response.HomeContent
import com.namshi.sharukh.utils.onClick
import com.namshi.sharukh.utils.showIf

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MainFragment : Fragment(), ActionListener {

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

        adapter = NamshiWidgetAdapter(this)
        binding.mainRecycler.adapter = adapter
        binding.mainRefresh.setOnRefreshListener { viewModel.refreshMainScreen() }
        binding.errorLayout.onClick { viewModel.refreshMainScreen() }
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
        (activity as BaseActivity).addFragment(ProductGridFragment.newInstance(), true, addToBackStack = true)
    }
}