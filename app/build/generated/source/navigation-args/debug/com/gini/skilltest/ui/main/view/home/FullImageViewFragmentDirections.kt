package com.gini.skilltest.ui.main.view.home

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.gini.skilltest.R

class FullImageViewFragmentDirections private constructor() {
  companion object {
    fun navigateToHome(): NavDirections = ActionOnlyNavDirections(R.id.navigate_to_home)
  }
}
