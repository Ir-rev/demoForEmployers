package com.example.trainingzonev4.controllers.videoFromYouTubeController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;

public class VideoFromYouTubeController extends BaseController {

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_video_from_you_tube,container,false);
    }
}
