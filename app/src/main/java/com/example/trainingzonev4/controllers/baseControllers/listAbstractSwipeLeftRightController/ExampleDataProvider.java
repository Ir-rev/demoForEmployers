package com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController;

import androidx.annotation.NonNull;

import com.h6ah4i.android.widget.advrecyclerview.swipeable.RecyclerViewSwipeManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class ExampleDataProvider extends AbstractDataProvider {


    private List<ConcreteData> mData;
    private ConcreteData mLastRemovedData;
    private int mLastRemovedPosition = -1;



    public ExampleDataProvider() {

        ArrayList<String> dataList=getListWithData();

        mData = new LinkedList<>();

        for (int i = 0; i < dataList.size(); i++) {
            final int times=1;
            final long id = mData.size();
            final int viewType = 0;
            final String text = dataList.get(i)+" x"+times;
            final int swipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_UP | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_DOWN;
            mData.add(new ConcreteData(id, viewType, text, swipeReaction,times));

        }

    }

    protected abstract ArrayList<String> getListWithData();


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Data getItem(int index) {
        if (index < 0 || index >= getCount()) {
            throw new IndexOutOfBoundsException("index = " + index);
        }
        return mData.get(index);
    }

    @Override
    public int undoLastRemoval() {
        if (mLastRemovedData != null) {
            int insertedPosition;
            if (mLastRemovedPosition >= 0 && mLastRemovedPosition < mData.size()) {
                insertedPosition = mLastRemovedPosition;
            } else {
                insertedPosition = mData.size();
            }

            mData.add(insertedPosition, mLastRemovedData);

            mLastRemovedData = null;
            mLastRemovedPosition = -1;

            return insertedPosition;
        } else {
            return -1;
        }
    }

    @Override
    public void moveItem(int fromPosition, int toPosition) {
        if (fromPosition == toPosition) {
            return;
        }

        final ConcreteData item = mData.remove(fromPosition);

        mData.add(toPosition, item);
        mLastRemovedPosition = -1;
    }

    @Override
    public void swapItem(int fromPosition, int toPosition) {
        if (fromPosition == toPosition) {
            return;
        }

        Collections.swap(mData, toPosition, fromPosition);
        mLastRemovedPosition = -1;
    }



    @Override
    public void addItemByPosition(int position) {
        final long id = mData.size();
        final int times=mData.get(position).getTimes();
        final int viewType = 0;
        final String text = mData.get(position).getText();
        final int swipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_UP | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_DOWN;
        mData.add(position+1,new ConcreteData(id, viewType, text, swipeReaction,times));
    }

    @Override
    public void addItemWithName(String exercise) {
        final long id = mData.size();
        final int times=20;
        final int viewType = 0;
        final int swipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_UP | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_DOWN;
        mData.add(mData.size(),new ConcreteData(id, viewType, exercise, swipeReaction,times));
    }

    @Override
    public String getTextFrom(int index) {
        return mData.get(index).getText();
    }

    @Override
    public void addItemWithName(String exercise, int exerciseTimes) {
        final long id = mData.size();
//        final int times=20;
        final int viewType = 0;
        final int swipeReaction = RecyclerViewSwipeManager.REACTION_CAN_SWIPE_UP | RecyclerViewSwipeManager.REACTION_CAN_SWIPE_DOWN;
        mData.add(mData.size(),new ConcreteData(id, viewType, exercise, swipeReaction,exerciseTimes));
    }

    @Override
    public void removeItem(int position) {
        //noinspection UnnecessaryLocalVariable
        final ConcreteData removedItem = mData.remove(position);

        mLastRemovedData = removedItem;
        mLastRemovedPosition = position;
    }

    public static final class ConcreteData extends Data {

        private final long mId;
        @NonNull
        private final String mText;
        private final int mViewType;
        private boolean mPinned;
        private int times;


        public ConcreteData(long id, int viewType, @NonNull String text, int swipeReaction,int times) {
            mId = id;
            mViewType = viewType;
            mText = makeText(id, text, swipeReaction);
            this.times=times;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        private static String makeText(long id, String text, int swipeReaction) {
            return text;
        }

        @Override
        public boolean isSectionHeader() {
            return false;
        }

        @Override
        public int getViewType() {
            return mViewType;
        }

        @Override
        public long getId() {
            return mId;
        }

        @NonNull
        @Override
        public String toString() {
            return mText;
        }

        @Override
        public String getText() {
            return mText;
        }

        @Override
        public boolean isPinned() {
            return mPinned;
        }

        @Override
        public void setPinned(boolean pinned) {
            mPinned = pinned;
        }
    }
}

