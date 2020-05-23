package com.example.trainingzonev4.controllers.startToWorkoutController.implementationExerciseListController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.realm.ExerciseImageAndNameDataClass;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.OnClick;

public class ProcessOfPerformanceExerciseController extends BaseController {

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_process_of_performance_exercise,container,false);
    }

    @BindView(R.id.textView)
    TextView textViewName;
    @BindView(R.id.imageView)
    ImageView imageViewExercise;
    @BindView(R.id.imageButton)
    ImageButton imageButtonAccept;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    public ProcessOfPerformanceExerciseController() {
    }

    private LinkedList<ImplementationExerciseListController.ExerciseListData> exerciseArrayList;
    private int index;

    public ProcessOfPerformanceExerciseController(LinkedList<ImplementationExerciseListController.ExerciseListData> exerciseArrayList, int index) {
        this.exerciseArrayList = exerciseArrayList;
        this.index = index;
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        textViewName.setText(exerciseArrayList.get(index).name+" x "+exerciseArrayList.get(index).times);
        imageViewExercise.setImageDrawable(getResources().getDrawable
                (ExerciseImageAndNameDataClass.getIntImageByName(exerciseArrayList.get(index).name,getResources())));
        progressBar.setMax(exerciseArrayList.size());
        progressBar.setProgress(index);


    }
    @OnClick(R.id.imageButton)
    public void buttonAcceptOnClick(){

        if (index<exerciseArrayList.size()-1){


            getRouter().pushController(
                    RouterTransaction.with(new ProcessOfPerformanceExerciseController(exerciseArrayList,index+1))
                            .pushChangeHandler(new HorizontalChangeHandler())
                            .popChangeHandler(new HorizontalChangeHandler()));

        }else {
            getRouter().pushController(
                    RouterTransaction.with(new EndTheExerciseListController())
                            .pushChangeHandler(new HorizontalChangeHandler())
                            .popChangeHandler(new HorizontalChangeHandler()));

        }

    }
}
