# Range Bar Preference

Ever wanted to use a range bar in your android shared preferences. This library will help with that.  Simply add straight into your preferences.xml and you're away.

<img src="https://cloud.githubusercontent.com/assets/16980993/23489688/3235ab46-ff37-11e6-82d1-3e3febb96bd6.png" width="255">
<img src="https://cloud.githubusercontent.com/assets/16980993/23489687/3233bfe8-ff37-11e6-8917-0337462bf64c.png" width="255">

## Usage

Add this to your module dependencies:
```groovy
    compile 'com.nfx.android:range-bar-preference:0.0.5'
````

Now just add the preference to to your preference xml file like any other preference
```xml
  <com.nfx.android.rangebarpreference.RangeBarPreference
      android:key="your_pref_key"
      android:title="Preference Key"
      android:summary="A summary of the preference"

      app:rbp_measurementUnit="unit text"
      app:rbp_tickStart="1"
      app:rbp_tickEnd="100"
      app:rbp_tickInterval="1"
      app:rbp_view_defaultLowValue="40"
      app:rbp_view_defaultHighValue="60"/>
````

If you have to support API-v7+, this lib provides also RangeBarPreferenceCompat that works with preference-v7.
```xml
  <com.nfx.android.rangebarpreference.RangeBarPreferenceCompat
      android:key="your_pref_key"
      android:title="Preference Key"
      android:summary="A summary of the preference"

      app:rbp_measurementUnit="unit text"
      app:rbp_tickStart="1"
      app:rbp_tickEnd="100"
      app:rbp_tickInterval="1"
      app:rbp_view_defaultLowValue="40"
      app:rbp_view_defaultHighValue="60"/>
````

Also, the two classes provide methods to modify the preference at runtime
```java
public float getTickEnd();
public void setTickEnd(float tickEnd);

public float getTickStart();
public void setTickStart(int tickStart);

public float getTickInterval();
public void setTickInterval(int tickInterval);

public float getCurrentLowValue();
public void setCurrentLowValue(float currentLowValue);

public float getCurrentHighValue();
public void setCurrentHighValue(float currentHighValue);

public String getMeasurementUnit();
public void setMeasurementUnit(String measurementUnit);

public boolean isDialogEnabled();
public void setDialogEnabled(boolean dialogEnabled);

public void setDialogStyle(int dialogStyle);
````

As there are 2 values stored as shared preferences, they are stored as a JSON string. It follows the format below.

```JSON
  {lowValue:LOW_VALUE, highValue:HIGH_VALUE}
````

This can be a little troublesome to parse therefore static helper classes exist to mitigate these problems. Use the following examples for guidance
```java
    String jsonString = RangeBarHelper.convertValuesToJsonString(LOW_VALUE, HIGH_VALUE);

    RangeBarHelper.getLowValueFromJsonString(jsonString);
    RangeBarHelper.getHighValueFromJsonString(jsonString);
````


## Versioning

[SemVer](http://semver.org/) is used for versioning. For the versions available, see the [tags on this repository](https://github.com/nfxdevelopment/range-bar-preferences/tags).

## Authors

* **Nicholas Winder** - *Preference adaptation work* - [nfxdevelopment](https://github.com/nfxdevelopment)

## Licence
Licenced under *Apache2 licence*, so you can do whatever you want with it.
Please push back changes to help the library mature.

## Acknowledgments

* Thanks to [oli107](https://github.com/oli107) for the orignal range bar library.
