package com.gini.skilltest.ui.main.view.home

import android.os.Bundle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

data class FullImageViewFragmentArgs(
  val id: String,
  val url: String
) : NavArgs {
  fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("id", this.id)
    result.putString("url", this.url)
    return result
  }

  companion object {
    @JvmStatic
    fun fromBundle(bundle: Bundle): FullImageViewFragmentArgs {
      bundle.setClassLoader(FullImageViewFragmentArgs::class.java.classLoader)
      val __id : String?
      if (bundle.containsKey("id")) {
        __id = bundle.getString("id")
        if (__id == null) {
          throw IllegalArgumentException("Argument \"id\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"id\" is missing and does not have an android:defaultValue")
      }
      val __url : String?
      if (bundle.containsKey("url")) {
        __url = bundle.getString("url")
        if (__url == null) {
          throw IllegalArgumentException("Argument \"url\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"url\" is missing and does not have an android:defaultValue")
      }
      return FullImageViewFragmentArgs(__id, __url)
    }
  }
}
