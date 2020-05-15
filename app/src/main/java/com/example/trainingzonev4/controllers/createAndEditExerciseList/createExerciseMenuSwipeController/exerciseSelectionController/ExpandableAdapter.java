package com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.exerciseSelectionController;

import android.widget.TextView;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.expandableExampleAbstractController.ExampleAbstractExpandableDataProvider;
import com.example.trainingzonev4.controllers.baseControllers.expandableExampleAbstractController.ExpandableAbstractAdapter;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.AbstractDataProvider;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.DraggableSwipeableExampleAdapter;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.chooseTimesExerciseController.ChooseTimesExerciseController;

public class ExpandableAdapter extends ExpandableAbstractAdapter {

    private Router router;
    private AbstractDataProvider provider;
    private DraggableSwipeableExampleAdapter myItemAdapter;

    public ExpandableAdapter(DraggableSwipeableExampleAdapter myItemAdapter,AbstractDataProvider provider, Router router, ExampleAbstractExpandableDataProvider exampleAbstractExpandableDataProvider) {
        super(exampleAbstractExpandableDataProvider);
        this.router=router;
        this.provider=provider;
        this.myItemAdapter=myItemAdapter;
    }

    public ExpandableAdapter() {
        super();
    }

    @Override
    protected int getCloseFolderColor() {
        return R.drawable.bg_group_item_normal_state;
    }

    @Override
    protected int getOpenFolderColor() {
        return R.drawable.bg_group_item_expanded_state;
    }

    @Override
    protected int getElementsColor() {
        return R.drawable.bg_item_normal_state;
    }

    @Override
    protected void onItemClick(MyChildViewHolder holder, TextView textView) {

        router.pushController(
                RouterTransaction.with(new ChooseTimesExerciseController(provider, myItemAdapter,textView))
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new HorizontalChangeHandler()));
    }
}
