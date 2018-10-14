package com.example.cas.geoguess;

public class GeoObject {

    private boolean mInEurope;
    private int mGeoImageName;

    public GeoObject(Boolean mInEurope, int mGeoImageName) {
        this.mInEurope = mInEurope;
        this.mGeoImageName = mGeoImageName;
    }

    public boolean getmInEurope() {
        return mInEurope;
    }

    public void setmInEurope(boolean mInEurope) {
        this.mInEurope = mInEurope;
    }

    public int getmGeoImageName() {
        return mGeoImageName;
    }

    public void setmGeoImageName(int mGeoImageName) {
        this.mGeoImageName = mGeoImageName;
    }

    public static final boolean[] PRE_DEFINED_GEO_OBJECT_IN_EUROPE = {
            true, false, false, true, false, true, true, false
    };

    public static final int[] PRE_DEFINED_GEO_OBJECT_IMAGE_IDS = {
            R.drawable.img1_yes_denmark,
            R.drawable.img2_no_canada,
            R.drawable.img3_no_bangladesh,
            R.drawable.img4_yes_kazachstan,
            R.drawable.img5_no_colombia,
            R.drawable.img6_yes_poland,
            R.drawable.img7_yes_malta,
            R.drawable.img8_no_thailand
    };
}
