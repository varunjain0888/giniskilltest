package com.gini.skilltest.ui.main.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gini.skilltest.databinding.FragmentCatsListBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class TemplateFragment : Fragment(), View.OnClickListener {
    private var activity: MainActivity? = null
    private lateinit var mContext: Context
    lateinit var binding: FragmentCatsListBinding

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
        binding = FragmentCatsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setUi()
    }

    fun setUi() {

    }

    fun setListeners() {

    }

    override fun onClick(v: View?) {
        when (v) {

        }
    }
}
