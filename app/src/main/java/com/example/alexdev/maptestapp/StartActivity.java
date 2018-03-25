package com.example.alexdev.maptestapp;

import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alexdev.maptestapp.models.http.Client;
import com.example.alexdev.maptestapp.models.http.api.ApiGeoCode;
import com.example.alexdev.maptestapp.models.http.models.ResponseGeoCode;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class StartActivity extends AppCompatActivity implements  View.OnClickListener, OnMapReadyCallback,AdapterView.OnItemSelectedListener{
    public final String MAPVIEW_BUNDLE_KEY= "MAPVIEW_KEY";
    private Presenter presenter=null;
    private MapView mMapView;
    private GoogleMap map;
    private LinearLayout container;
    private LinearLayout footer;
    private LinearLayout header;
    private Spinner registry;
    private TextView hideButton;
    private Button addField;
    private Button startBt;
    private ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        mMapView = (MapView) findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        presenter=new Presenter(this);
        initFooterAndContainer();
        initTextView();
        initHeader();
    }

    public void writeMarker(ArrayList<LatLng> list){
       if(list!=null&&list.size()>0){
           for(LatLng latLng:list){
               map.addMarker(new MarkerOptions()
                       .position(latLng));
           }
       }
    }

    public void clearMarker(){
        map.clear();
    }


    private void initFooterAndContainer(){
        footer       = (LinearLayout) findViewById(R.id.footer);
        hideButton   = (TextView) findViewById(R.id.show_bt);
        container    = (LinearLayout) findViewById(R.id.container_text_view);
        addField     = (Button) findViewById(R.id.add_field_bt);

        addField.setOnClickListener(this);
        hideButton.setOnClickListener(this);
    }


    private void initTextView(){
        AutoCompleteTextView textViewStart  = (AutoCompleteTextView) findViewById(R.id.autocomplete_start);
        AutoCompleteTextView textViewEnd    = (AutoCompleteTextView) findViewById(R.id.autocomplete_end);

        presenter.initTextView(textViewStart);
        presenter.initTextView(textViewEnd);

    }

    public void addPath(String s){
        if(registry.getVisibility()!=View.VISIBLE)
            registry.setVisibility(View.VISIBLE);
        adapter.add(s);
        adapter.notifyDataSetChanged();
    }

    private void initHeader(){
        header   = (LinearLayout) findViewById(R.id.header);
        registry = (Spinner) findViewById(R.id.regitry_spinner);
        startBt  = (Button) findViewById(R.id.start_bt);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>());
        registry.setAdapter(adapter);

        registry.setOnItemSelectedListener(this);
        startBt.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }



    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.show_bt: {
                if(footer.getVisibility() == View.VISIBLE) {
                    footer.setVisibility(View.GONE);
                    hideButton.setText("show");
                }
                else {
                    footer.setVisibility(View.VISIBLE);
                    hideButton.setText("hide");
                }
                break;
            }

            case R.id.add_field_bt: {
                if(container.getChildCount()<3) {
                    AutoCompleteTextView textView = new AutoCompleteTextView(this);
                    container.addView(textView);
                    presenter.initTextView(textView);

                    if(container.getChildCount()==3)
                        addField.setVisibility(View.GONE);
                }
                break;
            }
            case R.id.start_bt: {
                presenter.writePath();
                break;
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemSelected(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void writePath(String path){
        List<LatLng> mPoints = PolyUtil.decode(path);
        PolylineOptions line = new PolylineOptions();
        line.width(30f).color(Color.RED);
        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();
        map.clear();
        for (int i = 0; i < mPoints.size(); i++) {
            if (i == 0) {
                MarkerOptions startMarkerOptions = new MarkerOptions()
                        .position(mPoints.get(i));
                map.addMarker(startMarkerOptions);
            } else if (i == mPoints.size() - 1) {
                MarkerOptions endMarkerOptions = new MarkerOptions()
                        .position(mPoints.get(i));
                map.addMarker(endMarkerOptions);
            }
            line.add(mPoints.get(i));
            latLngBuilder.include(mPoints.get(i));
        }
        map.addPolyline(line);
        int size = getResources().getDisplayMetrics().widthPixels;
        LatLngBounds latLngBounds = latLngBuilder.build();
        CameraUpdate track = CameraUpdateFactory.newLatLngBounds(latLngBounds, size, size, 25);
        map.moveCamera(track);

        moveMarker(mPoints);
    }

    public void moveMarker(final List<LatLng> positions) {
        final long time = 60;
        final List<LatLng> list = new ArrayList();
        list.addAll(positions);
        final Handler handler = new Handler();
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.image_avto);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(positions.get(0))
                .title("its a car")
                .snippet("its a car")
                .icon(icon);
        final Marker marker = map.addMarker(markerOptions);

        handler.post(new Runnable() {
            @Override
            public void run() {
                marker.setPosition(list.get(0));

                list.remove(0);
                if (list.size() != 0) {
                    // Post again
                    handler.postDelayed(this, time);
                } else {
                        marker.setVisible(false);
                        map.clear();

                }
            }
        });
    }

    public void showError(String errorMessage){
        final Snackbar errorSnackBar = Snackbar.make(findViewById(android.R.id.content),
                errorMessage, Snackbar.LENGTH_LONG);
        errorSnackBar.getView().setBackgroundColor(getResources().getColor(R.color.snack_bar_error_color));
        errorSnackBar.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
