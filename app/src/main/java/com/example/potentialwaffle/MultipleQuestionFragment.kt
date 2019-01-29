package com.example.potentialwaffle

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import com.example.potentialwaffle.dummy.DummyContent
import com.example.potentialwaffle.util.ChangeFragmentListener
import kotlinx.android.synthetic.main.challenge_detail.view.*

/**
 * A fragment representing a single Challenge detail screen.
 * This fragment is either contained in a [ChallengeListActivity]
 * in two-pane mode (on tablets) or a [ChallengeDetailActivity]
 * on handsets.
 */
class MultipleQuestionFragment : Fragment(), View.OnClickListener {

  /**
   * The dummy content this fragment is presenting.
   */
  private var item: DummyContent.DummyItem? = null
  private var aListener: ChangeFragmentListener? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      if (it.containsKey(ARG_ITEM_ID)) {
        // Load the dummy content specified by the fragment
        // arguments. In a real-world scenario, use a Loader
        // to load content from a content provider.
        item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
      }
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    //todo: inflate a specific fragment depending on the type of question
    val rootView = inflater.inflate(R.layout.challenge_detail, container, false)

    // Show the dummy content as text in a TextView.
    item?.let {
      rootView.challenge_detail.text = it.details
    }

    val videoImageButton = rootView.video_button as ImageButton
    videoImageButton?.setOnClickListener(this)

    return rootView
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    aListener = context as? ChangeFragmentListener
    if (aListener == null) {
      throw ClassCastException("$context must implement OnArticleSelectedListener")
    }
  }

  override fun onClick(v: View?) {
    when (v?.id) {
      R.id.video_button -> {
        val args = Bundle()
        args.putString("uri", "funn")
        aListener?.onChangeFragment("ChallengeVideoFragment", args)
      }
      else -> {

      }
    }
  }

  companion object {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    const val ARG_ITEM_ID = "item_id"
  }
}
