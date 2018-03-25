package com.example.alexdev.maptestapp.models.http.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.alexdev.maptestapp.models.http.models.BodyGeoCodeResponse;
import com.example.alexdev.maptestapp.models.http.models.subModels.StatusResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexdev on 23.03.18.
 */

public class ResponseGeoCode implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<BodyGeoCodeResponse> bodyGeoCodeResponses = new ArrayList<BodyGeoCodeResponse>();
    @SerializedName("status")
    @Expose
    private StatusResponse status;
    public final static Parcelable.Creator<ResponseGeoCode> CREATOR = new Creator<ResponseGeoCode>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ResponseGeoCode createFromParcel(Parcel in) {
            return new ResponseGeoCode(in);
        }

        public ResponseGeoCode[] newArray(int size) {
            return (new ResponseGeoCode[size]);
        }

    };

    protected ResponseGeoCode(Parcel in) {
        in.readList(this.bodyGeoCodeResponses, (BodyGeoCodeResponse.class.getClassLoader()));
        this.status = ((StatusResponse) in.readValue((StatusResponse.class.getClassLoader())));
    }


    public ResponseGeoCode() {
    }

    public List<BodyGeoCodeResponse> getbodyGeoCodeResponses() {
        return bodyGeoCodeResponses;
    }

    public void setbodyGeoCodeResponses(List<BodyGeoCodeResponse> bodyGeoCodeRespons) {
        this.bodyGeoCodeResponses = bodyGeoCodeRespons;
    }

    public ResponseGeoCode withbodyGeoCodeResponses(List<BodyGeoCodeResponse> bodyGeoCodeRespons) {
        this.bodyGeoCodeResponses = bodyGeoCodeRespons;
        return this;
    }

    public StatusResponse getStatus() {
        return status;
    }

    public void setStatus(StatusResponse status) {
        this.status = status;
    }

    public ResponseGeoCode withStatus(StatusResponse status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("bodyGeoCodeResponses", bodyGeoCodeResponses).append("status", status).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(bodyGeoCodeResponses);
        dest.writeValue(status);
    }

    public int describeContents() {
        return 0;
    }

}