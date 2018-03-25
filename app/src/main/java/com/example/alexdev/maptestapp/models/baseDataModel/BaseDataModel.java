package com.example.alexdev.maptestapp.models.baseDataModel;

import android.widget.AutoCompleteTextView;

import com.example.alexdev.maptestapp.Presenter;
import com.example.alexdev.maptestapp.models.container.DataContainer;

import java.util.ArrayList;

/**
 * Created by alex on 24.03.18.
 */

public class BaseDataModel {
    private static BaseDataModel instanse;
    ArrayList<DataContainer> list = new ArrayList<>();
    private Presenter presenter;
    private ArrayList<String> registry = new ArrayList<>();

    public static BaseDataModel getInstanse(){
        if(instanse == null)
            instanse = new BaseDataModel();
        return instanse;
    }

    public ArrayList<String> getRegistry() {
        return registry;
    }

    public void setRegistry(ArrayList<String> registry) {
        this.registry = registry;
    }

    public ArrayList<DataContainer> getList() {
        return list;
    }

    public void setList(ArrayList<DataContainer> list) {
        this.list = list;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public void addTextView(AutoCompleteTextView completeTextView, Presenter presenter){
        if(list.size()>1)
            list.add(list.size()-1,new DataContainer(completeTextView,presenter));
        else
            list.add(new DataContainer(completeTextView,presenter));
    }

}
