package com.example.trainingzonev4.transform.arcFadeMoveTransform;

import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.example.trainingzonev4.transform.fabTransform.TransitionChangeHandlerCompat;

public class ArcFadeMoveChangeHandlerCompat extends TransitionChangeHandlerCompat {

    public ArcFadeMoveChangeHandlerCompat() {
        super();
    }

    public ArcFadeMoveChangeHandlerCompat(String... transitionNames) {
        super(new ArcFadeMoveChangeHandler(transitionNames), new FadeChangeHandler());
    }

}
