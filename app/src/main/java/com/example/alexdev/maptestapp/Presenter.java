package com.example.alexdev.maptestapp;

import android.widget.AutoCompleteTextView;

import com.example.alexdev.maptestapp.models.baseDataModel.BaseDataModel;
import com.example.alexdev.maptestapp.models.container.DataContainer;
import com.example.alexdev.maptestapp.models.directionsModel.DirectionsModel;
import com.example.alexdev.maptestapp.models.http.models.ApiResponseDirections;
import com.example.alexdev.maptestapp.models.http.models.subModels.StatusResponse;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.content.ContentValues.TAG;


/**
 * Created by alexdev on 23.03.18.
 */

public class Presenter {
    private BaseDataModel baseDataModel;
    private StartActivity activity;


    public Presenter(StartActivity activity) {
        this.baseDataModel = BaseDataModel.getInstanse();
        this.activity = activity;
        baseDataModel.setPresenter(this);
    }


    public void initTextView(AutoCompleteTextView completeTextView){
        baseDataModel.addTextView(completeTextView,this);
    }

    private ArrayList<LatLng> getMarkers(){
        ArrayList<LatLng> list = new ArrayList<>();
        for (DataContainer container:baseDataModel.getList()){
            if(container.getMarker() != null)
                list.add(container.getMarker());

        }
        return list;
    }


    public void clearMarker(){

        activity.clearMarker();
    }

    public void writeMarker(){

        activity.writeMarker(getMarkers());
    }

    public void writePath(){
        ArrayList<LatLng> list = getMarkers();

        if(list.size() >= 2){
            getPathFromBE();
        }
        else {
            activity.showError("You need at least 2 address");
        }

    }

    private void getPathFromBE() {
        ArrayList<LatLng> list  = getMarkers();
        String start            = LatToStr(list.get(0));
        String end              = LatToStr(list.get(list.size() - 1));
        String waypints         = null;
        if(list.size()!=2){
            waypints="";
            for(LatLng mid:list.subList(1,list.size() - 1)){
                waypints= waypints.concat("|" + LatToStr(mid));
            }
            waypints= waypints.substring(1);
        }
        DirectionsModel.getData(start,end,waypints,activity,new Observer<ApiResponseDirections>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ApiResponseDirections apiBasicResponse) {
                if(apiBasicResponse.getStatus().equals(StatusResponse.ok)) {
                    String path = apiBasicResponse.getRoutes().get(0).getOverviewPolyline().getPoints();
                    activity.writePath(path);
                    baseDataModel.getRegistry().add(path);
                    activity.addPath("path "+baseDataModel.getRegistry().size());
                }
                else activity.showError("some problem with data");
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

    public void onItemSelected(int idSelected){
        activity.writePath(baseDataModel.getRegistry().get(idSelected));
    }


    private String LatToStr(LatLng data){
        return String.format("%1$f,%2$f",data.latitude,data.longitude);
    }
}
