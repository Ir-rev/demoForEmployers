package com.example.trainingzonev4.controllers.gymnasticMenuController.tightening;

import android.os.Bundle;

import com.example.trainingzonev4.realmDatabase.ExerciseImageAndNameDataClass;
import com.example.trainingzonev4.transform.swapAndOpenTransform.SwapAndOpenDetailController;

public class TighteningDetailMenuController extends SwapAndOpenDetailController {


    private static String[] LIST_ROWS = new String[]{};


    public TighteningDetailMenuController(int imageDrawableRes, String title) {
        super(imageDrawableRes, title);
        selectDescription(title);
    }


    @Override
    protected void selectDescription(String title) {

        LIST_ROWS = new String[]{(ExerciseImageAndNameDataClass.getIntDescriptionsByName(title,getResources()))};
    }


    @Override
    protected String[] getListRows() {
        return LIST_ROWS;
    }

    public TighteningDetailMenuController(Bundle args) {
        super(args);
    }
}
