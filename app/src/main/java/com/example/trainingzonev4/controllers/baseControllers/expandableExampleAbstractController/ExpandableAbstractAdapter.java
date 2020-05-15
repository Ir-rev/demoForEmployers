package com.example.trainingzonev4.controllers.baseControllers.expandableExampleAbstractController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.expandableExampleAbstractController.util.ExpandableItemIndicator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemState;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemViewHolder;

public abstract class ExpandableAbstractAdapter
        extends AbstractExpandableItemAdapter<ExpandableAbstractAdapter.MyGroupViewHolder, ExpandableAbstractAdapter.MyChildViewHolder> {
    private static final String TAG = "MyExpandableItemAdapter";

    private AbstractExpandableDataProvider mProvider;

    public ExpandableAbstractAdapter() {

    }

    public static abstract class MyBaseViewHolder extends AbstractExpandableItemViewHolder {
        public FrameLayout mContainer;
        public TextView mTextView;

        public MyBaseViewHolder(View v) {
            super(v);
            mContainer = v.findViewById(R.id.container);
            mTextView = v.findViewById(android.R.id.text1);
        }
    }

    public static class MyGroupViewHolder extends MyBaseViewHolder {
        public ExpandableItemIndicator mIndicator;

        public MyGroupViewHolder(View v) {
            super(v);
            mIndicator = v.findViewById(R.id.indicator);
        }
    }

    public static class MyChildViewHolder extends MyBaseViewHolder {
        public MyChildViewHolder(View v) {
            super(v);
        }
    }

    public ExpandableAbstractAdapter(AbstractExpandableDataProvider dataProvider) {
        mProvider = dataProvider;

        setHasStableIds(true);
    }

    @Override
    public int getGroupCount() {
        return mProvider.getGroupCount();
    }

    @Override
    public int getChildCount(int groupPosition) {
        return mProvider.getChildCount(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return mProvider.getGroupItem(groupPosition).getGroupId();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return mProvider.getChildItem(groupPosition, childPosition).getChildId();
    }

    @Override
    public int getGroupItemViewType(int groupPosition) {
        return 0;
    }

    @Override
    public int getChildItemViewType(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    @NonNull
    public MyGroupViewHolder onCreateGroupViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_group_item, parent, false);
        return new MyGroupViewHolder(v);
    }

    @Override
    @NonNull
    public MyChildViewHolder onCreateChildViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View v = inflater.inflate(R.layout.list_item, parent, false);
        return new MyChildViewHolder(v);
    }

    @Override
    public void onBindGroupViewHolder(@NonNull MyGroupViewHolder holder, int groupPosition, int viewType) {

        final AbstractExpandableDataProvider.BaseData item = mProvider.getGroupItem(groupPosition);


        holder.mTextView.setText(item.getText());


        holder.itemView.setClickable(true);


        final ExpandableItemState expandState = holder.getExpandState();

        if (expandState.isUpdated()) {
            int bgResId;
            boolean animateIndicator = expandState.hasExpandedStateChanged();

            if (expandState.isExpanded()) {
                bgResId = getOpenFolderColor();
            } else {
                bgResId = getCloseFolderColor();
            }

            holder.mContainer.setBackgroundResource(bgResId);
            holder.mIndicator.setExpandedState(expandState.isExpanded(), animateIndicator);
        }
    }

    protected abstract int getCloseFolderColor();
    protected abstract int getOpenFolderColor();
    protected abstract int getElementsColor();



    protected abstract void onItemClick(MyChildViewHolder holder, TextView textView);


    @Override
    public void onBindChildViewHolder(@NonNull MyChildViewHolder holder, int groupPosition, int childPosition, int viewType) {

        final AbstractExpandableDataProvider.ChildData item = mProvider.getChildItem(groupPosition, childPosition);

        holder.mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(holder,holder.mTextView);
            }
        });

        holder.mTextView.setText(item.getText());

        int bgResId;
        bgResId =  getElementsColor();
        holder.mContainer.setBackgroundResource(bgResId);
    }




    @Override
    public boolean onCheckCanExpandOrCollapseGroup(@NonNull MyGroupViewHolder holder, int groupPosition, int x, int y, boolean expand) {

        if (mProvider.getGroupItem(groupPosition).isPinned()) {
            return false;
        }

        if (!(holder.itemView.isEnabled() && holder.itemView.isClickable())) {
            return false;
        }

        return true;
    }
}
