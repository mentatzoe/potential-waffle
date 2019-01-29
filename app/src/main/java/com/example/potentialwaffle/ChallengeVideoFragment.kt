package com.example.potentialwaffle

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

class ChallengeVideoFragment : Fragment() {

  private var videoView: VideoView? = null
  private var mediaController: MediaController? = null
  private var uri: Uri? = null
  private var thisFragment: Context? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    arguments?.let {
      thisFragment = activity!!.applicationContext
      if (it.containsKey("uri")) {
        val packageName = activity!!.packageName
        val uriPath = "android.resource://$packageName/raw/${it.get("uri")}"
        uri = Uri.parse(uriPath)
      }
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    val rootView = inflater.inflate(R.layout.video_fragment, container, false)

    videoView = rootView.findViewById(R.id.video_view) as VideoView
    videoView!!.setMediaController(mediaController)
    videoView!!.setVideoURI(uri)
    videoView!!.setOnPreparedListener { onPrepared() }
    videoView!!.setOnCompletionListener { onCompletion() }

    mediaController!!.setAnchorView(videoView)
    mediaController!!.setMediaPlayer(videoView)

    return rootView
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)

    mediaController = MediaController(context)

  }

  override fun onStart() {
    super.onStart()
    initializePlayer()
  }

  override fun onStop() {
    super.onStop()
    releasePlayer()
  }

  private fun onPrepared() {
    videoView!!.requestFocus()
    videoView!!.start()
  }

  private fun initializePlayer() {
    videoView!!.requestFocus()
    videoView!!.start()
  }

  private fun releasePlayer() {
    videoView!!.stopPlayback()
  }

  private fun onCompletion() {
    fragmentManager?.popBackStackImmediate()
  }


}
