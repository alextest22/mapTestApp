package com.example.alexdev.maptestapp.models.http.models;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddressComponent implements Parcelable
    {

        @SerializedName("long_name")
        @Expose
        private String longName;
        @SerializedName("short_name")
        @Expose
        private String shortName;
        @SerializedName("types")
        @Expose
        private List<String> types = new ArrayList<String>();
        public final static Parcelable.Creator<AddressComponent> CREATOR = new Creator<AddressComponent>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public AddressComponent createFromParcel(Parcel in) {
                return new AddressComponent(in);
            }

            public AddressComponent[] newArray(int size) {
                return (new AddressComponent[size]);
            }

        }
                ;

        protected AddressComponent(Parcel in) {
            this.longName = ((String) in.readValue((String.class.getClassLoader())));
            this.shortName = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(this.types, (java.lang.String.class.getClassLoader()));
        }

        public AddressComponent() {
        }

        public String getLongName() {
            return longName;
        }

        public void setLongName(String longName) {
            this.longName = longName;
        }

        public AddressComponent withLongName(String longName) {
            this.longName = longName;
            return this;
        }

        public String getShortName() {
            return shortName;
        }

        public void setShortName(String shortName) {
            this.shortName = shortName;
        }

        public AddressComponent withShortName(String shortName) {
            this.shortName = shortName;
            return this;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public AddressComponent withTypes(List<String> types) {
            this.types = types;
            return this;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("longName", longName).append("shortName", shortName).append("types", types).toString();
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(longName);
            dest.writeValue(shortName);
            dest.writeList(types);
        }

        public int describeContents() {
            return 0;
        }

    }