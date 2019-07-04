package com.davincia.lucasmahe.entrevoisins_pj3.neighbour_list;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.davincia.lucasmahe.entrevoisins_pj3.R;
import com.davincia.lucasmahe.entrevoisins_pj3.ui.ListNeighbourActivity;
import com.davincia.lucasmahe.entrevoisins_pj3.utlis.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
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

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        //TODO: ERROR AmbiguousViewMatcherException...
       // onView(ViewMatchers.withId(R.id.list_neighbours))
       //         .check(matches(hasMinimumChildCount(1)));
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        //Same ERROR AmbiguousViewMatcherException...
        // Given : We remove the element at position 2
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * When we click on item, correct detail activity is launched
     */
    @Test
    public void myNeighbourList_clickOnItem_opensDetailActivity(){
        //Given : we click on item in neighbourList
        onView(withId(R.id.list_neighbours)).perform();
    }

//
//  ○ test vérifiant qu’au démarrage de ce nouvel écran, le TextView indiquant
//    le nom de l’utilisateur en question est bien rempli ;
//  ○ test vérifiant qu’au clic sur le bouton de suppression, la liste d’utilisateurs
//    compte bien un utilisateur en moins ;
//  ○ test vérifianque l’onglet Favoris n’affiche que les voisins marqués comme
//    favoris.
}
