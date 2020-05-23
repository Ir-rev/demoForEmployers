package com.example.trainingzonev4.transform.swapAndOpenTransform;

import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.realm.ExerciseImageAndNameDataClass;
import com.example.trainingzonev4.util.BundleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class SwapAndOpenAbstractGridController extends BaseController {

    private static final String KEY_TITLE = "SwapAndOpenAbstractGridController.name";
    private static final String KEY_DOT_COLOR = "SwapAndOpenAbstractGridController.dotColor";
    private static final String KEY_FROM_POSITION = "SwapAndOpenAbstractGridController.position";

    private GymnasticModel[] GYMNASTIC_MODELS = new GymnasticModel[] {
            new GymnasticModel( R.string.push_ups_level_1),
            new GymnasticModel( R.string.push_ups_level_1)
    };

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.img_dot)
    ImageView imgDot;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private String title;
    private int dotColor;
    private int fromPosition;

    public SwapAndOpenAbstractGridController(String title, int dotColor, int fromPosition) {
        this(new BundleBuilder(new Bundle())
                .putString(KEY_TITLE, title)
                .putInt(KEY_DOT_COLOR, dotColor)
                .putInt(KEY_FROM_POSITION, fromPosition)
                .build());
        if(GYMNASTIC_MODELS!=null){
            GYMNASTIC_MODELS =getGymnasticModel();
        }
    }


    public SwapAndOpenAbstractGridController(Bundle args) {
        super(args);
        title = getArgs().getString(KEY_TITLE);
        dotColor = getArgs().getInt(KEY_DOT_COLOR);
        fromPosition = getArgs().getInt(KEY_FROM_POSITION);
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_swap_grid, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        tvTitle.setText(title);
        imgDot.getDrawable().setColorFilter(ContextCompat.getColor(getActivity(), dotColor), Mode.SRC_ATOP);

        ViewCompat.setTransitionName(tvTitle, getResources().getString(R.string.transition_tag_title_indexed, fromPosition));
        ViewCompat.setTransitionName(imgDot, getResources().getString(R.string.transition_tag_dot_indexed, fromPosition));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        recyclerView.setAdapter(new GymnasticsGridAdapter(LayoutInflater.from(view.getContext()), GYMNASTIC_MODELS));
    }

    @Override
    protected String getTitle() {
        return "Shared Element Demos";
    }


    protected abstract void onModelRowClick(GymnasticModel model);

    protected abstract GymnasticModel[] getGymnasticModel();


    protected class GymnasticsGridAdapter extends RecyclerView.Adapter<GymnasticsGridAdapter.ViewHolder> {

        private final LayoutInflater inflater;
        private final GymnasticModel[] items;

        public GymnasticsGridAdapter(LayoutInflater inflater, GymnasticModel[] items) {
            this.inflater = inflater;
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.row_exercise_grid, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(items[position]);
        }

        @Override
        public int getItemCount() {
            return items.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_title)
            TextView textView;
            @BindView(R.id.img_city)
            ImageView imageView;
            private GymnasticModel model;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }

            void bind(GymnasticModel item) {
                model = item;
                imageView.setImageDrawable(
                        (getResources().getDrawable
                                (ExerciseImageAndNameDataClass.getIntImageByName
                                (getResources().getString(item.name),getResources()))));
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setText(getResources().getString(item.name));

                ViewCompat.setTransitionName(textView, getResources().getString(R.string.transition_tag_title_named
                        , getResources().getString(model.name)));
                ViewCompat.setTransitionName(imageView, getResources().getString(R.string.transition_tag_image_named,
                        getResources().getString(model.name)));
            }

            @OnClick(R.id.row_root)
            void onRowClick() {
                onModelRowClick(model);
            }

        }
    }

    protected static class GymnasticModel {
        public int name;

        public GymnasticModel(int name) {
            this.name = name;
        }
    }
}
