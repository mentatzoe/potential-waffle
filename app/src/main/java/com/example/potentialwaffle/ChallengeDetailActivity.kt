package com.example.potentialwaffle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.potentialwaffle.util.ChangeFragmentListener

/**
 * An activity representing a single Challenge detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ChallengeListActivity].
 */
class ChallengeDetailActivity : AppCompatActivity(), ChangeFragmentListener {

  val fragmentManager = supportFragmentManager
  val fragmentTransaction = fragmentManager.beginTransaction()
  val FRAGMENT_CONTAINER = R.id.challenge_detail_container

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_challenge_detail)

    // savedInstanceState is non-null when there is fragment state
    // saved from previous configurations of this activity
    // (e.g. when rotating the screen from portrait to landscape).
    // In this case, the fragment will automatically be re-added
    // to its container so we don't need to manually add it.
    // For more information, see the Fragments API guide at:
    //
    // http://developer.android.com/guide/components/fragments.html
    //
    if (savedInstanceState == null) {
      // Create the detail fragment and add it to the activity
      // using a fragment transaction.
      val fragment = ChallengeDetailFragment().apply {
        arguments = Bundle().apply {
          putString(
            ChallengeDetailFragment.ARG_ITEM_ID,
            intent.getStringExtra(ChallengeDetailFragment.ARG_ITEM_ID)
          )
        }
      }

      supportFragmentManager.beginTransaction()
        .add(R.id.challenge_detail_container, fragment)
        .commit()
    }
  }

  override fun onOptionsItemSelected(item: MenuItem) =
    when (item.itemId) {
      android.R.id.home -> {
        // This ID represents the Home or Up button. In the case of this
        // activity, the Up button is shown. For
        // more details, see the Navigation pattern on Android Design:
        //
        // http://developer.android.com/design/patterns/navigation.html#up-vs-back

        navigateUpTo(Intent(this, ChallengeListActivity::class.java))
        true
      }
      else -> super.onOptionsItemSelected(item)
    }

  override fun onChangeFragment(fragmentName: String, fragmentBundle: Bundle) {
    val newFragment = Class.forName("com.example.potentialwaffle.$fragmentName").newInstance() as Fragment

    if (!fragmentBundle.isEmpty) {
      newFragment.arguments = fragmentBundle
    }

    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(FRAGMENT_CONTAINER, newFragment)
    transaction.addToBackStack(null)
    transaction.commit()
  }
}
