package io.github.akueisara.tourguide;

import android.location.Location;

/**
 * Created by akueisara on 9/29/2016.
 */
public class Attraction {
    private String mAttractionName;
    private String mAttractionPhone;
    private String mAttractionAddress;
    private String mImageResourceUrl;
    private Location mAtrractionLocation;

    public Attraction(String name, String phone, String address, String imageUrl, Location location) {
        mAttractionName = name;
        mAttractionPhone = phone;
        mAttractionAddress = address;
        mImageResourceUrl = imageUrl;
        mAtrractionLocation = location;
    }

    public String getAttractionName() {
        return mAttractionName;
    }

    public String getAttractionPhone() {
        return mAttractionPhone;
    }

    public Location getLocation() {
        return mAtrractionLocation;
    }

    public String getAttractionAddress() {
        return mAttractionAddress;
    }

    public String getImageResourceUrl(){
        return mImageResourceUrl;
    }

}
