package com.example.trainingzonev4.controllers.baseControllers.expandableExampleAbstractController;

import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.h6ah4i.android.widget.advrecyclerview.animator.GeneralItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.ItemShadowDecorator;
import com.h6ah4i.android.widget.advrecyclerview.decoration.SimpleListDividerDecorator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;

import java.util.Objects;

import butterknife.BindView;

public abstract class ExpandableExampleAbstractController extends BaseController implements RecyclerViewExpandableItemManager.OnGroupCollapseListener,
        RecyclerViewExpandableItemManager.OnGroupExpandListener {

//    private static final String SAVED_STATE_EXPANDABLE_ITEM_MANAGER = "RecyclerViewExpandableItemManager";
//
//    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mWrappedAdapter;
    private RecyclerViewExpandableItemManager mRecyclerViewExpandableItemManager;


    public ExpandableExampleAbstractController() {
//        super();
    }

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    protected abstract ExpandableAbstractAdapter createAdapter();

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        //noinspection ConstantConditions
//        mRecyclerView = getView().findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(view.getContext());

//        final Parcelable eimSavedState = (savedInstanceState != null) ? savedInstanceState.getParcelable(SAVED_STATE_EXPANDABLE_ITEM_MANAGER) : null;
        mRecyclerViewExpandableItemManager = new RecyclerViewExpandableItemManager(new Parcelable() {
            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {

            }
        });

        mRecyclerViewExpandableItemManager.setOnGroupExpandListener(this);
        mRecyclerViewExpandableItemManager.setOnGroupCollapseListener(this);

        //adapter
//        final ExpandableAbstractAdapter myItemAdapter = new ExpandableAbstractAdapter(new ExampleAbstractExpandableDataProvider());
        final ExpandableAbstractAdapter myItemAdapter = createAdapter();

        mWrappedAdapter = mRecyclerViewExpandableItemManager.createWrappedAdapter(myItemAdapter);       // wrap for expanding

        final GeneralItemAnimator animator = new RefactoredDefaultItemAnimator();

        // Change animations are enabled by default since support-v7-recyclerview v22.
        // Need to disable them when using animation indicator.
        animator.setSupportsChangeAnimations(false);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mWrappedAdapter);  // requires *wrapped* adapter
        mRecyclerView.setItemAnimator(animator);
        mRecyclerView.setHasFixedSize(false);

        // additional decorations
        //noinspection StatementWithEmptyBody
        if (supportsViewElevation()) {
            // Lollipop or later has native drop shadow feature. ItemShadowDecorator is not required.
        } else {
            mRecyclerView.addItemDecoration(new ItemShadowDecorator((NinePatchDrawable)
                    Objects.requireNonNull(ContextCompat.getDrawable(view.getContext(), R.drawable.material_shadow_z1))));
        }
        mRecyclerView.addItemDecoration(new SimpleListDividerDecorator
                (ContextCompat.getDrawable(view.getContext(), R.drawable.list_divider_h), true));

        mRecyclerViewExpandableItemManager.attachRecyclerView(mRecyclerView);
    }

//    public AbstractExpandableDataProvider getDataProvider() {
//        return ((ExpandableExampleActivity) getActivity()).getDataProvider();
//    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.expandable_list, container, false);
    }


    @Override
    public void onGroupCollapse(int groupPosition, boolean fromUser, Object payload) {

    }

    private boolean supportsViewElevation() {
        return (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
    }

    @Override
    public void onGroupExpand(int groupPosition, boolean fromUser, Object payload) {

    }
}
