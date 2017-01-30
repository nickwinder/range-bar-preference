package com.nfx.android.rangebarpreference;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.sax.RootElement;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.appyvet.rangebar.RangeBar;

/**
 * NFX Development
 * Created by nick on 29/01/17.
 */
class PreferenceControllerDelegate implements RangeBar.OnRangeBarChangeListener, View.OnClickListener {

    private final String TAG = getClass().getSimpleName();

    private static final float DEFAULT_CURRENT_LOW_VALUE = 20;
    private static final float DEFAULT_CURRENT_HIGH_VALUE = 80;
    private static final float DEFAULT_TICK_START = 0;
    private static final float DEFAULT_TICK_END = 100;
    private static final float DEFAULT_TICK_INTERVAL = 1;
    private static final boolean DEFAULT_DIALOG_ENABLED = true;
    private static final boolean DEFAULT_IS_ENABLED = true;

    private static final int DEFAULT_DIALOG_STYLE = R.style.Range_Bar_Dialog_Default;

    private String measurementUnit;
    private boolean dialogEnabled = DEFAULT_DIALOG_ENABLED;

    private int dialogStyle;

    private TextView currentLowValueView;
    private TextView currentLowMeasurementView;
    private FrameLayout currentLowBottomLineView;
    private TextView currentHighValueView;
    private TextView currentHighMeasurementView;
    private FrameLayout currentHighBottomLineView;

    private RangeBar rangeBarView;
    private RelativeLayout valueHolderView;

    //view stuff
    private TextView titleView, summaryView;
    private String title;
    private String summary;
    private boolean isEnabled = DEFAULT_IS_ENABLED;

    //controller stuff
    private float currentLowValue = DEFAULT_CURRENT_LOW_VALUE;
    private float currentHighValue = DEFAULT_CURRENT_HIGH_VALUE;
    private float tickStart = DEFAULT_TICK_START;
    private float tickEnd = DEFAULT_TICK_END;
    private float tickInterval = DEFAULT_TICK_INTERVAL;
    private boolean isView = false;
    private Context context;
    private ViewStateListener viewStateListener;
    private PersistValueListener persistValueListener;
    private ChangeValueListener changeValueListener;

    interface ViewStateListener {
        boolean isEnabled();
        void setEnabled(boolean enabled);
    }

    PreferenceControllerDelegate(Context context, Boolean isView) {
        this.context = context;
        this.isView = isView;
    }

    void setPersistValueListener(PersistValueListener persistValueListener) {
        this.persistValueListener = persistValueListener;
    }

    void setViewStateListener(ViewStateListener viewStateListener) {
        this.viewStateListener = viewStateListener;
    }

    void setChangeValueListener(ChangeValueListener changeValueListener) {
        this.changeValueListener = changeValueListener;
    }

    void loadValuesFromXml(AttributeSet attrs) {
        if(attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RangeBarPreference);
            try {
                tickStart = a.getFloat(R.styleable.RangeBarPreference_rbp_tickStart, DEFAULT_TICK_START);
                tickEnd = a.getFloat(R.styleable.RangeBarPreference_rbp_tickEnd, DEFAULT_TICK_END);
                tickInterval = a.getFloat(R.styleable.RangeBarPreference_rbp_tickInterval, DEFAULT_TICK_INTERVAL);
                dialogEnabled = a.getBoolean(R.styleable.RangeBarPreference_rbp_dialogEnabled, DEFAULT_DIALOG_ENABLED);

                measurementUnit = a.getString(R.styleable.RangeBarPreference_rbp_measurementUnit);
                currentLowValue = attrs.getAttributeFloatValue("http://schemas.android.com/apk/res/android", "defaultLowValue", DEFAULT_CURRENT_LOW_VALUE);
                currentHighValue = attrs.getAttributeFloatValue("http://schemas.android.com/apk/res/android", "defaultHighValue", DEFAULT_CURRENT_HIGH_VALUE);

//                TODO make it work:
//                dialogStyle = a.getInt(R.styleable.SeekBarPreference_msbp_interval, DEFAULT_DIALOG_STYLE);

                dialogStyle = DEFAULT_DIALOG_STYLE;

                if(isView) {
                    title = a.getString(R.styleable.RangeBarPreference_rbp_view_title);

                    summary = a.getString(R.styleable.RangeBarPreference_rbp_view_summary);
                    currentLowValue = a.getFloat(R.styleable.RangeBarPreference_rbp_view_defaultLowValue, DEFAULT_CURRENT_LOW_VALUE);
                    currentHighValue = a.getFloat(R.styleable.RangeBarPreference_rbp_view_defaultHighValue, DEFAULT_CURRENT_HIGH_VALUE);

                    isEnabled = a.getBoolean(R.styleable.RangeBarPreference_rbp_view_enabled, DEFAULT_IS_ENABLED);
                }
            }
            finally {
                a.recycle();
            }
        }
    }


    @Override
    public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex,
                                      String leftPinValue, String rightPinValue) {
        if(changeValueListener != null) {
            changeValueListener.onLowValueChange(Float.valueOf(leftPinValue));
            changeValueListener.onHighValueChange(Float.valueOf(rightPinValue));
        }

        currentLowValueView.setText(leftPinValue);
        currentHighValueView.setText(rightPinValue);
    }

    void onBind(View view) {

        if(isView) {
            titleView = (TextView) view.findViewById(android.R.id.title);
            summaryView = (TextView) view.findViewById(android.R.id.summary);

            titleView.setText(title);
            summaryView.setText(summary);
        }

        view.setClickable(false);

        rangeBarView = (RangeBar) view.findViewById(R.id.range_bar);

        currentLowMeasurementView = (TextView) view.findViewById(R.id.current_low_value_measurement_unit);
        currentLowValueView = (TextView) view.findViewById(R.id.current_low_value);

        currentHighMeasurementView = (TextView) view.findViewById(R.id.current_high_value_measurement_unit);
        currentHighValueView = (TextView) view.findViewById(R.id.current_high_value);

        rangeBarView.setOnRangeBarChangeListener(this);
        // This enables the rangebar to catch drag left when in a navigation drawer
        rangeBarView.setOnTouchListener(new SeekBar.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int action = event.getAction();
                switch (action)
                {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow Drawer to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow Drawer to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle rangebar touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        rangeBarView.setTickStart(tickStart);
        rangeBarView.setTickEnd(tickEnd);
        rangeBarView.setTickInterval(tickInterval);
        rangeBarView.setDrawTicks(false);

        currentLowMeasurementView.setText(measurementUnit);
        currentHighMeasurementView.setText(measurementUnit);

        setCurrentLowValue(currentLowValue);
        setCurrentHighValue(currentHighValue);

        currentLowBottomLineView = (FrameLayout) view.findViewById(R.id.current_low_value_bottom_line);
        currentHighBottomLineView = (FrameLayout) view.findViewById(R.id.current_high_value_bottom_line);
        valueHolderView = (RelativeLayout) view.findViewById(R.id.value_holder);

        setDialogEnabled(dialogEnabled);
        setEnabled(isEnabled());
    }

    @Override
    public void onClick(final View v) {
        // TODO when implement dialog
//        new CustomValueDialog(context, dialogStyle, minValue, maxValue, currentValue)
//                .setPersistValueListener(new PersistValueListener() {
//                    @Override
//                    public boolean persistInt(int value) {
//                        setCurrentValue(value);
//                        seekBarView.setOnSeekBarChangeListener(null);
//                        seekBarView.setProgress(currentValue - minValue);
//                        seekBarView.setOnSeekBarChangeListener(PreferenceControllerDelegate.this);
//
//                        valueView.setText(String.valueOf(currentValue));
//                        return true;
//                    }
//                })
//                .show();
    }


    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
        if(titleView != null) {
            titleView.setText(title);
        }
    }

    String getSummary() {
        return summary;
    }

    void setSummary(String summary) {
        this.summary = summary;
        if(rangeBarView != null) {
            summaryView.setText(summary);
        }
    }

    boolean isEnabled() {
        if(!isView && viewStateListener != null) {
            return viewStateListener.isEnabled();
        }
        else return isEnabled;
    }

    void setEnabled(boolean enabled) {
        Log.d(TAG, "setEnabled = " + enabled);
        isEnabled = enabled;

        if(viewStateListener != null) {
            viewStateListener.setEnabled(enabled);
        }

        if(rangeBarView != null) { //theoretically might not always work
            Log.d(TAG, "view is disabled!");
            rangeBarView.setEnabled(enabled);
            currentLowValueView.setEnabled(enabled);
            currentHighValueView.setEnabled(enabled);
            valueHolderView.setClickable(enabled);
            valueHolderView.setEnabled(enabled);

            currentHighMeasurementView.setEnabled(enabled);
            currentLowMeasurementView.setEnabled(enabled);
            currentLowBottomLineView.setEnabled(enabled);
            currentHighBottomLineView.setEnabled(enabled);

            if(isView) {
                titleView.setEnabled(enabled);
                summaryView.setEnabled(enabled);
            }
        }

    }

    float getTickEnd() {
        return rangeBarView.getTickEnd();
    }

    void setTickEnd(float tickEnd) {
        rangeBarView.setTickEnd(tickEnd);
    }

    float getTickStart() {
        return rangeBarView.getTickStart();
    }

    void setTickStart(float tickStart) {
        rangeBarView.setTickStart(tickStart);
    }

    float getTickInterval() {
        return (float) rangeBarView.getTickInterval();
    }

    void setTickInterval(float interval){
        rangeBarView.setTickInterval(interval);
    }

//    int getCurrentValue() {
//        return rangeBarView.getLeftPinValue();
//    }

    void setCurrentLowValue(float value) {
        rangeBarView.setRangePinsByValue(value, rangeBarView.getRightIndex());

        if (changeValueListener != null) {
            if (!changeValueListener.onLowValueChange(value)) {
                return;
            }
        }

        String currentLowValueString;
        if (value == Math.ceil(value)) {
            currentLowValueString = String.valueOf((int) value);
        } else {
            currentLowValueString = String.valueOf(value);
        }

        if(currentLowValueView != null) {
            currentLowValueView.setText(currentLowValueString);
        }


        // TODO Make JSON into string
//        if(persistValueListener != null) {
//            persistValueListener.persistString(jsonString);
//        }
    }

    void setCurrentHighValue(float value) {
        rangeBarView.setRangePinsByValue(rangeBarView.getLeftIndex(), value);

        if (changeValueListener != null) {
            if (!changeValueListener.onHighValueChange(value)) {
                return;
            }
        }

        String currentHighValueString;
        if (value == Math.ceil(value)) {
            currentHighValueString = String.valueOf((int) value);
        } else {
            currentHighValueString = String.valueOf(value);
        }

        if(currentHighValueView != null) {
            currentHighValueView.setText(currentHighValueString);
        }

        // TODO Make JSON into string
//        if(persistValueListener != null) {
//            persistValueListener.persistString(jsonString);
//        }
    }

    String getMeasurementUnit() {
        return measurementUnit;
    }

    void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
        if(currentLowMeasurementView != null) {
            currentLowMeasurementView.setText(measurementUnit);
        }
        if(currentHighMeasurementView != null) {
            currentHighMeasurementView.setText(measurementUnit);
        }
    }

    boolean isDialogEnabled() {
        return dialogEnabled;
    }

    void setDialogEnabled(boolean dialogEnabled) {
        this.dialogEnabled = dialogEnabled;

        if(valueHolderView != null && currentLowBottomLineView != null && currentHighBottomLineView != null) {
            valueHolderView.setOnClickListener(dialogEnabled ? this : null);
            valueHolderView.setClickable(dialogEnabled);
            currentLowBottomLineView.setVisibility(dialogEnabled ? View.VISIBLE : View.INVISIBLE);
            currentHighBottomLineView.setVisibility(dialogEnabled ? View.VISIBLE : View.INVISIBLE);
        }
    }

    void setDialogStyle(int dialogStyle) {
        this.dialogStyle = dialogStyle;
    }
}