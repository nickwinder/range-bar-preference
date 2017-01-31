package com.nfx.android.rangebarpreference;

import org.json.JSONException;

/**
 * NFX Development
 * Created by nick on 1/02/17.
 */
class RangeBarHelper {
    static String formatFloatToString(float value) {
        String valueString;
        if (value == Math.ceil(value)) {
            valueString = String.valueOf((int) value);
        } else {
            valueString = String.valueOf(value);
        }

        return valueString;
    }

    static String convertValuesToJsonString(float lowValue, float highValue) {
        String jsonString = null;
        try {
            RangeBarValueJSON rangeBarValueJSON = new RangeBarValueJSON();
            rangeBarValueJSON.setLowValue(lowValue);
            rangeBarValueJSON.setHighValue(highValue);

            jsonString = rangeBarValueJSON.toString();
        } catch(JSONException e) {
            e.printStackTrace();
        }

        return jsonString;
    }
}
