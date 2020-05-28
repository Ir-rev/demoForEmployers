package com.example.trainingzonev4.controllers.startToWorkoutController.implementationExerciseListController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.dragDismissController.DragDismissController;
import com.example.trainingzonev4.controllers.videoFromYouTubeController.VideoFromYouTubeController;
import com.example.trainingzonev4.realmDatabase.ExerciseImageAndNameDataClass;

import butterknife.BindView;

public class ExerciseDescriptionsInWorkoutControllers extends DragDismissController {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_description)
    TextView tvDescriptions;
    @BindView(R.id.button_watch_video)
    Button buttonWatchVideo;

    private String name;

    public ExerciseDescriptionsInWorkoutControllers() {
    }

    public ExerciseDescriptionsInWorkoutControllers(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_exersice_descriptions_in_workout, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        if (this.name==null){
            return;
        }
        String name=this.name.replaceAll("\"","");
        textView.setText(name);
        imageView.setImageDrawable(getResources().getDrawable(ExerciseImageAndNameDataClass.getIntImageByName(name,getResources())));
        tvDescriptions.setText((ExerciseImageAndNameDataClass.getIntDescriptionsByName(name,getResources())));
        buttonWatchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRouter().pushController(
                        RouterTransaction.with(new VideoFromYouTubeController(getResources().getString(
                                ExerciseImageAndNameDataClass.getIntVideoByName(name,getResources())
                        )))
                                .pushChangeHandler(new HorizontalChangeHandler())
                                .popChangeHandler(new HorizontalChangeHandler()));
            }
        });
    }
}
