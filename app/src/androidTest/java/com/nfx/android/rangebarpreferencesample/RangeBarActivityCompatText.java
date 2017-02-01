package com.nfx.android.rangebarpreferencesample;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * NFX Development
 * Created by nick on 1/02/17.
 */
@RunWith(AndroidJUnit4.class)
public class RangeBarActivityCompatText extends RangeBarActivityTest {
    @Rule
    public ActivityTestRule<RangeBarActivityCompat> mActivityTestRule = new ActivityTestRule<>
            (RangeBarActivityCompat.class);

    @Test
    @Override
    public void checkInitialDisplay() {
        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Range Bar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Range Bar")));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.summary), withText("This is a range bar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("This is a range bar")));

        ViewInteraction view = onView(
                allOf(withId(R.id.range_bar),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout
                                                .class),
                                        2),
                                0),
                        isDisplayed()));
        view.check(matches(isDisplayed()));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.current_low_value),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.low_value_holder),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.current_low_value_measurement_unit), withText("samples"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.low_value_holder),
                                        0),
                                1),
                        isDisplayed()));
        textView4.check(matches(withText("samples")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.current_high_value),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.high_value_holder),
                                        0),
                                0),
                        isDisplayed()));
        textView5.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.current_high_value_measurement_unit), withText("samples"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.high_value_holder),
                                        0),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("samples")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.current_high_value_measurement_unit), withText("samples"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.high_value_holder),
                                        0),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText("samples")));

    }

}
