package com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.exerciseListSwipeController;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.AbstractDataProvider;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.DraggableSwipeableExampleAdapter;

public class ExerciseListSwipeAdapter extends DraggableSwipeableExampleAdapter {


    public ExerciseListSwipeAdapter(AbstractDataProvider dataProvider) {
        super(dataProvider);
    }

    @Override
    protected int getSwipeLeftImage() {
        return R.drawable.bg_swipe_item_left;
    }

    @Override
    protected int getSwipeRightImage() {
        return R.drawable.bg_swipe_item_right;
    }

}
