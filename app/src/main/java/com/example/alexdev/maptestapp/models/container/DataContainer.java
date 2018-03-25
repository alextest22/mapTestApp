package com.example.alexdev.maptestapp.models.container;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.alexdev.maptestapp.models.geoCodeModel.GeoCodeModel;
import com.example.alexdev.maptestapp.Presenter;
import com.example.alexdev.maptestapp.models.http.models.BodyGeoCodeResponse;
import com.example.alexdev.maptestapp.models.http.models.Location;
import com.example.alexdev.maptestapp.models.http.models.ResponseGeoCode;
import com.example.alexdev.maptestapp.models.http.models.subModels.StatusResponse;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by alex on 24.03.18.
 */

public class DataContainer {
    private AutoCompleteTextView completeTextView;
    public List<BodyGeoCodeResponse> apiResponse=null;
    private LatLng marker = null;
    private Presenter presenter;


    public LatLng getMarker() {
        return marker;
    }


    public DataContainer(final AutoCompleteTextView completeTextView, final Presenter presenter) {
        this.completeTextView = completeTextView;
        final Context context = completeTextView.getContext();
        this.presenter = presenter;

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_list_item_1,new String[0]);
        completeTextView.setAdapter(adapter);
        completeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int pos,
                                    long id) {
                if(pos<apiResponse.size()){
                   Location location = apiResponse.get(pos).getGeometry().getLocation();
                   marker=new LatLng(location.getLat(),location.getLng());
                    presenter.writeMarker();
                }
            }
        });



        completeTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(count == 0) {
                    marker = null;
                    presenter.clearMarker();
                    presenter.writeMarker();
                }
                if(count>2){
                    uploadData(completeTextView,adapter);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void uploadData(final AutoCompleteTextView textView, final ArrayAdapter<String> adapter){
        Context context = textView.getContext();
        GeoCodeModel.getData(textView.getText().toString(),context,new Observer<ResponseGeoCode>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseGeoCode apiBasicResponse) {
                if(apiBasicResponse.getStatus().equals(StatusResponse.ok))
                {
                    apiResponse = apiBasicResponse.getbodyGeoCodeResponses();

                    List<String> place = new ArrayList<>();
                    for(BodyGeoCodeResponse resp: apiBasicResponse.getbodyGeoCodeResponses()){
                        place.add(resp.getFormattedAddress());
                    }
                    adapter.getFilter().filter(null);
                    adapter.clear();
                    adapter.addAll(place.toArray(new String[0]));
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {

            }
        });
    }

}
