package com.nfx.android.rangebarpreference;

import android.content.Context;
import android.os.Build;
import android.preference.Preference;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * NFX Development
 * Created by nick on 29/01/17.
 */
public class RangeBarPreference extends Preference implements PreferenceControllerDelegate.ViewStateListener , ChangeValueListener, PersistValueListener {

    private PreferenceControllerDelegate controllerDelegate;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RangeBarPreference(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public RangeBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public RangeBarPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RangeBarPreference(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        setLayoutResource(R.layout.range_bar_view_layout);

        controllerDelegate = new PreferenceControllerDelegate(getContext(), false);

        controllerDelegate.setViewStateListener(this);
        controllerDelegate.setPersistValueListener(this);
        controllerDelegate.setChangeValueListener(this);

        controllerDelegate.loadValuesFromXml(attrs);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        controllerDelegate.onBind(view);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        super.onSetInitialValue(restorePersistedValue, defaultValue);
        // TODO Use to setup values in object
    }

    @Override
    public boolean persistString(String value) {
        return super.persistString(value);
    }

    @Override
    public boolean onChangeValue(String jsonString) {
        return callChangeListener(jsonString);
    }
}
