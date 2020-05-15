package com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.chooseTimesExerciseController;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.AbstractDataProvider;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.DraggableSwipeableExampleAdapter;

import java.util.Objects;

import butterknife.BindView;

public class ChooseTimesExerciseController extends BaseController {

    @BindView(R.id.button_add_exercise)
    Button buttonAddExercise;
    @BindView(R.id.exercise_time_edit_text)
    TextView exerciseTimeEditText;


    private AbstractDataProvider provider;
    private DraggableSwipeableExampleAdapter myItemAdapter;
    private TextView textView;

    public ChooseTimesExerciseController() {
    }

    public ChooseTimesExerciseController(AbstractDataProvider provider, DraggableSwipeableExampleAdapter myItemAdapter, TextView textView) {
        this.provider = provider;
        this.myItemAdapter = myItemAdapter;
        this.textView=textView;

    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_choose_exercise, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        buttonAddExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStringInt(String.valueOf(exerciseTimeEditText.getText()))){

                    provider.addItemWithName(String.valueOf(textView.getText()),
                            Integer.parseInt(String.valueOf(exerciseTimeEditText.getText())));

                    myItemAdapter.onSwipeItemStarted(new DraggableSwipeableExampleAdapter.MyViewHolder(textView), 0);

                    InputMethodManager inputMethodManager = (InputMethodManager)
                            Objects.requireNonNull(getActivity()).getSystemService(Context.INPUT_METHOD_SERVICE);

                    assert inputMethodManager != null;
                    inputMethodManager.hideSoftInputFromWindow(buttonAddExercise.getWindowToken(), 0);

                    getRouter().handleBack();

                }else{
                    exerciseTimeEditText.setBackgroundColor(Color.RED);
                }
            }
        });

    }

    private boolean isStringInt(String text){
        if (text==null) return false;
        int resolt;
        try {
            resolt=Integer.parseInt(text);
            return true;
        }catch (Throwable e){
            return false;
        }
    }
}
