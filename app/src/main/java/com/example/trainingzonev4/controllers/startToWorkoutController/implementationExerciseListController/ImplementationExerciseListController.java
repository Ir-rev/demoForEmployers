package com.example.trainingzonev4.controllers.startToWorkoutController.implementationExerciseListController;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler;
import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.realm.ExerciseImageAndNameDataClass;
import com.example.trainingzonev4.transform.scaleFadeChangeHandler.ScaleFadeChangeHandler;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;

public class ImplementationExerciseListController extends BaseController {

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


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.button_start)
    Button buttonStart;

    private LinkedList<ExerciseListData> exerciseArrayList;
    private String workoutListTAG;

    public ImplementationExerciseListController() {

    }

    public ImplementationExerciseListController(String workoutListTAG) {
        this.workoutListTAG = workoutListTAG;
    }

    @OnClick(R.id.button_start)
    public void onButtonStartClick(){


        getRouter().pushController(
                RouterTransaction.with(new ProcessOfPerformanceExerciseController(exerciseArrayList,0))
                        .pushChangeHandler(new HorizontalChangeHandler())
                        .popChangeHandler(new HorizontalChangeHandler()));
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);
        JsonParser parser = new JsonParser();
        JsonObject jsonFromStringTAG = new JsonObject();
        exerciseArrayList = new LinkedList<>();

        if (workoutListTAG.equals(Objects.requireNonNull(getResources())
                .getString(R.string.workout_fresh_blood))) {

            jsonFromStringTAG = (JsonObject) parser.parse(getResources().getString(R.string.fresh_blood_json));
            exerciseArrayList = setUpExerciseListData(jsonFromStringTAG);
        }

        else if (workoutListTAG.equals(Objects.requireNonNull(getResources())
                .getString(R.string.my_exercise_list_TAG))) {


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

        }


        if (exerciseArrayList != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

            ImplementationExerciseListAdapter recyclerViewTableListAdapter =
                    new ImplementationExerciseListAdapter(exerciseArrayList, getResources());

            recyclerView.setAdapter(recyclerViewTableListAdapter);
        }


    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_implementation_exercise_list, container, false);
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


    class ImplementationExerciseListAdapter extends RecyclerView.Adapter<ImplementationExerciseListAdapter.ImplementationExerciseListHolder> {

        private LinkedList<ExerciseListData> exerciseListDataLinkedList;
        Resources resources;

        public ImplementationExerciseListAdapter(LinkedList<ExerciseListData> exerciseListDataLinkedList, Resources resources) {
            this.exerciseListDataLinkedList = exerciseListDataLinkedList;
            this.resources = resources;
        }


        class ImplementationExerciseListHolder extends RecyclerView.ViewHolder {

            TextView textViewName;
            View containerForItems;
            ImageView imageView;


            public ImplementationExerciseListHolder(@NonNull View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.textViewName);
                imageView = itemView.findViewById(R.id.imageView);

                containerForItems = itemView;
            }
        }

        @NonNull
        @Override
        public ImplementationExerciseListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.implementation_to_exercise_recycler_view_holder, parent, false);
            return new ImplementationExerciseListHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImplementationExerciseListHolder holder, int position) {

            holder.textViewName.setText(exerciseListDataLinkedList.get(position).getName().replaceAll("\"", "") + " x" +
                    (exerciseListDataLinkedList.get(position).getTimes()));

            holder.imageView.setImageDrawable(getResources()
                    .getDrawable(ExerciseImageAndNameDataClass
                            .getIntImageByName(exerciseListDataLinkedList.get(position).getName(), getResources())));


            holder.containerForItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getRouter().pushController(
                            RouterTransaction.with(new ExerciseDescriptionsInWorkoutControllers(exerciseListDataLinkedList.get(position).name))
                                    .pushChangeHandler(new VerticalChangeHandler())
                                    .popChangeHandler(new ScaleFadeChangeHandler()));

                }

            });
        }

        @Override
        public int getItemCount() {
            return exerciseListDataLinkedList.size();
        }
    }



}
