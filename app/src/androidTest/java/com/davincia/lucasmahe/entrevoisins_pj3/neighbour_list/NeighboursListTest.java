package com.davincia.lucasmahe.entrevoisins_pj3.neighbour_list;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.davincia.lucasmahe.entrevoisins_pj3.R;
import com.davincia.lucasmahe.entrevoisins_pj3.data.service.NeighbourApiService;
import com.davincia.lucasmahe.entrevoisins_pj3.di.DI;
import com.davincia.lucasmahe.entrevoisins_pj3.model.Neighbour;
import com.davincia.lucasmahe.entrevoisins_pj3.repositories.NeighboursRepository;
import com.davincia.lucasmahe.entrevoisins_pj3.ui.ListNeighbourActivity;
import com.davincia.lucasmahe.entrevoisins_pj3.ui.NeighbourDetailActivity;
import com.davincia.lucasmahe.entrevoisins_pj3.utlis.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.davincia.lucasmahe.entrevoisins_pj3.utlis.RecyclerViewItemCountAssertion.withItemCount;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    private NeighboursRepository repository;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());

        repository = NeighboursRepository.getInstance();
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When we click on item, detail activity is launched
     */
    @Test
    public void myNeighbourList_clickOnItem_opensDetailActivity(){

        //Given : we click on item in neighbourList
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Details activity opens
        Intents.init();
        //TODO: won't work
        intended(hasComponent(NeighbourDetailActivity.class.getName()));
    }

    /**
     * When we click on item, detail activity contains right neighbour
     */
    @Test
    public void myNeighbourList_clickOnItem_DetailActivity_ContainsRightName(){

        //Given When : we click on item in neighbourList
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //Then :
        onView(withId(R.id.textView_detail_name)).check(matches(withText("Caroline")));

    }

    /**
     * Check if favorites fragment shows only neighbour marked as favorites
     */
    @Test
    public void myNeighbourList_FavoriteFragment_DisplaysOnlyFavoriteNeighbours(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        List<Integer> favorites = repository.getFavoriteIds(context);

        //Perform a click on favorite tab
        onView(ViewMatchers.withContentDescription(R.string.tab_favorites_title)).perform(click());

        //Make sure number of favorite is the right size
        onView(withId(R.id.list_favorites)).check(withItemCount(favorites.size()));

    }
}
