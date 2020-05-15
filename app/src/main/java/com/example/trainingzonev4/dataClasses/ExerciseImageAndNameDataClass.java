package com.example.trainingzonev4.dataClasses;

import android.content.res.Resources;

import com.example.trainingzonev4.R;

public class ExerciseImageAndNameDataClass {

    public static int getIntImageByName(String name, Resources resources) {
        name = name.replaceAll("\"", "");

        if (name.equals(resources.getString(R.string.push_ups_level_1))) {
            return R.drawable.push_ups_wall;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_2))) {
            return R.drawable.push_ups_inclination;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_3))) {
            return R.drawable.push_ups_lap;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_4))) {
            return R.drawable.push_ups_incomplete;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_5))) {
            return R.drawable.push_ups_classic;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_6))) {
            return R.drawable.push_ups_diamond;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_7))) {
            return R.drawable.push_ups_different_heights;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_8))) {
            return R.drawable.push_ups_incomplete_on_one_arm;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_9))) {
            return R.drawable.push_ups_on_one_arm_with_support;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_10))) {
            return R.drawable.push_ups_one_hand;
        }


        if (name.equals(resources.getString(R.string.squats_level_1))) {
            return R.drawable.squats_shoulder;
        }
        if (name.equals(resources.getString(R.string.squats_level_2))) {
            return R.drawable.squats_folding_knife;
        }
        if (name.equals(resources.getString(R.string.squats_level_3))) {
            return R.drawable.squats_with_supports;
        }
        if (name.equals(resources.getString(R.string.squats_level_4))) {
            return R.drawable.squats_not_complete;
        }
        if (name.equals(resources.getString(R.string.squats_level_5))) {
            return R.drawable.squats_classic;
        }
        if (name.equals(resources.getString(R.string.squats_level_6))) {
            return R.drawable.squats_norrow;
        }
        if (name.equals(resources.getString(R.string.squats_level_7))) {
            return R.drawable.squats_not_complete_one_leg;
        }
        if (name.equals(resources.getString(R.string.squats_level_8))) {
            return R.drawable.squats_not_complete_one_leg_without_support;
        }
        if (name.equals(resources.getString(R.string.squats_level_9))) {
            return R.drawable.squats_one_leg_with_support;
        }
        if (name.equals(resources.getString(R.string.squats_level_10))) {
            return R.drawable.squat_one_leg;
        }
        if (name.equals(resources.getString(R.string.tightening_level_1))) {
            return R.drawable.tightening_doorway;
        }
        if (name.equals(resources.getString(R.string.tightening_level_2))) {
            return R.drawable.tightening_horizontal;
        }
        if (name.equals(resources.getString(R.string.tightening_level_3))) {
            return R.drawable.tightening_folding_knife;
        }
        if (name.equals(resources.getString(R.string.tightening_level_4))) {
            return R.drawable.tightening_not_complete;
        }
        if (name.equals(resources.getString(R.string.tightening_level_5))) {
            return R.drawable.tightening_classic;
        }
        if (name.equals(resources.getString(R.string.tightening_level_6))) {
            return R.drawable.tightening_narrow;
        }
        if (name.equals(resources.getString(R.string.tightening_level_7))) {
            return R.drawable.tightening_one_hand_with_support;
        }
        if (name.equals(resources.getString(R.string.tightening_level_8))) {
            return R.drawable.tightening_not_complete_one_hand;
        }
        if (name.equals(resources.getString(R.string.tightening_level_9))) {
            return R.drawable.tightening_one_hand_with_support2;
        }
        if (name.equals(resources.getString(R.string.tightening_level_10))) {
            return R.drawable.tightening_not_complete_one_hand;
        }


        return R.drawable.push_ups_wall;
    }

    public static int getIntImageByName(int name, Resources resources) {
        return getIntImageByName(resources.getString(name), resources);
    }

    public static int getIntDescriptionsByName(String name, Resources resources) {
        name = name.replaceAll("\"", "");

        if (name.equals(resources.getString(R.string.push_ups_level_1))) {
            return R.string.push_ups_level_1_descriptions;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_2))) {
            return R.string.push_ups_level_2_descriptions;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_3))) {
            return R.string.push_ups_level_3_descriptions;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_4))) {
            return R.string.push_ups_level_4_descriptions;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_5))) {
            return R.string.push_ups_level_5_descriptions;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_6))) {
            return R.string.push_ups_level_6_descriptions;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_7))) {
            return R.string.push_ups_level_7_descriptions;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_8))) {
            return R.string.push_ups_level_8_descriptions;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_9))) {
            return R.string.push_ups_level_9_descriptions;
        }
        if (name.equals(resources.getString(R.string.push_ups_level_10))) {
            return R.string.push_ups_level_10_descriptions;
        }


        if (name.equals(resources.getString(R.string.squats_level_1))) {
            return R.string.squats_level_1_descriptions;
        }
        if (name.equals(resources.getString(R.string.squats_level_2))) {
            return R.string.squats_level_2_descriptions;
        }
        if (name.equals(resources.getString(R.string.squats_level_3))) {
            return R.string.squats_level_3_descriptions;
        }
        if (name.equals(resources.getString(R.string.squats_level_4))) {
            return R.string.squats_level_4_descriptions;
        }
        if (name.equals(resources.getString(R.string.squats_level_5))) {
            return R.string.squats_level_5_descriptions;
        }
        if (name.equals(resources.getString(R.string.squats_level_6))) {
            return R.string.squats_level_6_descriptions;
        }
        if (name.equals(resources.getString(R.string.squats_level_7))) {
            return R.string.squats_level_7_descriptions;
        }
        if (name.equals(resources.getString(R.string.squats_level_8))) {
            return R.string.squats_level_8_descriptions;
        }
        if (name.equals(resources.getString(R.string.squats_level_9))) {
            return R.string.squats_level_9_descriptions;
        }
        if (name.equals(resources.getString(R.string.squats_level_10))) {
            return R.string.squats_level_10_descriptions;
        }

        if (name.equals(resources.getString(R.string.tightening_level_1))) {
            return R.string.tightening_level_1_descriptions;
        }
        if (name.equals(resources.getString(R.string.tightening_level_2))) {
            return R.string.tightening_level_2_descriptions;
        }
        if (name.equals(resources.getString(R.string.tightening_level_3))) {
            return R.string.tightening_level_3_descriptions;
        }
        if (name.equals(resources.getString(R.string.tightening_level_4))) {
            return R.string.tightening_level_4_descriptions;
        }


        return R.string.exercise_level_default_descriptions;
    }

    public static int getIntDescriptionsByName(int name, Resources resources) {
        return getIntDescriptionsByName(resources.getString(name), resources);
    }


}
