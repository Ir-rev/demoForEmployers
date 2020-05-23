package com.example.trainingzonev4.controllers.gymnasticMenuController.squat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.example.trainingzonev4.R;
import com.example.trainingzonev4.realmDatabase.ExerciseImageAndNameDataClass;
import com.example.trainingzonev4.transform.fabTransform.TransitionChangeHandlerCompat;
import com.example.trainingzonev4.transform.swapAndOpenTransform.SwapAndOpenAbstractGridController;
import com.example.trainingzonev4.transform.swapAndOpenTransform.SwapAndOpenGridSharedElementTransitionChangeHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class SquatGridController extends SwapAndOpenAbstractGridController {


    @Override
    protected GymnasticModel[] getGymnasticModel() {

        return new GymnasticModel[] {
                new GymnasticModel(R.string.squats_level_1),
                new GymnasticModel(R.string.squats_level_2),
                new GymnasticModel(R.string.squats_level_3),
                new GymnasticModel(R.string.squats_level_4),
                new GymnasticModel(R.string.squats_level_5),
                new GymnasticModel(R.string.squats_level_6),
                new GymnasticModel(R.string.squats_level_7),
                new GymnasticModel(R.string.squats_level_8),
                new GymnasticModel(R.string.squats_level_9),
                new GymnasticModel(R.string.squats_level_10),
        };
    }


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_dot)
    ImageView imgDot;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private String title;
    private int dotColor;
    private int fromPosition;


    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        tvTitle.setText(title);
        imgDot.setImageDrawable(Objects.requireNonNull(getResources()).getDrawable(R.drawable.ic_squat));

        ViewCompat.setTransitionName(tvTitle, getResources().getString(R.string.transition_tag_title_indexed, fromPosition));
        ViewCompat.setTransitionName(imgDot, getResources().getString(R.string.transition_tag_dot_indexed, fromPosition));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setAdapter(new GymnasticsGridAdapter(LayoutInflater.from(view.getContext()), getGymnasticModel()));
    }


    public SquatGridController(String title, int dotColor, int fromPosition) {
        super(title, dotColor, fromPosition);
        this.title=title;
        this.dotColor=dotColor;
        this.fromPosition=fromPosition;
    }

    public SquatGridController(Bundle args) {
        super(args);
    }

    @Override
    protected void onModelRowClick(GymnasticModel model){
        String imageTransitionName = getResources().getString(R.string.transition_tag_image_named,
                getResources().getString(model.name));
        String titleTransitionName = getResources().getString(R.string.transition_tag_title_named,
                getResources().getString(model.name));

        List<String> names = new ArrayList<>();
        names.add(imageTransitionName);
        names.add(titleTransitionName);

        getRouter().pushController(RouterTransaction.with
                (new SquatDetailMenuController(ExerciseImageAndNameDataClass.getIntImageByName
                        (model.name,getResources()),
                getResources().getString(model.name)))
                .pushChangeHandler(new TransitionChangeHandlerCompat(new SwapAndOpenGridSharedElementTransitionChangeHandler(names), new FadeChangeHandler()))
                .popChangeHandler(new TransitionChangeHandlerCompat(new SwapAndOpenGridSharedElementTransitionChangeHandler(names), new FadeChangeHandler())));

    }



}
