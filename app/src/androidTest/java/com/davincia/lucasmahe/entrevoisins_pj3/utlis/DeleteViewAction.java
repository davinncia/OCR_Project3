package com.davincia.lucasmahe.entrevoisins_pj3.utlis;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import android.view.View;

import com.davincia.lucasmahe.entrevoisins_pj3.R;

import org.hamcrest.Matcher;

public class DeleteViewAction implements ViewAction {

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }
    @Override
    public String getDescription() {
        return "Click on specific button";
    }
    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.item_list_delete_button);
        // Maybe check for null
        button.performClick();
    }
}
