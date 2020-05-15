package com.example.trainingzonev4.controllers.gymnasticMenuController.pushUps;

import android.content.res.Resources;
import android.os.Bundle;

import com.example.trainingzonev4.dataClasses.ExerciseImageAndNameDataClass;
import com.example.trainingzonev4.transform.swapAndOpenTransform.SwapAndOpenDetailController;

public class PushUpsDetailMenuController extends SwapAndOpenDetailController {


    private static String[] LIST_ROWS = new String[]{"стандартный текст", "пример текста 2"};

    private Resources resources;

    public PushUpsDetailMenuController(int imageDrawableRes, String title,Resources resources) {
        super(imageDrawableRes, title);
        this.resources=resources;
    }


    @Override
    protected void selectDescription(String title) {

        LIST_ROWS = new String[]{getResources().getString(ExerciseImageAndNameDataClass.getIntDescriptionsByName(title,getResources()))};
    }

    @Override
    protected String[] getListRows() {
        return LIST_ROWS;
    }

    public PushUpsDetailMenuController(Bundle args) {
        super(args);
    }
}
