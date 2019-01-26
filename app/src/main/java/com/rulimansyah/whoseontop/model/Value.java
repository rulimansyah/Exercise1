package com.rulimansyah.whoseontop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Value {

    @SerializedName("categories")
    private List<String> mCategories;
    @SerializedName("id")
    private Long mId;
    @SerializedName("joke")
    private String mJoke;

    public List<String> getCategories() {
        return mCategories;
    }

    public void setCategories(List<String> categories) {
        mCategories = categories;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        mId = id;
    }

    public String getJoke() {
        return mJoke;
    }

    public void setJoke(String joke) {
        mJoke = joke;
    }

}
