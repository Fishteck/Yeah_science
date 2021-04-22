package android.example.yeahscience.ui.main

import android.example.yeahscience.R
import android.example.yeahscience.data.nasa.models.NASAFrameToday
import android.example.yeahscience.utils.MediaType
import android.example.yeahscience.utils.Resource
import android.example.yeahscience.utils.showToast
import android.example.yeahscience.utils.takeAndSetImage
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*
import java.util.*


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val vmPhoto : NASAPhotoTodayViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
    }

    private fun initObserver() {
        vmPhoto.photoToday.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data != null) {
                        setContent(it.data)
                        fragment_main_constraint_layout.visibility = View.VISIBLE
                        fragment_main_progress.visibility = View.GONE
                    }
                }
                Resource.Status.ERROR -> {
                    showToast(it.message)
                }
                Resource.Status.LOADING ->{
                    fragment_main_constraint_layout.visibility = View.GONE
                    fragment_main_progress.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setContent(data: NASAFrameToday) {

            when (data.mediaType) {
                MediaType.IMAGE.name.toLowerCase(Locale.ROOT) -> {
                    fragment_main_today_image.visibility = View.VISIBLE
                    fragment_main_today_video.visibility = View.GONE
                    fragment_main_today_image.takeAndSetImage(
                        data.photoUrl,  RoundedCornersTransformation(
                            30,
                            5
                        )
                    )
                }
                MediaType.VIDEO.name.toLowerCase(Locale.ROOT) -> {
                    fragment_main_today_image.visibility = View.GONE
                    fragment_main_today_video.visibility = View.VISIBLE
                    takeAndSetVideo()
                }
            }

        fragment_main_today_title.text = data.title
        fragment_main_today_explanation.text = data.explanation
    }

    private fun takeAndSetVideo() {
        val youTubePlayerView: YouTubePlayerView = fragment_main_today_video

        youTubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo("rQcKIN9vj3U", 0f)
            }
        })
    }


}