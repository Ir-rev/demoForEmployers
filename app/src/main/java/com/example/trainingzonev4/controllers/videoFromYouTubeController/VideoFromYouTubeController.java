package com.example.trainingzonev4.controllers.videoFromYouTubeController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;

public class VideoFromYouTubeController extends BaseController {

    @BindView(R.id.youtube_player_view)
    YouTubePlayerView youTubePlayerView;


    public VideoFromYouTubeController() {
    }

    private String idVideo;

    public VideoFromYouTubeController(String idVideo) {
        this.idVideo = idVideo;
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_video_from_you_tube, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
    }

    @Override
    protected void onDetach(@NonNull View view) {
        super.onDetach(view);
        youTubePlayerView.release();
    }
}
