package com.example.trainingzonev4.controllers.instagramFood;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler;
import com.example.trainingzonev4.R;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.network.NetworkService;
import com.example.trainingzonev4.network.dataClasses.Datum;
import com.example.trainingzonev4.network.dataClasses.InstagramDataPOJO;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstagramFoodMenuController extends BaseController {

    public InstagramFoodMenuController() {
    }

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_instagram_food_menu,container,false);
    }

    @Override
    protected void onViewBound(@NonNull View view) {
        super.onViewBound(view);

        NetworkService.getInstance()
                .getJSONApi()
                .getPostWithID(getResources().getString(R.string.instagram_token)
                        , getResources().getString(R.string.instagram_field_search))
                .enqueue(new Callback<InstagramDataPOJO>() {

                    @Override
                    public void onResponse(@NonNull Call<InstagramDataPOJO> call, @NonNull Response<InstagramDataPOJO> response) {
                        InstagramDataPOJO instagramDataPOJO = response.body();

                        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

                        InstagramFoodMenuListAdapter recyclerViewTableListAdapter =
                                new InstagramFoodMenuListAdapter(instagramDataPOJO.getData(), getResources());

                        recyclerView.setAdapter(recyclerViewTableListAdapter);

                    }

                    @Override
                    public void onFailure(@NonNull Call<InstagramDataPOJO> call, @NonNull Throwable t) {

                        t.printStackTrace();

                    }
                });
    }

    class InstagramFoodMenuListAdapter extends RecyclerView.Adapter<InstagramFoodMenuListAdapter.InstagramFoodMenuListHolder>{

        private List<Datum> foodList;
        Resources resources;

        public InstagramFoodMenuListAdapter(List<Datum> exerciseListDataLinkedList, Resources resources) {
            this.foodList = exerciseListDataLinkedList;
            this.resources = resources;
        }


        public class InstagramFoodMenuListHolder extends RecyclerView.ViewHolder {

            TextView textViewName;
            View containerForItems;
            ImageView imageView;

            public InstagramFoodMenuListHolder(@NonNull View itemView) {
                super(itemView);
                textViewName = itemView.findViewById(R.id.textViewName);
                imageView = itemView.findViewById(R.id.imageView);

                containerForItems = itemView;

            }

        }



        @NonNull
        @Override
        public InstagramFoodMenuListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_instagram_image, parent, false);
            return new InstagramFoodMenuListHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull InstagramFoodMenuListHolder holder, int position) {

            holder.textViewName.setText(foodList.get(position).getCaption());
            Picasso.with(getView().getContext())
                    .load(foodList.get(position).getMediaUrl())
                    .placeholder(R.drawable.blur)
                    .error(R.drawable.error_download)
                    .into(holder.imageView);

            holder.containerForItems.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ViewGroup controllerContainerSelectExercise = Objects.requireNonNull(getActivity())
                            .findViewById(R.id.controller_container);

                    Router childRouterOnClick = getChildRouter(controllerContainerSelectExercise)
                            .setPopsLastView(true);

                    childRouterOnClick.pushController(
                            RouterTransaction.with(new InstagramFoodMenuDetailsController(foodList.get(position).getCaption(),
                                    holder.imageView.getDrawable()))
                                    .pushChangeHandler(new HorizontalChangeHandler())
                                    .popChangeHandler(new HorizontalChangeHandler()));
                }
            });

        }

        @Override
        public int getItemCount() {
            return foodList.size();
        }
    }


}
