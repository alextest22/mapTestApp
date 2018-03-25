package com.example.alexdev.maptestapp.models.http.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.alexdev.maptestapp.models.http.models.subModels.StatusResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by alex on 25.03.18.
 */

public class ApiResponseDirections implements Parcelable
{


    @SerializedName("routes")
    @Expose
    private List<Route> routes = new ArrayList<Route>();
    @SerializedName("status")
    @Expose
    private StatusResponse status;
    public final static Parcelable.Creator<ApiResponseDirections> CREATOR = new Creator<ApiResponseDirections>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ApiResponseDirections createFromParcel(Parcel in) {
            return new ApiResponseDirections(in);
        }

        public ApiResponseDirections[] newArray(int size) {
            return (new ApiResponseDirections[size]);
        }

    }
            ;

    protected ApiResponseDirections(Parcel in) {
        in.readList(this.routes, (Route.class.getClassLoader()));
        this.status = ((StatusResponse) in.readValue((StatusResponse.class.getClassLoader())));
    }

    public ApiResponseDirections() {
    }





    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("routes", routes).append("status", status).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(routes);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }


}
