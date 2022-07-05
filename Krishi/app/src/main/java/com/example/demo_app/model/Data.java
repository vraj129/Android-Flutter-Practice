package com.example.demo_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {

    @SerializedName("other_mandi")
    @Expose
    private ArrayList<Other_mandi> other_mandis;

    public ArrayList<Other_mandi> getOther_mandis() {
        return other_mandis;
    }

    public void setOther_mandis(ArrayList<Other_mandi> other_mandis) {
        this.other_mandis = other_mandis;
    }

    @Override
    public String toString() {
        return "Data{" +
                "other_mandis=" + other_mandis +
                '}';
    }
}
