package com.davincia.lucasmahe.entrevoisins_pj3;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.davincia.lucasmahe.entrevoisins_pj3.repositories.NeighboursRepository;
import com.davincia.lucasmahe.entrevoisins_pj3.ui.ListNeighbourActivity;
import com.davincia.lucasmahe.entrevoisins_pj3.utlis.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.davincia.lucasmahe.entrevoisins_pj3.utlis.RecyclerViewItemCountAssertion.withItemCount;
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

        //click on first neighbour (caroline)
        onView(withId(R.id.list_neighbours)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //add to favorites
        onView(withId(R.id.fab_favorite)).perform(click());
        //return
        onView(withId(R.id.image_details_back_arrow)).perform(click());
        ////Perform a click on favorite tab
        onView(ViewMatchers.withContentDescription(R.string.tab_favorites_title)).perform(click());
        //Check size & name
        onView(withId(R.id.list_favorites)).check(withItemCount(1));
        onView(withId(R.id.list_favorites)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.textView_detail_name)).check(matches(withText("Caroline")));


    }
}