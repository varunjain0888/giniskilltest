package com.gini.skilltest.ui.main.view.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.gini.skilltest.databinding.FragmentViewFullImageBinding
import com.gini.skilltest.ui.main.view.MainActivity
import com.gini.skilltest.ui.main.view.home.deligates.ImageLoaderDelegate
import com.gini.skilltest.utils.hide
import com.gini.skilltest.utils.loadImage

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FullImageViewFragment : Fragment(), View.OnClickListener {
    private var activity: MainActivity? = null
    private lateinit var mContext: Context
    lateinit var binding: FragmentViewFullImageBinding
    val safeArgs: FullImageViewFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        activity = context as? MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewFullImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(binding.ivImage, safeArgs.id)
        postponeEnterTransition()
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

        setListeners()
        setUi()
    }

    fun setUi() {
        binding.ivImage.loadImage(safeArgs.url, object : ImageLoaderDelegate {
            override fun onLoadSuccess() {
                startPostponedEnterTransition()
                binding.progressBar.hide()
            }

            override fun onLoadFailed() {
                startPostponedEnterTransition()
            }
        })
    }

    fun setListeners() {

    }

    override fun onClick(v: View?) {
        when (v) {

        }
    }
}
