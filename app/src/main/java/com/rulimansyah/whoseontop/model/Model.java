package com.rulimansyah.whoseontop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Model {

    @SerializedName("type")
    private String mType;
    @SerializedName("value")
    private List<Value> mValue;

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public List<Value> getValue() {
        return mValue;
    }

    public void setValue(List<Value> value) {
        mValue = value;
    }

}