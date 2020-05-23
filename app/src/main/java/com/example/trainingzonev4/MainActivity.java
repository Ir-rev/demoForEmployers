package com.example.trainingzonev4;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.example.trainingzonev4.controllers.homeControllers.HomeMenuController;
import com.example.trainingzonev4.realm.ExerciseDataRealm;
import com.example.trainingzonev4.realm.RealmDatabase;
import com.example.trainingzonev4.util.ActionBarProvider;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements ActionBarProvider {

    @BindView(R.id.controller_container)
    ViewGroup container;

    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RealmDatabase realmDatabase =new RealmDatabase(this,getResources());
        realmDatabase.createDatabase();

        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new HomeMenuController()));
        }
//
//        Realm realm = Realm.getDefaultInstance();
////
//        String text;
//        Disposable disposable= realm.where(ExerciseDataRealm.class).findAll().asFlowable()
//                .flatMap(Flowable::fromIterable)
//                .filter(v->v.getName().equals(getResources().getString(R.string.push_ups_level_2)))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(value->text=value);
//
//

    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

}
