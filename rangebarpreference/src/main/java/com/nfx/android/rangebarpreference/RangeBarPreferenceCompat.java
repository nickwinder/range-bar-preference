package com.nfx.android.rangebarpreference;

import android.content.Context;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.util.AttributeSet;

/**
 * NFX Development
 * Created by nick on 29/01/17.
 */
public class RangeBarPreferenceCompat extends Preference implements PreferenceControllerDelegate.ViewStateListener, ChangeValueListener, PersistValueListener {

    private PreferenceControllerDelegate controllerDelegate;

    public RangeBarPreferenceCompat(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public RangeBarPreferenceCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public RangeBarPreferenceCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RangeBarPreferenceCompat(Context context) {
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
    public void onBindViewHolder(PreferenceViewHolder viewRoot) {
        super.onBindViewHolder(viewRoot);
        controllerDelegate.onBind(viewRoot.itemView);
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
