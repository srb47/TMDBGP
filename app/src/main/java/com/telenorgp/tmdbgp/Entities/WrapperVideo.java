package com.telenorgp.tmdbgp.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


// TODO
public class WrapperVideo {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("results")
    @Expose
    private List<ResourceVideo> results = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ResourceVideo> getResults() {
        return results;
    }

    public void setResults(List<ResourceVideo> results) {
        this.results = results;
    }
}
