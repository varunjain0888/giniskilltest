// Generated by view binder compiler. Do not edit!
package com.gini.skilltest.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import com.gini.skilltest.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ContentBaseBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final HeaderBinding includeHeader;

  private ContentBaseBinding(@NonNull ConstraintLayout rootView,
      @NonNull HeaderBinding includeHeader) {
    this.rootView = rootView;
    this.includeHeader = includeHeader;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ContentBaseBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ContentBaseBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.content_base, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ContentBaseBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    String missingId;
    missingId: {
      View includeHeader = rootView.findViewById(R.id.includeHeader);
      if (includeHeader == null) {
        missingId = "includeHeader";
        break missingId;
      }
      HeaderBinding includeHeaderBinding = HeaderBinding.bind(includeHeader);
      return new ContentBaseBinding((ConstraintLayout) rootView, includeHeaderBinding);
    }
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}