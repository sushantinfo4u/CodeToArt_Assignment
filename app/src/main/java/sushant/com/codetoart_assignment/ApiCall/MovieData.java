package sushant.com.codetoart_assignment.ApiCall;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieData implements Serializable {

    @SerializedName("results")
    private ArrayList<MovieResponse> results;
    public ArrayList<MovieResponse> getResults() {
        return results;
    }
    public void setResults(ArrayList<MovieResponse> results) {
        this.results = results;
    }
}
