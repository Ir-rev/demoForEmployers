package com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.fabInfoAboutButtonController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;

import butterknife.BindView;
import butterknife.OnClick;

public class FabInfoAboutButtonController extends BaseController {

    public FabInfoAboutButtonController() {
    }

    @BindView(R.id.dialog_background)
    View backgroundContainer;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_info_about_button,container,false);
    }

    @OnClick(R.id.dialog_background)
    public void onClickContainer(){
        getRouter().handleBack();
    }
}
