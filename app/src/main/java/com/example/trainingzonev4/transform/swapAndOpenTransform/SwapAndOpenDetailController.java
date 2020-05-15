package com.example.trainingzonev4.transform.swapAndOpenTransform;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.util.BundleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class SwapAndOpenDetailController extends BaseController {

    private static final String KEY_TITLE = "SwapAndOpenDetailController.name";
    private static final String KEY_IMAGE = "SwapAndOpenDetailController.image";

    private static String[] LIST_ROWS = new String[] {
            "пример", "текста"
    };

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @DrawableRes
    private int imageDrawableRes;
    private String title;

    public SwapAndOpenDetailController(@DrawableRes int imageDrawableRes, String title ) {
        this(new BundleBuilder(new Bundle())
                .putInt(KEY_IMAGE, imageDrawableRes)
                .putString(KEY_TITLE, title)
                .build());
//        selectDescription(title);
//        if(getListRows()!=null){
//            LIST_ROWS=getListRows();
//        }

    }

    protected abstract void selectDescription(String title);

    protected abstract String[] getListRows();

    public SwapAndOpenDetailController(Bundle args) {
        super(args);
        imageDrawableRes = getArgs().getInt(KEY_IMAGE);
        title = getArgs().getString(KEY_TITLE);
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_city_detail, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        selectDescription(title);
        if(getListRows()!=null){
            LIST_ROWS=getListRows();
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new CityDetailAdapter(LayoutInflater.from(view.getContext()), title, imageDrawableRes, LIST_ROWS, title));
    }

    @Override
    protected String getTitle() {
        return title;
    }

    static class CityDetailAdapter extends RecyclerView.Adapter<CityDetailAdapter.ViewHolder> {

        private static final int VIEW_TYPE_HEADER = 0;
        private static final int VIEW_TYPE_DETAIL = 1;

        private final LayoutInflater inflater;
        private final String title;
        @DrawableRes
        private final int imageDrawableRes;
        private final String imageViewTransitionName;
        private final String textViewTransitionName;
        private final String[] details;

        public CityDetailAdapter(LayoutInflater inflater, String title, @DrawableRes int imageDrawableRes, String[] details, String transitionNameBase) {
            this.inflater = inflater;
            this.title = title;
            this.imageDrawableRes = imageDrawableRes;
            this.details = details;
            imageViewTransitionName = inflater.getContext().getResources().getString(R.string.transition_tag_image_named, transitionNameBase);
            textViewTransitionName = inflater.getContext().getResources().getString(R.string.transition_tag_title_named, transitionNameBase);
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return VIEW_TYPE_HEADER;
            } else {
                return VIEW_TYPE_DETAIL;
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == VIEW_TYPE_HEADER) {
                return new HeaderViewHolder(inflater.inflate(R.layout.row_city_header, parent, false));
            } else {
                return new DetailViewHolder(inflater.inflate(R.layout.row_city_detail, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (getItemViewType(position) == VIEW_TYPE_HEADER) {
                ((HeaderViewHolder)holder).bind(imageDrawableRes, title, imageViewTransitionName, textViewTransitionName);
            } else {
                ((DetailViewHolder)holder).bind(details[position - 1]);
            }
        }

        @Override
        public int getItemCount() {
            return 1 + details.length;
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        static class HeaderViewHolder extends ViewHolder {

            @BindView(R.id.image_view)
            ImageView imageView;
            @BindView(R.id.text_view)
            TextView textView;

            public HeaderViewHolder(View itemView) {
                super(itemView);
            }

            void bind(@DrawableRes int imageDrawableRes, String title, String imageTransitionName, String textViewTransitionName) {
                imageView.setImageResource(imageDrawableRes);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setText(title);

                ViewCompat.setTransitionName(imageView, imageTransitionName);
                ViewCompat.setTransitionName(textView, textViewTransitionName);
            }
        }

        static class DetailViewHolder extends ViewHolder {

            @BindView(R.id.text_view)
            TextView textView;

            public DetailViewHolder(View itemView) {
                super(itemView);
            }

            void bind(String detail) {
                textView.setText(detail);
            }

        }
    }
}
