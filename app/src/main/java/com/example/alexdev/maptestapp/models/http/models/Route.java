package com.example.alexdev.maptestapp.models.http.models;

/**
 * Created by alex on 25.03.18.
 */

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Route implements Parcelable
{

    @SerializedName("overview_polyline")
    @Expose
    private OverviewPolyline overviewPolyline;

    public final static Parcelable.Creator<Route> CREATOR = new Creator<Route>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Route createFromParcel(Parcel in) {
            return new Route(in);
        }

        public Route[] newArray(int size) {
            return (new Route[size]);
        }

    }
            ;

    protected Route(Parcel in) {
        this.overviewPolyline = ((OverviewPolyline) in.readValue((OverviewPolyline.class.getClassLoader())));
    }

    public Route() {
    }


    public OverviewPolyline getOverviewPolyline() {
        return overviewPolyline;
    }

    public void setOverviewPolyline(OverviewPolyline overviewPolyline) {
        this.overviewPolyline = overviewPolyline;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this).append("overviewPolyline", overviewPolyline).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(overviewPolyline);
    }

    public int describeContents() {
        return 0;
    }

}


