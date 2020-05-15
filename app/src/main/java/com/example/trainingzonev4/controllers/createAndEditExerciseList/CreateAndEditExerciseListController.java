package com.example.trainingzonev4.controllers.createAndEditExerciseList;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.VerticalChangeHandler;
import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.CreateExerciseMenuSwipeController;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.editExerciseMenuSwipeController.EditExerciseMenuSwipeController;

import java.util.ArrayList;

import butterknife.BindView;

public class CreateAndEditExerciseListController extends BaseController {


    enum MenuFilling{

        CREATE(R.drawable.ic_plus_vector_icon,R.string.create_exercise_list),
        EDIT(R.drawable.ic_description_exercise,R.string.edit_exercise_list),

        ;

        int image,name;

        MenuFilling(int image, int name) {
            this.image = image;
            this.name = name;
        }
    }

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_create_and_edit_exercise_list,container,false);
    }



    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        MenuFilling[] menuFilling= MenuFilling.values();
        ArrayList<CreateAndEditExerciseListData> createAndEditExerciseListData=new ArrayList<>();

        for (MenuFilling list : menuFilling) {
            createAndEditExerciseListData.add(new CreateAndEditExerciseListData(list.name, list.image, list));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));



        CreateAndEditExerciseListAdapter recyclerViewTableListAdapter=
                new CreateAndEditExerciseListAdapter(createAndEditExerciseListData, getResources());

        recyclerView.setAdapter(recyclerViewTableListAdapter);
    }

    class CreateAndEditExerciseListData {
        int name,image;
        MenuFilling TAG;

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public MenuFilling getTAG() {
            return TAG;
        }

        public void setTAG(MenuFilling TAG) {
            this.TAG = TAG;
        }

        public CreateAndEditExerciseListData(int name, int image, MenuFilling TAG) {
            this.name = name;
            this.image = image;
            this.TAG = TAG;
        }
    }

    class CreateAndEditExerciseListAdapter extends RecyclerView.Adapter<CreateAndEditExerciseListAdapter.CreateAndEditExerciseListHolder>{


        private ArrayList<CreateAndEditExerciseListData> createAndEditExerciseData;
        Resources resources;

        public CreateAndEditExerciseListAdapter(ArrayList<CreateAndEditExerciseListData> createAndEditExerciseData, Resources resources) {
            this.createAndEditExerciseData = createAndEditExerciseData;
            this.resources = resources;
        }

        public class CreateAndEditExerciseListHolder extends RecyclerView.ViewHolder{

            TextView textViewName;
            ImageView imageView;
            View containerForItem;

            public CreateAndEditExerciseListHolder(@NonNull View itemView) {
                super(itemView);
                textViewName=itemView.findViewById(R.id.tv_title);
                imageView=itemView.findViewById(R.id.img_dot);
                containerForItem =itemView;

            }
        }


        @NonNull
        @Override
        public CreateAndEditExerciseListAdapter.CreateAndEditExerciseListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_create_and_edit_exercise_list,parent,false);
            return new CreateAndEditExerciseListAdapter.CreateAndEditExerciseListHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CreateAndEditExerciseListAdapter.CreateAndEditExerciseListHolder holder, int position) {
            holder.imageView.setImageDrawable(resources.getDrawable(createAndEditExerciseData.get(position).getImage()));
            holder.textViewName.setText(resources.getString(createAndEditExerciseData.get(position).getName()));

            holder.containerForItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (createAndEditExerciseData.get(position).getTAG()){
                        case EDIT:

                            getRouter().pushController(
                                    RouterTransaction.with(new EditExerciseMenuSwipeController())
                                            .pushChangeHandler(new VerticalChangeHandler())
                                            .popChangeHandler(new VerticalChangeHandler()));


                            break;
                        case CREATE:
                            getRouter().pushController(
                                    RouterTransaction.with(new CreateExerciseMenuSwipeController())
                                            .pushChangeHandler(new VerticalChangeHandler())
                                            .popChangeHandler(new VerticalChangeHandler()));
                            break;
                    }
                }
            });
        }




        @Override
        public int getItemCount() {
            return createAndEditExerciseData.size();
        }


    }


}
