package com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.exerciseListSwipeController;

import com.bluelinelabs.conductor.Router;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.AbstractListSwipeController;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.DraggableSwipeableExampleAdapter;


public class ExerciseListSwipeController extends AbstractListSwipeController {

//    private Router router;
//    public ExerciseListSwipeController(Router router){
//        this.router=router;
//    }

    public ExerciseListSwipeController(){

    }

    private DraggableSwipeableExampleAdapter myItemAdapter;

    public DraggableSwipeableExampleAdapter getMyItemAdapter() {
        return myItemAdapter;
    }

    @Override
    protected DraggableSwipeableExampleAdapter draggableSwipeableSetEventListener(){
        myItemAdapter = new ExerciseListSwipeAdapter(new ExerciseDataProvider());

        return myItemAdapter;
    }

}
