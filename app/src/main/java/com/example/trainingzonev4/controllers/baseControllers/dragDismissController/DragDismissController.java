package com.example.trainingzonev4.controllers.baseControllers.dragDismissController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.controllers.baseControllers.dragDismissController.unit.ElasticDragDismissFrameLayout;
import com.example.trainingzonev4.transform.scaleFadeChangeHandler.ScaleFadeChangeHandler;

public abstract class DragDismissController extends BaseController {

    private final ElasticDragDismissFrameLayout.ElasticDragDismissCallback dragDismissListener
            = new ElasticDragDismissFrameLayout.ElasticDragDismissCallback() {

        @Override
        public void onDragDismissed() {
            overridePopHandler(new ScaleFadeChangeHandler());
            getRouter().popController(DragDismissController.this);

        }

    };

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_drag_dismiss, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        ((ElasticDragDismissFrameLayout) view).addListener(dragDismissListener);
    }

    @Override
    protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);

        ((ElasticDragDismissFrameLayout) view).removeListener(dragDismissListener);
    }

    @Override
    protected String getTitle() {
        return "Drag to Dismiss";
    }
}


