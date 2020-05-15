package com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.exerciseListSwipeController;

import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.ExampleDataProvider;

import java.util.ArrayList;

public class ExerciseDataProvider extends ExampleDataProvider {

    public ExerciseDataProvider() {
    }


    @Override
    protected ArrayList<String> getListWithData() {
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Пример текста");
        return arrayList;
    }

}
