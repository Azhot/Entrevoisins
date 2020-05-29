package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.contrib.ViewPagerActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);
    private ListNeighbourActivity mActivity;
    private NeighbourApiService mNeighbourApiService = DI.getNewInstanceApiService();

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
        onView(ViewMatchers.withContentDescription("neighbours_list"))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withContentDescription("neighbours_list")).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withContentDescription("neighbours_list"))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withContentDescription("neighbours_list")).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click an item, the item is displayed
     */
    @Test
    public void myNeighboursList_onClickItem_shouldOpenItem() {
        onView(ViewMatchers.withContentDescription("neighbours_list"))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        onView(withId(R.id.neighbour_activity_layout))
                .check(matches(isDisplayed()));
    }

    /**
     * When we open an item, the item name is displayed
     */
    @Test
    public void myNeighboursList_onClickItem_shouldDisplayName() {
        onView(ViewMatchers.withContentDescription("neighbours_list"))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        onView(withId(R.id.neighbour_activity_name_txt))
                .check(matches(withText(mNeighbourApiService.getNeighbours().get(0).getName())));
    }

    /**
     * When we open favorite tab, only favorites are displayed
     */
    @Test
    public void myNeighboursList_onFavoriteTab_shouldOnlyDisplayFavorites() {
        onView(ViewMatchers.withContentDescription("neighbours_list"))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));
        onView(withId(R.id.neighbour_activity_favorite_fab))
                .perform(ViewActions.click());
        onView(isRoot())
                .perform(ViewActions.pressBack());
        onView(withId(R.id.container))
                .perform(ViewPagerActions.scrollRight());
        onView(ViewMatchers.withContentDescription("favorites_list"))
                .check(matches(hasChildCount(1)));
    }
}