package com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.exerciseSelectionController;

import android.content.res.Resources;

import androidx.core.util.Pair;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.expandableExampleAbstractController.ExampleAbstractExpandableDataProvider;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExerciseExpandableDataProvider extends ExampleAbstractExpandableDataProvider {

    private LinkedList<Pair<ConcreteGroupData, List<ChildData>>> mData;

    private Resources resources;

    enum ExerciseName {

        PUSH_UPS(R.string.push_ups),
        TIGHTENING(R.string.tightening),
        SQUATS(R.string.squat),
        ;
        int title;

        ExerciseName(int title) {
            this.title = title;
        }
    }

    enum PushUpsListLevel {

        LVL_1(R.string.level_1, R.string.push_ups_level_1),
        LVL_2(R.string.level_2, R.string.push_ups_level_2),
        LVL_3(R.string.level_3, R.string.push_ups_level_3),
        LVL_4(R.string.level_4, R.string.push_ups_level_4),
        LVL_5(R.string.level_5, R.string.push_ups_level_5),
        LVL_6(R.string.level_6, R.string.push_ups_level_6),
        LVL_7(R.string.level_7, R.string.push_ups_level_7),
        LVL_8(R.string.level_8, R.string.push_ups_level_8),
        LVL_9(R.string.level_9, R.string.push_ups_level_9),
        LVL_10(R.string.level_10, R.string.push_ups_level_10);

        int level;
        int name;

        PushUpsListLevel(int level, int name) {
            this.level = level;
            this.name = name;
        }
    }

    enum TighteningListLevel {
        LVL_1(R.string.level_1, R.string.tightening_level_1),
        LVL_2(R.string.level_2, R.string.tightening_level_2),
        LVL_3(R.string.level_3, R.string.tightening_level_3),
        LVL_4(R.string.level_4, R.string.tightening_level_4),
        LVL_5(R.string.level_5, R.string.tightening_level_5),
        LVL_6(R.string.level_6, R.string.tightening_level_6),
        LVL_7(R.string.level_7, R.string.tightening_level_7),
        LVL_8(R.string.level_8, R.string.tightening_level_8),
        LVL_9(R.string.level_9, R.string.tightening_level_9),
        LVL_10(R.string.level_10, R.string.tightening_level_10);

        int level;
        int name;

        TighteningListLevel(int level, int name) {
            this.level = level;
            this.name = name;
        }
    }


    enum Squats {
        LVL_1(R.string.level_1, R.string.squats_level_1),
        LVL_2(R.string.level_2, R.string.squats_level_2),
        LVL_3(R.string.level_3, R.string.squats_level_3),
        LVL_4(R.string.level_4, R.string.squats_level_4),
        LVL_5(R.string.level_5, R.string.squats_level_5),
        LVL_6(R.string.level_6, R.string.squats_level_6),
        LVL_7(R.string.level_7, R.string.squats_level_7),
        LVL_8(R.string.level_8, R.string.squats_level_8),
        LVL_9(R.string.level_9, R.string.squats_level_9),
        LVL_10(R.string.level_10, R.string.squats_level_10);

        int level;
        int name;

        Squats(int level, int name) {
            this.level = level;
            this.name = name;
        }
    }




    @Override
    protected LinkedList<Pair<ConcreteGroupData, List<ChildData>>> getDataList() {
        return mData;
    }

    public ExerciseExpandableDataProvider() {

    }

    public ExerciseExpandableDataProvider(Resources resources) {

        this.resources = resources;

        final ExerciseName[] groupItems = ExerciseName.values();
        mData = new LinkedList<Pair<ConcreteGroupData, List<ChildData>>>();

        for (int i = 0; i < groupItems.length; i++) {

            final String groupText = resources.getString(groupItems[i].title);
            final ConcreteGroupData group = new ConcreteGroupData((long) i, groupText);
            final List<ChildData> children = new ArrayList<>();

            switch (groupItems[i]) {

                case PUSH_UPS:
                    PushUpsListLevel[] pushUpsListLevels = PushUpsListLevel.values();
                    for (int k = 0; k < pushUpsListLevels.length; k++) {
                        final long childId = group.generateNewChildId();
                        final String childText = resources.getString(pushUpsListLevels[k].name);
                        children.add(new ConcreteChildData(childId, childText));
                    }
                    break;

                case TIGHTENING:
                    TighteningListLevel[] tighteningListLevel = TighteningListLevel.values();
                    for (int k = 0; k < tighteningListLevel.length; k++) {
                        final long childId = group.generateNewChildId();
                        final String childText =resources.getString(tighteningListLevel[k].name);
                        children.add(new ConcreteChildData(childId, childText));
                    }
                    break;

                case SQUATS:
                    Squats[] squats = Squats.values();
                    for (int k = 0; k < squats.length; k++) {
                        final long childId = group.generateNewChildId();
                        final String childText = resources.getString(squats[k].name);
                        children.add(new ConcreteChildData(childId, childText));
                    }
                    break;

                default:
                    final long childId = group.generateNewChildId();
                    final String childText = "Пример текста";
                    children.add(new ConcreteChildData(childId, childText));
                    break;

            }

            mData.add(new Pair<>(group, children));
        }

    }
}
