package com.example.lenovo.volley_task;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LENOVO on 21-03-2018.
 */

class All {
    @SerializedName("Biller")
    String Biller;
    @SerializedName("Amount")
    String Amount;
    @SerializedName("Time")
    String TimeStamp;

    public String getBiller() {
        return Biller;
    }

    public void setBiller(String biller) {
        Biller = biller;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }
}
