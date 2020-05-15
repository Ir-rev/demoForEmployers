package com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController;

public abstract class AbstractDataProvider {

    public static abstract class Data {
        public abstract long getId();

        public abstract boolean isSectionHeader();

        public abstract int getViewType();

        public abstract String getText();

        public abstract int getTimes();

        public abstract void setPinned(boolean pinned);

        public abstract boolean isPinned();
    }

    public abstract int getCount();

    public abstract String getTextFrom(int index);

    public abstract Data getItem(int index);

    public abstract void addItemByPosition(int position);

    public abstract void addItemWithName(String exercise);

    public abstract void addItemWithName(String exercise,int exerciseTimes);

    public abstract void removeItem(int position);

    public abstract void moveItem(int fromPosition, int toPosition);

    public abstract void swapItem(int fromPosition, int toPosition);

    public abstract int undoLastRemoval();
}
