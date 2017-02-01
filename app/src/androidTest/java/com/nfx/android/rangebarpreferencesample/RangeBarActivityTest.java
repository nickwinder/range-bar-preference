package com.nfx.android.rangebarpreferencesample;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class RangeBarActivityTest {

    @Rule
    public ActivityTestRule<RangeBarActivity> mActivityTestRule = new ActivityTestRule<>
            (RangeBarActivity.class);

    static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void rangeBarDialogTest() {

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.low_value_holder), isDisplayed()));
        linearLayout.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.customValue), isDisplayed()));
        appCompatEditText.perform(replaceText("30"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.btn_apply), withText("apply"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.current_low_value), withText("30"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.low_value_holder),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("30")));

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.high_value_holder), isDisplayed()));
        linearLayout2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.customValue), isDisplayed()));
        appCompatEditText2.perform(replaceText("70"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.customValue), withText("70"), isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.btn_apply), withText("apply"), isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.current_high_value), withText("70"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.high_value_holder),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("70")));

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.high_value_holder), isDisplayed()));
        linearLayout3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.customValue), isDisplayed()));
        appCompatEditText4.perform(replaceText("10"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.btn_apply), withText("apply"), isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.current_low_value), withText("10"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.low_value_holder),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("10")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.current_high_value), withText("30"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.high_value_holder),
                                        0),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("30")));

        ViewInteraction linearLayout4 = onView(
                allOf(withId(R.id.low_value_holder), isDisplayed()));
        linearLayout4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.customValue), isDisplayed()));
        appCompatEditText5.perform(replaceText("50"), closeSoftKeyboard());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(R.id.btn_apply), withText("apply"), isDisplayed()));
        appCompatButton5.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.current_low_value), withText("30"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.low_value_holder),
                                        0),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("30")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.current_high_value), withText("50"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.high_value_holder),
                                        0),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("50")));
    }

    @Test
    public void rangeBarClickLowValue() {
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.low_value_holder), isDisplayed()));
        linearLayout.perform(click());

        dialogPopupTest();
    }

    @Test
    public void rangeBarClickHighValue() {
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.high_value_holder), isDisplayed()));
        linearLayout.perform(click());

        dialogPopupTest();
    }

    @Test
    public void checkInitialDisplay() {
        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("Range Bar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Range Bar")));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.summary), withText("This is a range bar"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.list),
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

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.current_high_value_measurement_unit), withText("samples"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.high_value_holder),
                                        0),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText("samples")));
    }

    private void dialogPopupTest() {
        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.dialog_color_area),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                0),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.dialog_icon), withContentDescription("compass logo"),
                        childAtPosition(
                                allOf(withId(R.id.dialog_color_area),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget
                                                        .LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.dialog_title), withText("Enter custom value"),
                        childAtPosition(
                                allOf(withId(R.id.dialog_color_area),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget
                                                        .LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Enter custom value")));

        ViewInteraction button = onView(
                allOf(withId(R.id.btn_cancel),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout
                                                .class),
                                        2),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.btn_apply),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout
                                                .class),
                                        2),
                                1),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.minValue), withText("0"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout
                                                .class),
                                        1),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("0")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.maxValue), withText("100"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout
                                                .class),
                                        1),
                                4),
                        isDisplayed()));
        textView5.check(matches(withText("100")));
    }
}
