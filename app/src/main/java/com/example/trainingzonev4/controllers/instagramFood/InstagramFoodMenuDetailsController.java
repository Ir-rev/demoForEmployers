package com.example.trainingzonev4.controllers.instagramFood;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;

import butterknife.BindView;

public class InstagramFoodMenuDetailsController extends BaseController {

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.tv_description)
    TextView tvDescriptions;

    private String text;
    private Drawable image;

    public InstagramFoodMenuDetailsController() {
    }

    public InstagramFoodMenuDetailsController(String text, Drawable image) {
        this.text = text;
        this.image = image;
    }

    @NonNull
    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_instagram_descriptions_food, container, false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        tvDescriptions.setText(text);
        imageView.setImageDrawable(image);
    }
}
