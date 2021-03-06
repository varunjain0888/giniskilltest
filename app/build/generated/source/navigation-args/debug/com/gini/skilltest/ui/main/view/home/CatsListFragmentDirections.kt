package com.gini.skilltest.ui.main.view.home

import android.os.Bundle
import androidx.navigation.NavDirections
import com.gini.skilltest.R
import kotlin.Int
import kotlin.String

class CatsListFragmentDirections private constructor() {
  private data class NavigateToViewImage(
    val id: String,
    val url: String
  ) : NavDirections {
    override fun getActionId(): Int = R.id.navigate_to_view_image

    override fun getArguments(): Bundle {
      val result = Bundle()
      result.putString("id", this.id)
      result.putString("url", this.url)
      return result
    }
  }

  companion object {
    fun navigateToViewImage(id: String, url: String): NavDirections = NavigateToViewImage(id, url)
  }
}
