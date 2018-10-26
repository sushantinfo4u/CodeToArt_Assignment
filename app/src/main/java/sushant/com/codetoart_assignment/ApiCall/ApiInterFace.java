package sushant.com.codetoart_assignment.ApiCall;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterFace {
    @GET("movie/upcoming")
    Call<MovieData> getupcomingMovies(@Query("api_key") String apiKey);
}
