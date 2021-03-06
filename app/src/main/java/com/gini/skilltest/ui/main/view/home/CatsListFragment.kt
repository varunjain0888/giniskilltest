package com.gini.skilltest.ui.main.view.home

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.transition.TransitionInflater
import com.gini.skilltest.R
import com.gini.skilltest.data.api.ApiHelper
import com.gini.skilltest.data.api.RetrofitBuilder
import com.gini.skilltest.data.model.Cat
import com.gini.skilltest.databinding.FragmentCatsListBinding
import com.gini.skilltest.ui.base.ViewModelFactory
import com.gini.skilltest.ui.main.view.MainActivity
import com.gini.skilltest.ui.main.view.home.adapters.CatListAdapter
import com.gini.skilltest.ui.main.view.home.adapters.CatLoadStateAdapter
import com.gini.skilltest.ui.main.view.home.deligates.CatImageClickDelegate
import com.gini.skilltest.ui.main.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class CatsListFragment : Fragment() {
    private var activity: MainActivity? = null
    private lateinit var mContext: Context
    lateinit var binding: FragmentCatsListBinding
    private var page: Int = 0
    private lateinit var catAdapter: CatListAdapter
    private val viewModel by lazy {
        ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catAdapter = CatListAdapter(listener)
        lifecycleScope.launch {
            viewModel.getImages().collectLatest {
                catAdapter.submitData(it)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        retainInstance = true
        mContext = context
        activity = context as? MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCatsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
    }

    fun setUi() {
        exitTransition =
            TransitionInflater.from(mContext).inflateTransition(R.transition.exit_shared_element)
        binding.rvList.apply {
            this.adapter = catAdapter.withLoadStateFooter(
                footer = CatLoadStateAdapter { catAdapter.retry() }
            )
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }
        // show the loading state for te first load
        catAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.btnRetry.visibility = View.GONE
                // Show ProgressBar
                binding.progressBar.visibility = View.VISIBLE
            } else {
                // Hide ProgressBar
                binding.progressBar.visibility = View.GONE
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        binding.btnRetry.visibility = View.VISIBLE
                        loadState.refresh as LoadState.Error
                    }
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(mContext, it.error.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    val listener = object : CatImageClickDelegate {
        override fun onClick(item: Cat?, imageView: ImageView) {
            if (!TextUtils.isEmpty(item?.id) && !TextUtils.isEmpty(item?.url)) {
                val action =
                    CatsListFragmentDirections.navigateToViewImage(item?.id ?: "", item?.url ?: "")
                val extras = FragmentNavigatorExtras(
                    imageView to "${item?.id}"
                )
                findNavController().navigate(action, extras)
            }
        }
    }
}
