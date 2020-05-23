package com.example.trainingzonev4.realm;

import android.content.res.Resources;

import com.example.trainingzonev4.R;
import com.example.trainingzonev4.realm.ExerciseDataRealm;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

public class ExerciseImageAndNameDataClass {

    private static String resultString;
    private static int resultInt;

    public static int getIntImageByName(String name, Resources resources) {

        resultInt=R.drawable.push_ups_wall;

        name = name.replaceAll("\"", "");
        String finalName = name;

        Realm realm = Realm.getDefaultInstance();

        Disposable disposable= realm.where(ExerciseDataRealm.class).findAll().asFlowable()
                .flatMap(Flowable::fromIterable)
                .filter(v->v.getName().equals(finalName))
                .subscribe(value->resultInt=value.getImage());

        return resultInt;

    }

    public static int getIntImageByName(int name, Resources resources) {
        return getIntImageByName(resources.getString(name), resources);
    }

    public static String getIntDescriptionsByName(String name, Resources resources) {

        resultString =resources.getString(R.string.push_ups_level_1);

        name = name.replaceAll("\"", "");
        String finalName = name;

        Realm realm = Realm.getDefaultInstance();

        Disposable disposable= realm.where(ExerciseDataRealm.class).findAll().asFlowable()
                .flatMap(Flowable::fromIterable)
                .filter(v->v.getName().equals(finalName))
                .subscribe(value->resultString=value.getDescriptions());

        return resultString;
    }

    public static String getIntDescriptionsByName(int name, Resources resources) {
        return getIntDescriptionsByName(resources.getString(name), resources);
    }

    public static int getIntVideoByName(int name, Resources resources){
        return getIntVideoByName(resources.getString(name), resources);
    }

    public static int getIntVideoByName(String name, Resources resources){
        name = name.replaceAll("\"", "");

        return R.string.tightening_level_1_video;
    }

}
