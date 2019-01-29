package com.example.potentialwaffle.util

import android.os.Bundle

interface ChangeFragmentListener {
  public fun onChangeFragment(fragmentName: String, fragmentBundle: Bundle)
}
