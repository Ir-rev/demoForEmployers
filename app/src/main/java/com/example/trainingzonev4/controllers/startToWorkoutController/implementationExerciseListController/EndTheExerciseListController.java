package com.example.trainingzonev4.controllers.startToWorkoutController.implementationExerciseListController;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.controllers.homeControllers.HomeMenuController;

import java.util.Objects;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.rxjava3.core.Flowable;
import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class EndTheExerciseListController extends BaseController {

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_end_the_exercese,container,false);
    }

    public EndTheExerciseListController() {
    }

    @BindView(R.id.viewKonfetti)
    KonfettiView konfettiView;
    @BindView(R.id.button)
    Button button;

    private void konfettiViewStart(){

        /*
        KonfettiView generate some bug on the tablets. idk what is it
        will fix it. 16.05.2020
         */

        try {

            konfettiView.build()
                    .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                    .setDirection(0.0, 359.0)
                    .setSpeed(1f, 5f)
                    .setFadeOutEnabled(true)
                    .setTimeToLive(2000L)
                    .addShapes(Shape.Square.INSTANCE, Shape.Circle.INSTANCE)
                    .addSizes(new Size(12, 5f))
                    .setPosition(-50f, konfettiView.getWidth() + 50f, -50f, -50f)
                    .streamFor(300, 5000L);

        }catch (Throwable e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        /*
        KonfettiView generate some bug on the tablets. idk what is it
        will fix it. 16.05.2020
         */
        Flowable.timer(500, TimeUnit.MILLISECONDS).subscribe(v->konfettiViewStart()); //fix animation's bag



    }

    @OnClick(R.id.button)
    public void buttonOnMainOnclick(){
        getRouter().pushController(
                RouterTransaction.with(new HomeMenuController())
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new HorizontalChangeHandler()));

    }

}
