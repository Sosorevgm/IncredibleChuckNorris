package com.incredible.chuck.norris.features.fact_feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.incredible.chuck.norris.R
import com.incredible.chuck.norris.data.image_datasource.ImageLoader
import com.incredible.chuck.norris.data.models.FactModel
import com.incredible.chuck.norris.common.BaseFragment
import com.incredible.chuck.norris.common.ErrorModel
import com.incredible.chuck.norris.databinding.FragmentFactBinding
import com.incredible.chuck.norris.utils.Constants.CATEGORY
import com.incredible.chuck.norris.utils.getDateString
import com.incredible.chuck.norris.utils.getSnackBarFactsFromCache
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.Router
import java.util.*

class FactFragment : BaseFragment(), View.OnClickListener {

    companion object {
        fun getInstance(category: String) = FactFragment().apply {
            arguments = Bundle().apply {
                putString(CATEGORY, category)
            }
        }
    }

    private val viewModel: FactViewModel by viewModel()
    private val imageLoader: ImageLoader by inject()
    private val router: Router by inject()

    private var _binding: FragmentFactBinding? = null
    private val binding get() = _binding!!
    private var category: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFactBinding.inflate(inflater, container, false)

        initFragmentLayout(
            binding.factSuccessLayout,
            binding.factLoadingLayout,
            binding.factErrorLayout.errorWidgetLayout
        )

        category = arguments?.getString(CATEGORY)

        if (viewModel.currentFact != null) {
            showSuccess(viewModel.currentFact!!)
        } else {
            category?.let {
                binding.tvFactCategory.text = it
                viewModel.fetchData(it)
            }
        }

        viewModel.screenState.observe(viewLifecycleOwner, {
            when (it) {
                is FactScreenState.Loading -> showLoading()
                is FactScreenState.SuccessFromApi -> {
                    showSuccess(it.fact)
                }
                is FactScreenState.SuccessFromCache -> {
                    showSuccess(it.fact)
                    getSnackBarFactsFromCache(requireView(), requireContext()).show()
                }
                is FactScreenState.Error -> {
                    showError(it.errorModel, this)
                }
            }
        })

        binding.layoutFactFragmentArrowBack.setOnClickListener {
            router.exit()
        }

        binding.layoutFactFragmentShare.setOnClickListener {
            viewModel.currentFact?.fact?.let {
                val message = "$it ${getString(R.string.google_play_link)}"
                viewModel.shareFact(message)
            }
        }

        binding.factSwipeLayout.setOnRefreshListener {
            binding.factSwipeLayout.isRefreshing = false
            category?.let {
                viewModel.updateFact(it)
            }
        }
        return binding.root
    }

    override fun showSuccess(data: Any) {
        super.showSuccess(data)
        binding.layoutFactFragmentShare.isClickable = true

        ContextCompat.getDrawable(
            requireContext(),
            R.drawable.chuck_main_icon
        )?.let {
            imageLoader.loadImageFromResources(it, binding.ivFactIcon)
        }

        val fact = data as FactModel

        binding.tvFactCategory.text = viewModel.currentCategory.capitalize(Locale.ROOT)
        binding.tvFactText.text = fact.fact
        binding.tvFactDate.text = getDateString(fact.date)
        viewModel.currentFact = fact
    }

    override fun showLoading() {
        super.showLoading()
        binding.layoutFactFragmentShare.isClickable = false
    }

    override fun showError(errorModel: ErrorModel, errorListener: View.OnClickListener?) {
        super.showError(errorModel, errorListener)
        binding.layoutFactFragmentShare.isClickable = false
        binding.factSwipeLayout.isRefreshing = false
        viewModel.currentCategory = ""
        viewModel.currentFact = null
    }

    override fun onClick(v: View?) {
        category?.let {
            viewModel.fetchData(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}