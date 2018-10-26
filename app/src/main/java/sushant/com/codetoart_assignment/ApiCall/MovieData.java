package sushant.com.codetoart_assignment.ApiCall;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MovieData {
    @SerializedName("results")
    private ArrayList<MovieResponse> results;
    public ArrayList<MovieResponse> getResults() {
        return results;
    }
    public void setResults(ArrayList<MovieResponse> results) {
        this.results = results;
    }
}
