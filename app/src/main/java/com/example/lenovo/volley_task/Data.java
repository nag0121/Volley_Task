package com.example.lenovo.volley_task;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LENOVO on 21-03-2018.
 */

public class Data {
    @SerializedName("IncomingList")
    List<IncomingList> incomingLists ;
    @SerializedName("OutgoingList")
    List<OutgoingList> outgoingLists ;
    @SerializedName("AllTranList")
    List<All> allList ;

    public List<IncomingList> getIncomingLists() {
        return incomingLists;
    }

    public void setIncomingLists(List<IncomingList> incomingLists) {
        this.incomingLists = incomingLists;
    }

    public List<OutgoingList> getOutgoingLists() {
        return outgoingLists;
    }

    public void setOutgoingLists(List<OutgoingList> outgoingLists) {
        this.outgoingLists = outgoingLists;
    }

    public List<All> getAllList() {
        return allList;
    }

    public void setAllList(List<All> allList) {
        this.allList = allList;
    }
}
