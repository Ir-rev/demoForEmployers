package com.example.trainingzonev4.controllers.homeControllers;

import android.content.Intent;
import android.graphics.PorterDuff.Mode;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeType;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.CreateAndEditExerciseListController;
import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.controllers.baseControllers.abstractFabToDialog.util.DialogFromFabController;
import com.example.trainingzonev4.controllers.dialogsFromFabControllers.fabFromHomeMenu.FabHomeToDialogTransitionChangeHandler;
import com.example.trainingzonev4.controllers.gymnasticMenuController.GymnasticMenuController;
import com.example.trainingzonev4.controllers.startToWorkoutController.StartToWorkoutController;
import com.example.trainingzonev4.transform.fabTransform.TransitionChangeHandlerCompat;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeMenuController extends BaseController {

    private enum DemoModel {

        START_TO_WORKOUT(R.string.start_to_workout, R.color.red_300),
        GYMNASTIC_LIST(R.string.exercise_description, R.color.red_300),
        EXERCISE_LIST_CREATE(R.string.my_exercise_list, R.color.red_300),
        ;

        int title;
        @ColorRes
        int color;

        DemoModel(int title, @ColorRes int color) {
            this.title = title;
            this.color = color;
        }
    }

    private static final String KEY_FAB_VISIBILITY = "HomeMenuController.fabVisibility";

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    View fab;

    public HomeMenuController() {
        setHasOptionsMenu(true);
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_home_menu, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new HomeAdapter(LayoutInflater.from(view.getContext()), DemoModel.values()));
    }

    @Override
    protected void onSaveViewState(@NonNull View view, @NonNull Bundle outState) {
        super.onSaveViewState(view, outState);
        outState.putInt(KEY_FAB_VISIBILITY, fab.getVisibility());
    }

    @Override
    protected void onRestoreViewState(@NonNull View view, @NonNull Bundle savedViewState) {
        super.onRestoreViewState(view, savedViewState);

        //noinspection WrongConstant
        fab.setVisibility(savedViewState.getInt(KEY_FAB_VISIBILITY));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home, menu);
    }

    @Override
    protected void onChangeStarted(@NonNull ControllerChangeHandler changeHandler, @NonNull ControllerChangeType changeType) {
        setOptionsMenuHidden(!changeType.isEnter);

        if (changeType.isEnter) {
            setTitle();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about) {
            onFabClicked(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected String getTitle() {
        return "Conductor Demos";
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        onFabClicked(true);
    }

    private void onFabClicked(boolean fromFab) {
        SpannableString details = new SpannableString(Objects.requireNonNull(getResources()).getString(R.string.send_questions));
        details.setSpan(new AbsoluteSizeSpan(16, true), 0, details.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        final String url = Objects.requireNonNull(getResources()).getString(R.string.my_vk_url);
        SpannableString link = new SpannableString(url);
        link.setSpan(new URLSpan(url) {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        }, 0, link.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        SpannableStringBuilder description = new SpannableStringBuilder();
        description.append(details);
        description.append("\n\n");
        description.append(link);

        ControllerChangeHandler pushHandler = fromFab ? new TransitionChangeHandlerCompat
                (new FabHomeToDialogTransitionChangeHandler(), new FadeChangeHandler(false)) : new FadeChangeHandler(false);
        ControllerChangeHandler popHandler = fromFab ? new TransitionChangeHandlerCompat
                (new FabHomeToDialogTransitionChangeHandler(), new FadeChangeHandler()) : new FadeChangeHandler();

        getRouter()
                .pushController(RouterTransaction.with(new DialogFromFabController
                        (Objects.requireNonNull(getResources()).getString(R.string.info), description))
                        .pushChangeHandler(pushHandler)
                        .popChangeHandler(popHandler));

    }

    void onModelRowClick(DemoModel model, int position) {
        switch (model) {
            case START_TO_WORKOUT:
                getRouter().pushController(
                        RouterTransaction.with(new StartToWorkoutController())
                                .pushChangeHandler(new HorizontalChangeHandler())
                                .popChangeHandler(new HorizontalChangeHandler()));
                break;

            case GYMNASTIC_LIST:
                getRouter().pushController(
                        RouterTransaction.with(new GymnasticMenuController())
                                .pushChangeHandler(new HorizontalChangeHandler())
                                .popChangeHandler(new HorizontalChangeHandler()));
                break;
            case EXERCISE_LIST_CREATE:
                getRouter().pushController(
                        RouterTransaction.with(new CreateAndEditExerciseListController())
                                .pushChangeHandler(new HorizontalChangeHandler())
                                .popChangeHandler(new HorizontalChangeHandler()));
                break;
        }
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

        private final LayoutInflater inflater;
        private final DemoModel[] items;

        public HomeAdapter(LayoutInflater inflater, DemoModel[] items) {
            this.inflater = inflater;
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.row_home, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(position, items[position]);
        }

        @Override
        public int getItemCount() {
            return items.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_title)
            TextView tvTitle;
            @BindView(R.id.img_dot)
            ImageView imgDot;
            private DemoModel model;
            private int position;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            void bind(int position, DemoModel item) {
                model = item;
                tvTitle.setText(getResources().getString(item.title));
                this.position = position;

                switch (item) {
                    case START_TO_WORKOUT:
                        imgDot.setImageDrawable(Objects.requireNonNull(getResources()).getDrawable(R.drawable.ic_hand));
                        break;
                    case GYMNASTIC_LIST:
                        imgDot.setImageDrawable(Objects.requireNonNull(getResources()).getDrawable(R.drawable.ic_description_exercise));
                        break;
                    case EXERCISE_LIST_CREATE:
                        imgDot.setImageDrawable(Objects.requireNonNull(getResources()).getDrawable(R.drawable.ic_plus_vector_icon));
                        break;
                    default:
                        imgDot.getDrawable().setColorFilter(ContextCompat.getColor(getActivity(), item.color), Mode.SRC_ATOP);
                        break;
                }

                ViewCompat.setTransitionName(tvTitle, getResources().getString(R.string.transition_tag_title_indexed, position));
                ViewCompat.setTransitionName(imgDot, getResources().getString(R.string.transition_tag_dot_indexed, position));
            }

            @OnClick(R.id.row_root)
            void onRowClick() {
                onModelRowClick(model, position);
            }

        }
    }
}


