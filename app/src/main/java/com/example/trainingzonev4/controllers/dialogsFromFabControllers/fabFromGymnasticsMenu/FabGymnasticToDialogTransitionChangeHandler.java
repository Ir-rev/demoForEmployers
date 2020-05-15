package com.example.trainingzonev4.controllers.dialogsFromFabControllers.fabFromGymnasticsMenu;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.transition.Fade;
import androidx.transition.Transition;
import androidx.transition.TransitionSet;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.abstractFabToDialog.FabAbstractToDialogTransitionChangeHandler;
import com.example.trainingzonev4.transform.fabTransform.FabTransform;

public class FabGymnasticToDialogTransitionChangeHandler extends FabAbstractToDialogTransitionChangeHandler {

    @Override
    @NonNull
    protected Transition getTransition(@NonNull final ViewGroup container, @Nullable final View from, @Nullable final View to, boolean isPush) {
        Transition backgroundFade = new Fade();
        backgroundFade.addTarget(R.id.dialog_background);

        Transition fabTransform = new FabTransform(ContextCompat.getColor(container.getContext(), R.color.colorAccent), R.drawable.ic_fitness_center_black_24dp);

        TransitionSet set = new TransitionSet();
        set.addTransition(backgroundFade);
        set.addTransition(fabTransform);

        return set;
    }
}
