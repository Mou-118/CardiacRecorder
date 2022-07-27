package com.example.cardiacrecorder;

import java.util.ArrayList;

public class RecordList {
    public static ArrayList<DataModel>myArraylist=new ArrayList<>();


    public void addRecord(DataModel dataModel){
        if (myArraylist.contains(dataModel)){
            throw new IllegalArgumentException();
        }
        myArraylist.add(dataModel);
    }

    public void deleteRecord(int position){
        if(position>=0 && position<myArraylist.size()){
            myArraylist.remove(position);
        }
        else
        {
            throw new IllegalArgumentException();
        }

    }

    public int count() {
        return myArraylist.size();
    }
}
