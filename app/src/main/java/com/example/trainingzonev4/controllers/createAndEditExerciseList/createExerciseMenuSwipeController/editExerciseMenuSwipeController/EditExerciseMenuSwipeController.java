package com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.editExerciseMenuSwipeController;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler;
import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.AbstractDataProvider;
import com.example.trainingzonev4.controllers.baseControllers.listAbstractSwipeLeftRightController.DraggableSwipeableExampleAdapter;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.exerciseListSwipeController.ExerciseListSwipeController;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.exerciseSelectionController.ExerciseSelectionController;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.fabInfoAboutButtonController.FabInfoAboutButtonController;
import com.example.trainingzonev4.controllers.startToWorkoutController.implementationExerciseListController.ImplementationExerciseListController;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class EditExerciseMenuSwipeController extends BaseController {


    @BindView(R.id.controller_container_with_list)
    ViewGroup container;
    @BindView(R.id.fab)
    View fab;

    private AbstractDataProvider provider;
    private DraggableSwipeableExampleAdapter myItemAdapter;
    private LinkedList<ExerciseListData> exerciseArrayList;
    public EditExerciseMenuSwipeController() {

    }

    class ExerciseListData {
        int times;
        String name;

        public ExerciseListData(String name, int times) {
            this.name = name;
            this.times = times;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }
    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.exercise_list_swipe_menu, container, false);
    }

    @OnClick(R.id.fab)
    public void onFabSaveClicked() {

        if (provider.getCount() > 0) {

            try {

                JSONObject item = new JSONObject();
                JSONObject jsonOutToFile = new JSONObject();
                JSONArray jsonArray = new JSONArray();

                for (int i = 0; provider.getCount() > i; i++) {

                    item = new JSONObject();
                    item.put("name", provider.getItem(i).getText());
                    item.put("times", provider.getItem(i).getTimes());
                    jsonArray.put(item);

                }

                jsonOutToFile.put("Exercise", jsonArray);

                String FILENAME = "MyExerciseList";

                try {

                    FileOutputStream fileOutputStream = Objects.requireNonNull(getView()).getContext()
                            .openFileOutput(FILENAME, Context.MODE_PRIVATE);

                    fileOutputStream.write(jsonOutToFile.toString().replace("\\\"","").getBytes());
                    fileOutputStream.close();


                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        if(provider.getCount()>0){
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Сохранено", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

    @OnClick(R.id.fab_info)
    public void onFabInfoClicked(){
        ViewGroup controllerContainerSelectExercise = Objects.requireNonNull(getActivity())
                .findViewById(R.id.controller_container);

        Router childRouterOnClick = getChildRouter(controllerContainerSelectExercise)
                .setPopsLastView(true);

        childRouterOnClick.setRoot(RouterTransaction.with(new FabInfoAboutButtonController())
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        Router childRouter = getChildRouter(container).setPopsLastView(false);
        ExerciseListSwipeController childController = new ExerciseListSwipeController();

        childRouter.setRoot(RouterTransaction.with(childController)
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));

        provider = childController.getMyItemAdapter().getmProvider();
        myItemAdapter = childController.getMyItemAdapter();

        provider.removeItem(0);


        JsonParser parser = new JsonParser();
        JsonObject jsonFromStringTAG = new JsonObject();
        exerciseArrayList = new LinkedList<>();

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = view.getContext().openFileInput(getResources().getString(R.string.my_exercise_list_file_name));
            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            String jsonFromFile = new String (bytes);
            jsonFromStringTAG = (JsonObject) parser.parse(jsonFromFile);
            exerciseArrayList = setUpExerciseListData(jsonFromStringTAG);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<exerciseArrayList.size();i++){
            provider.addItemWithName(exerciseArrayList.get(i).name,exerciseArrayList.get(i).times);
        }

    }

    protected LinkedList<ExerciseListData> setUpExerciseListData(JsonObject jsonFromStringTAG) {

        JsonArray jsonArray = (JsonArray) jsonFromStringTAG.get("Exercise");
        LinkedList<ExerciseListData> exerciseArrayList = new LinkedList<>();

        Gson g = new Gson();

        for (int i = 0; jsonArray.size() > i; i++) {
            JsonObject jsonExercise = (JsonObject) jsonArray.get(i);
            ExerciseListData exerciseListData = new ExerciseListData(jsonExercise.get("name").toString(), Integer.parseInt(jsonExercise.get("times").toString()));
            exerciseArrayList.add(exerciseListData);
        }

        return exerciseArrayList;

    }

    @OnClick(R.id.button_add_exercise)
    void addExerciseOnClick(View view) {

        ViewGroup controllerContainerSelectExercise = Objects.requireNonNull(getActivity())
                .findViewById(R.id.controller_container);

        Router childRouterOnClick = getChildRouter(controllerContainerSelectExercise)
                .setPopsLastView(true);

        childRouterOnClick.setRoot(RouterTransaction.with(new ExerciseSelectionController(myItemAdapter, provider))
                .pushChangeHandler(new VerticalChangeHandler())
                .popChangeHandler(new VerticalChangeHandler()));

    }

}

