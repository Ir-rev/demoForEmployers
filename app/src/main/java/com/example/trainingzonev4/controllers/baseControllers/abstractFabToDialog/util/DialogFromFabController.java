package com.example.trainingzonev4.controllers.baseControllers.abstractFabToDialog.util;


import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.util.BundleBuilder;

import butterknife.BindView;
import butterknife.OnClick;

public class DialogFromFabController extends BaseController {

    private static final String KEY_TITLE = "DialogFromFabController.name";
    private static final String KEY_DESCRIPTION = "DialogFromFabController.description";

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_description)
    TextView tvDescription;

    public DialogFromFabController(CharSequence title, CharSequence description) {
        this(new BundleBuilder(new Bundle())
                .putCharSequence(KEY_TITLE, title)
                .putCharSequence(KEY_DESCRIPTION, description)
                .build());
    }

    public DialogFromFabController(Bundle args) {
        super(args);
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_dialog, container, false);
    }

    @Override
    public void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        tvTitle.setText(getArgs().getCharSequence(KEY_TITLE));
        tvDescription.setText(getArgs().getCharSequence(KEY_DESCRIPTION));
        tvDescription.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @OnClick({R.id.dismiss, R.id.dialog_window})
    public void dismissDialog() {
        getRouter().popController(this);
    }

    @Override
    public boolean handleBack() {
        return super.handleBack();
    }
}
