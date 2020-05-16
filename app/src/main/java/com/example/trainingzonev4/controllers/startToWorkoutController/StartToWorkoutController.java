package com.example.trainingzonev4.controllers.startToWorkoutController;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler;
import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.controllers.startToWorkoutController.implementationExerciseListController.ImplementationExerciseListController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;

public class StartToWorkoutController extends BaseController {

    enum WorkoutList {

        FRESH_BLOOD(R.string.workout_fresh_blood, R.drawable.fresh_blood),
        MY_LIST_EXERCISE(R.string.workout_good_behavior, R.drawable.good_behavior);

        int name;
        int image;

        WorkoutList(int name, int image) {
            this.name = name;
            this.image = image;
        }
    }

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;


    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_star_to_workout, container, false);

    }


    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        WorkoutList[] workoutList = WorkoutList.values();
        ArrayList<StartToWorkoutData> startToWorkoutData = new ArrayList<>();

        for (WorkoutList list : workoutList) {
            startToWorkoutData.add(new StartToWorkoutData(list.name, list.image, list));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        StartToWorkoutRecyclerViewAdapter recyclerViewTableListAdapter =
                new StartToWorkoutRecyclerViewAdapter(startToWorkoutData, getResources());

        recyclerView.setAdapter(recyclerViewTableListAdapter);


    }


    public class StartToWorkoutRecyclerViewAdapter extends RecyclerView.Adapter<StartToWorkoutRecyclerViewAdapter.StartToWorkoutRecyclerViewHolder> {

        private ArrayList<StartToWorkoutController.StartToWorkoutData> startToWorkoutData;
        Resources resources;

        public StartToWorkoutRecyclerViewAdapter(ArrayList<StartToWorkoutController.StartToWorkoutData> startToWorkoutData, Resources resources) {
            this.startToWorkoutData = startToWorkoutData;
            this.resources = resources;
        }

        public class StartToWorkoutRecyclerViewHolder extends RecyclerView.ViewHolder {


            TextView textViewName;
            ImageView imageView;
            View containerForItems;

            public StartToWorkoutRecyclerViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.textViewName);
                imageView = itemView.findViewById(R.id.imageView);
                containerForItems = itemView;


            }
        }

        @NonNull
        @Override
        public StartToWorkoutRecyclerViewAdapter.StartToWorkoutRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.start_to_workout_recycler_view_holder, parent, false);
            return new StartToWorkoutRecyclerViewAdapter.StartToWorkoutRecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull StartToWorkoutRecyclerViewAdapter.StartToWorkoutRecyclerViewHolder holder, int position) {
            holder.imageView.setImageDrawable(resources.getDrawable(startToWorkoutData.get(position).getImage()));
            holder.textViewName.setText(resources.getString(startToWorkoutData.get(position).getName()));

            holder.containerForItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (startToWorkoutData.get(position).list) {
                        case FRESH_BLOOD:
                            getRouter().pushController(
                                    RouterTransaction.with
                                            (new ImplementationExerciseListController((Objects.requireNonNull(getResources())
                                                    .getString(R.string.workout_fresh_blood))))
                                            .pushChangeHandler(new VerticalChangeHandler())
                                            .popChangeHandler(new VerticalChangeHandler()));
                            break;
                        case MY_LIST_EXERCISE:

                                FileInputStream fileInputStream = null;
                                try {
                                    fileInputStream = getView().getContext().openFileInput(getResources().getString(R.string.my_exercise_list_file_name));
                                    byte[] bytes = new byte[fileInputStream.available()];
                                    fileInputStream.read(bytes);
                                    String exerciseList = new String (bytes);
                                    System.out.println(exerciseList);
                                    getRouter().pushController(
                                            RouterTransaction.with
                                                    (new ImplementationExerciseListController
                                                            (getResources().getString(R.string.my_exercise_list_TAG)))
                                                    .pushChangeHandler(new VerticalChangeHandler())
                                                    .popChangeHandler(new VerticalChangeHandler()));

                                } catch (FileNotFoundException e) {
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            getResources().getString(R.string.please_create_ur_exercise_list), Toast.LENGTH_SHORT);
                                    toast.show();
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            break;
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return startToWorkoutData.size();
        }


    }

    public class StartToWorkoutData {
        private int image, name;
        private WorkoutList list;

        public StartToWorkoutData(int name, int image, WorkoutList list) {
            this.image = image;
            this.name = name;
            this.list = list;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }

        public WorkoutList getList() {
            return list;
        }

        public void setList(WorkoutList list) {
            this.list = list;
        }
    }

}
