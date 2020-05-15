package com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.exerciseSelectionController;

import com.example.trainingzonev4.controllers.baseControllers.expandableExampleAbstractController.ExpandableAbstractAdapter;
import com.example.trainingzonev4.controllers.baseControllers.expandableExampleAbstractController.ExpandableExampleAbstractController;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.AbstractDataProvider;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.DraggableSwipeableExampleAdapter;

public class ExerciseSelectionController extends ExpandableExampleAbstractController {

    private AbstractDataProvider provider;
    private DraggableSwipeableExampleAdapter myItemAdapter;

    public ExerciseSelectionController() {
    }

    public ExerciseSelectionController(DraggableSwipeableExampleAdapter myItemAdapter,AbstractDataProvider provider) {
        this.provider=provider;
        this.myItemAdapter=myItemAdapter;

    }

    @Override
    protected ExpandableAbstractAdapter createAdapter() {
        return new ExpandableAdapter(myItemAdapter,provider,getRouter(),new ExerciseExpandableDataProvider(getResources()));
    }


}


