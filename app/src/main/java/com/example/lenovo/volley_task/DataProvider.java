package com.example.lenovo.volley_task;

/**
 * Created by LENOVO on 21-03-2018.
 */

public class DataProvider {
    String Biller;
    String amount;
    String time;

    public DataProvider(String biller, String amount, String time) {
        Biller = biller;
        this.amount = amount;
        this.time = time;
    }

    public String getBiller() {
        return Biller;
    }

    public void setBiller(String biller) {
        Biller = biller;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
