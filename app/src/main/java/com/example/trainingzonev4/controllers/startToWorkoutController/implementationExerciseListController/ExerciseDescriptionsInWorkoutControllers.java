package com.example.trainingzonev4.controllers.startToWorkoutController.implementationExerciseListController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.dragDismissController.DragDismissController;
import com.example.trainingzonev4.dataClasses.ExerciseImageAndNameDataClass;

import butterknife.BindView;

public class ExerciseDescriptionsInWorkoutControllers extends DragDismissController {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_description)
    TextView tvDescriptions;

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
        String name=this.name.replaceAll("\"","");
        textView.setText(name);
        imageView.setImageDrawable(getResources().getDrawable(ExerciseImageAndNameDataClass.getIntImageByName(name,getResources())));
        tvDescriptions.setText(getResources().getString(ExerciseImageAndNameDataClass.getIntDescriptionsByName(name,getResources())));
    }
}
