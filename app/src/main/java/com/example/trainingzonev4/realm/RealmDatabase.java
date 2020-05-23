package com.example.trainingzonev4.realm;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.example.trainingzonev4.R;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmDatabase {

    enum ExerciseData{
        PUSH_UPS_1(R.string.push_ups_level_1,R.drawable.push_ups_wall,R.string.push_ups_level_1_descriptions,R.string.level_1),
        PUSH_UPS_2(R.string.push_ups_level_2,R.drawable.push_ups_inclination,R.string.push_ups_level_2_descriptions,R.string.level_2),
        PUSH_UPS_3(R.string.push_ups_level_3,R.drawable.push_ups_lap,R.string.push_ups_level_3_descriptions,R.string.level_3),
        PUSH_UPS_4(R.string.push_ups_level_4,R.drawable.push_ups_incomplete,R.string.push_ups_level_4_descriptions,R.string.level_4),
        PUSH_UPS_5(R.string.push_ups_level_5,R.drawable.push_ups_classic,R.string.push_ups_level_5_descriptions,R.string.level_5),
        PUSH_UPS_6(R.string.push_ups_level_6,R.drawable.push_ups_diamond,R.string.push_ups_level_6_descriptions,R.string.level_6),
        PUSH_UPS_7(R.string.push_ups_level_7,R.drawable.push_ups_different_heights,R.string.push_ups_level_7_descriptions,R.string.level_7),
        PUSH_UPS_8(R.string.push_ups_level_8,R.drawable.push_ups_incomplete_on_one_arm,R.string.push_ups_level_8_descriptions,R.string.level_8),
        PUSH_UPS_9(R.string.push_ups_level_9,R.drawable.push_ups_on_one_arm_with_support,R.string.push_ups_level_9_descriptions,R.string.level_9),
        PUSH_UPS_10(R.string.push_ups_level_10,R.drawable.push_ups_one_hand,R.string.push_ups_level_10_descriptions,R.string.level_10),

        SQUATS_1(R.string.squats_level_1,R.drawable.squats_shoulder,R.string.squats_level_1_descriptions,R.string.level_1),
        SQUATS_2(R.string.squats_level_2,R.drawable.squats_folding_knife,R.string.squats_level_2_descriptions,R.string.level_2),
        SQUATS_3(R.string.squats_level_3,R.drawable.squats_with_supports,R.string.squats_level_3_descriptions,R.string.level_3),
        SQUATS_4(R.string.squats_level_4,R.drawable.squats_not_complete,R.string.squats_level_4_descriptions,R.string.level_4),
        SQUATS_5(R.string.squats_level_5,R.drawable.squats_classic,R.string.squats_level_5_descriptions,R.string.level_5),
        SQUATS_6(R.string.squats_level_6,R.drawable.squats_norrow,R.string.squats_level_6_descriptions,R.string.level_6),
        SQUATS_7(R.string.squats_level_7,R.drawable.squats_not_complete_one_leg,R.string.squats_level_7_descriptions,R.string.level_7),
        SQUATS_8(R.string.squats_level_8,R.drawable.squats_not_complete_one_leg_without_support,R.string.squats_level_8_descriptions,R.string.level_8),
        SQUATS_9(R.string.squats_level_9,R.drawable.squats_one_leg_with_support,R.string.squats_level_9_descriptions,R.string.level_9),
        SQUATS_10(R.string.squats_level_10,R.drawable.squat_one_leg,R.string.squats_level_10_descriptions,R.string.level_10),

        TIGHTENING_1(R.string.tightening_level_1,R.drawable.tightening_doorway,R.string.tightening_level_1_descriptions,R.string.level_1),
        TIGHTENING_2(R.string.tightening_level_2,R.drawable.tightening_horizontal,R.string.tightening_level_2_descriptions,R.string.level_2),
        TIGHTENING_3(R.string.tightening_level_3,R.drawable.tightening_folding_knife,R.string.tightening_level_3_descriptions,R.string.level_3),
        TIGHTENING_4(R.string.tightening_level_4,R.drawable.tightening_not_complete,R.string.tightening_level_4_descriptions,R.string.level_4),
        TIGHTENING_5(R.string.tightening_level_5,R.drawable.tightening_classic,R.string.tightening_level_5_descriptions,R.string.level_5),
        TIGHTENING_6(R.string.tightening_level_6,R.drawable.tightening_narrow,R.string.tightening_level_6_descriptions,R.string.level_6),
        TIGHTENING_7(R.string.tightening_level_7,R.drawable.tightening_one_hand_with_support,R.string.tightening_level_7_descriptions,R.string.level_7),
        TIGHTENING_8(R.string.tightening_level_8,R.drawable.tightening_not_complete_one_hand,R.string.tightening_level_8_descriptions,R.string.level_8),
        TIGHTENING_9(R.string.tightening_level_9,R.drawable.tightening_one_hand_with_support2,R.string.tightening_level_9_descriptions,R.string.level_9),
        TIGHTENING_10(R.string.tightening_level_10,R.drawable.tightening_not_complete_one_hand,R.string.tightening_level_10_descriptions,R.string.level_10),

        ;

        int name, image, descriptions, level;

        ExerciseData(int name, int image, int descriptions, int level) {
            this.name = name;
            this.image = image;
            this.descriptions = descriptions;
            this.level = level;
        }
    }

    private Context context;
    private Resources resources;

    public RealmDatabase(Context context, Resources resources) {
        this.context = context;
        this.resources = resources;
    }

    public RealmDatabase() {
    }

    public void createDatabase() {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name("exerciseList").build();
        Realm.deleteRealm(config);
        Realm.setDefaultConfiguration(config);
        createTestData();
    }

    private void createTestData() {
        Realm realm = Realm.getDefaultInstance();
        ExerciseData[] exerciseData= ExerciseData.values();
        realm.executeTransaction(realmOjb -> {
            for (ExerciseData list: exerciseData) {
                ExerciseDataRealm dataRealm= realmOjb.createObject(ExerciseDataRealm.class);
                dataRealm.setName(resources.getString(list.name));
                dataRealm.setDescriptions(resources.getString(list.descriptions));
                dataRealm.setLevel(resources.getString(list.level));
                dataRealm.setImage(list.image);
            }
        });
        realm.close();
    }
}
