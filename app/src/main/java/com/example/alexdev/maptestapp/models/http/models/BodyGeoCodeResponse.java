package com.example.alexdev.maptestapp.models.http.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexdev on 23.03.18.
 */

public class BodyGeoCodeResponse implements Parcelable
{

    @SerializedName("address_components")
    @Expose
    private List<AddressComponent> addressComponents = new ArrayList<AddressComponent>();
    @SerializedName("formatted_address")
    @Expose
    private String formattedAddress;
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("types")
    @Expose
    private List<String> types = new ArrayList<String>();
    public final static Parcelable.Creator<BodyGeoCodeResponse> CREATOR = new Creator<BodyGeoCodeResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BodyGeoCodeResponse createFromParcel(Parcel in) {
            return new BodyGeoCodeResponse(in);
        }

        public BodyGeoCodeResponse[] newArray(int size) {
            return (new BodyGeoCodeResponse[size]);
        }

    }
            ;

    protected BodyGeoCodeResponse(Parcel in) {
        this.formattedAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.geometry = ((Geometry) in.readValue((Geometry.class.getClassLoader())));
        this.placeId = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.types, (java.lang.String.class.getClassLoader()));
    }

    public BodyGeoCodeResponse() {
    }

    public List<AddressComponent> getAddressComponents() {
        return addressComponents;
    }

    public void setAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
    }

    public BodyGeoCodeResponse withAddressComponents(List<AddressComponent> addressComponents) {
        this.addressComponents = addressComponents;
        return this;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public BodyGeoCodeResponse withFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
        return this;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public BodyGeoCodeResponse withGeometry(Geometry geometry) {
        this.geometry = geometry;
        return this;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public BodyGeoCodeResponse withPlaceId(String placeId) {
        this.placeId = placeId;
        return this;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public BodyGeoCodeResponse withTypes(List<String> types) {
        this.types = types;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("addressComponents", addressComponents).append("formattedAddress", formattedAddress).append("geometry", geometry).append("placeId", placeId).append("types", types).toString();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(addressComponents);
        dest.writeValue(formattedAddress);
        dest.writeValue(geometry);
        dest.writeValue(placeId);
        dest.writeList(types);
    }

    public int describeContents() {
        return 0;
    }

}