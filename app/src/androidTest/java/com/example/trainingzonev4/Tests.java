package com.example.trainingzonev4;

import android.app.Activity;
import android.content.res.Resources;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.example.trainingzonev4.controllers.baseControllers.BaseController;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.CreateAndEditExerciseListController;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.CreateExerciseMenuSwipeController;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.chooseTimesExerciseController.ChooseTimesExerciseController;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.editExerciseMenuSwipeController.EditExerciseMenuSwipeController;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.exerciseListSwipeController.ExerciseListSwipeController;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.exerciseSelectionController.ExerciseSelectionController;
import com.example.trainingzonev4.controllers.createAndEditExerciseList.createExerciseMenuSwipeController.fabInfoAboutButtonController.FabInfoAboutButtonController;
import com.example.trainingzonev4.controllers.gymnasticMenuController.GymnasticMenuController;
import com.example.trainingzonev4.controllers.gymnasticMenuController.pushUps.PushUpsDetailMenuController;
import com.example.trainingzonev4.controllers.gymnasticMenuController.pushUps.PushUpsGridController;
import com.example.trainingzonev4.controllers.homeControllers.HomeMenuController;
import com.example.trainingzonev4.controllers.instagramFood.InstagramFoodMenuController;
import com.example.trainingzonev4.controllers.instagramFood.InstagramFoodMenuDetailsController;
import com.example.trainingzonev4.controllers.startToWorkoutController.StartToWorkoutController;
import com.example.trainingzonev4.controllers.startToWorkoutController.implementationExerciseListController.ExerciseDescriptionsInWorkoutControllers;
import com.example.trainingzonev4.controllers.startToWorkoutController.implementationExerciseListController.ImplementationExerciseListController;
import com.example.trainingzonev4.controllers.startToWorkoutController.implementationExerciseListController.ProcessOfPerformanceExerciseController;
import com.example.trainingzonev4.controllers.videoFromYouTubeController.VideoFromYouTubeController;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn;
import static com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItem;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class Tests {

    private int percentTest=3; //1=100% 3=33% 5=20%
    private Router router;

    @Rule
    public ControlledActivityTestRule<MainActivity> testRule = new ControlledActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() {

    }


    @Test
    public void testHomeController() {
        Activity activity = testRule.getActivity();
        RecyclerView recyclerView = testRule.getActivity().findViewById(R.id.recycler_view);
        for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
            clickListItem(R.id.recycler_view,i);
            pressBack();
        }
    }

    @Test
    public void testAllEmptyControllers(){

        testEmptyController(new HomeMenuController());
        testEmptyController(new StartToWorkoutController());
        testEmptyController(new ImplementationExerciseListController());
        testEmptyController(new CreateAndEditExerciseListController());
        testEmptyController(new ChooseTimesExerciseController());
        testEmptyController(new EditExerciseMenuSwipeController());
        testEmptyController(new ExerciseListSwipeController());
        testEmptyController(new ExerciseSelectionController());
        testEmptyController(new FabInfoAboutButtonController());
        testEmptyController(new CreateExerciseMenuSwipeController());
        testEmptyController(new GymnasticMenuController());
        testEmptyController(new InstagramFoodMenuController());
        testEmptyController(new InstagramFoodMenuDetailsController());
        testEmptyController(new ProcessOfPerformanceExerciseController());
        testEmptyController(new ExerciseDescriptionsInWorkoutControllers());
        testEmptyController(new VideoFromYouTubeController());

    }

    public void testEmptyController(BaseController controller){
        Resources resources = testRule.getActivity().getResources();
        Activity activity = testRule.getActivity();

        activity.runOnUiThread(() -> {
            router = testRule.getActivity().getRouter();
            router.setRoot(RouterTransaction.with(controller));
        });

        testRule.relaunchActivity();

    }

    @Test
    public void testRecyclerViewInControllers() {
        Resources resources = testRule.getActivity().getResources();


        testRecyclerViewInControllers(new StartToWorkoutController());
        testRecyclerViewInControllers(new CreateAndEditExerciseListController());
        testRecyclerViewInControllers(new ImplementationExerciseListController(resources.getString(R.string.workout_fresh_blood)));


    }

    @Test
    public void testGymnasticMenuController() {

        Activity activity = testRule.getActivity();
        GymnasticMenuController gymnasticMenuController = new GymnasticMenuController();

        setUp(activity, gymnasticMenuController);

        onView(withId(R.id.recycler_view)).check(matches(not(doesNotExist())));
        RecyclerView recyclerView = activity.findViewById(R.id.recycler_view);

        for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
            onView(withId(R.id.recycler_view))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));

            testChildrenRecyclerView(activity);

            pressBack();
        }

    }

    public int getPercent(int value){
        return value/percentTest;
    }

    public void testChildrenRecyclerView(Activity activity){
        onView(withId(R.id.recycler_view)).check(matches(not(doesNotExist())));
        RecyclerView recyclerChildView = activity.findViewById(R.id.recycler_view);

        int countElements=recyclerChildView.getAdapter().getItemCount();
        int step=1;

        if(getPercent(countElements)>step){
            step= getPercent(countElements);
        }

        for (int i = 0; i < countElements;) {
            clickListItem(R.id.recycler_view,i);
            pressBack();

            i+=step;
        }
    }

    @Test
    public void testProcessOfPerformanceExerciseController() {
        Activity activity = testRule.getActivity();
        Resources resources = testRule.getActivity().getResources();

        ImplementationExerciseListController implementationExerciseListController =
                new ImplementationExerciseListController(resources.getString(R.string.workout_fresh_blood));

        setUp(activity, implementationExerciseListController);

        clickOn(R.id.button_start);

        for (int i = 0; i < implementationExerciseListController.getExerciseArrayList().size(); i++) {
            onView(withId(R.id.imageButton)).check(matches(not(doesNotExist())));

            clickOn(R.id.imageButton);
        }


    }


    private void setUp(Activity activity, BaseController controller) {

        activity.runOnUiThread(() -> {
            router = testRule.getActivity().getRouter();
            router.setRoot(RouterTransaction.with(controller));
        });

    }

    public void testRecyclerViewInControllers(BaseController controller) {
        Activity activity = testRule.getActivity();

        setUp(activity, controller);


        onView(withId(R.id.recycler_view)).check(matches(not(doesNotExist())));
        RecyclerView recyclerView = activity.findViewById(R.id.recycler_view);

        int countElements=recyclerView.getAdapter().getItemCount();
        int step=1;

        if(getPercent(countElements)>step){
            step =getPercent(countElements);
        }

        for (int i = 0; i < countElements;i+=step) {
            clickListItem(R.id.recycler_view,i);
            pressBack();
        }

        testRule.relaunchActivity();
    }


}

class ControlledActivityTestRule<T extends Activity> extends ActivityTestRule<T> {
    public ControlledActivityTestRule(Class<T> activityClass) {
        super(activityClass, false);
    }

    public ControlledActivityTestRule(Class<T> activityClass, boolean initialTouchMode) {
        super(activityClass, initialTouchMode, true);
    }

    public ControlledActivityTestRule(Class<T> activityClass, boolean initialTouchMode, boolean launchActivity) {
        super(activityClass, initialTouchMode, launchActivity);
    }

    public void finish() {
        finishActivity();
    }

    public void relaunchActivity() {
        finishActivity();
        launchActivity();
    }

    public void launchActivity() {
        launchActivity(getActivityIntent());
    }
}

