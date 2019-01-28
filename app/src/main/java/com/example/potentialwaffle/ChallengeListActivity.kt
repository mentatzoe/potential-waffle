package com.example.potentialwaffle

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.NavUtils
import androidx.appcompat.app.ActionBar
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager

import com.example.potentialwaffle.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_challenge_list.*
import kotlinx.android.synthetic.main.challenge_list_content.view.*
import kotlinx.android.synthetic.main.challenge_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ChallengeDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ChallengeListActivity : AppCompatActivity() {

  /**
   * Whether or not the activity is in two-pane mode, i.e. running on a tablet
   * device.
   */
  private var twoPane: Boolean = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_challenge_list)


    // Show the Up button in the action bar.
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    if (challenge_detail_container != null) {
      // The detail container view will be present only in the
      // large-screen layouts (res/values-w900dp).
      // If this view is present, then the
      // activity should be in two-pane mode.
      twoPane = true
    }

    setupRecyclerView(challenge_list)
  }

  override fun onOptionsItemSelected(item: MenuItem) =
    when (item.itemId) {
      android.R.id.home -> {
        // This ID represents the Home or Up button. In the case of this
        // activity, the Up button is shown. Use NavUtils to allow users
        // to navigate up one level in the application structure. For
        // more details, see the Navigation pattern on Android Design:
        //
        // http://developer.android.com/design/patterns/navigation.html#up-vs-back
        NavUtils.navigateUpFromSameTask(this)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  private fun setupRecyclerView(recyclerView: RecyclerView) {

    recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, twoPane)
    recyclerView.layoutManager = GridLayoutManager(this, 3)
  }

  class SimpleItemRecyclerViewAdapter(
    private val parentActivity: ChallengeListActivity,
    private val values: List<DummyContent.DummyItem>,
    private val twoPane: Boolean
  ) :
    RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
      onClickListener = View.OnClickListener { v ->
        val item = v.tag as DummyContent.DummyItem
          //TODO: Implement lock/unlock check here and navigate to pertinent activity
          val intent = Intent(v.context, ChallengeDetailActivity::class.java).apply {
            putExtra(ChallengeDetailFragment.ARG_ITEM_ID, item.id)
          }
          v.context.startActivity(intent)
      }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater.from(parent.context)
        .inflate(R.layout.challenge_list_content, parent, false)
      return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val item = values[position]
      //TODO: Configuration of each item within the recyclerview
      //holder.idView.text = item.id
      //holder.contentView.text = item.content

      with(holder.itemView) {
        tag = item
        setOnClickListener(onClickListener)
      }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
      //TODO: Configuration of each item within the recyclerview
      //val idView: TextView = view.id_text
      //val contentView: TextView = view.content
    }
  }
}
