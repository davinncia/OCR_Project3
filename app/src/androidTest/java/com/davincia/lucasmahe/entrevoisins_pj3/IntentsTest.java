package com.davincia.lucasmahe.entrevoisins_pj3;

import android.content.Intent;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.davincia.lucasmahe.entrevoisins_pj3.repositories.NeighboursRepository;
import com.davincia.lucasmahe.entrevoisins_pj3.ui.ListNeighbourActivity;
import com.davincia.lucasmahe.entrevoisins_pj3.ui.NeighbourDetailActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class IntentsTest {

        @Rule
        public IntentsTestRule<ListNeighbourActivity> mActivityRule =
                new IntentsTestRule<>(ListNeighbourActivity.class);

    @Test
    public void validateIntentSentToDetailActivity() {
        //Given : we click on item in neighbourList
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //THEN : Intent
        intended(hasComponent(NeighbourDetailActivity.class.getName()));
    }
}
