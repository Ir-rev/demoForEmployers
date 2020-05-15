package com.example.trainingzonev4.controllers.gymnasticMenuController.squat;

import android.content.res.Resources;
import android.os.Bundle;

import com.bluelinelabs.conductor.Router;
import com.example.trainingzonev4.dataClasses.ExerciseImageAndNameDataClass;
import com.example.trainingzonev4.transform.swapAndOpenTransform.SwapAndOpenDetailController;

public class SquatDetailMenuController extends SwapAndOpenDetailController {


    private static String[] LIST_ROWS = new String[] {};


    public SquatDetailMenuController(int imageDrawableRes, String title) {
        super(imageDrawableRes, title);
    }




    @Override
    protected void selectDescription(String title) {

        LIST_ROWS = new String[]{getResources().getString(ExerciseImageAndNameDataClass.getIntDescriptionsByName(title,getResources()))};
    }

    @Override
    protected String[] getListRows() {
        return LIST_ROWS;
    }

    public SquatDetailMenuController(Bundle args) {
        super(args);
    }
}
