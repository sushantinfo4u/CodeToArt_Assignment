package sushant.com.codetoart_assignment.Activity;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import sushant.com.codetoart_assignment.Adapter.MovieAdapter;
import sushant.com.codetoart_assignment.ApiCall.ApiInterFace;
import sushant.com.codetoart_assignment.ApiCall.BuilderClass;
import sushant.com.codetoart_assignment.ApiCall.MovieData;
import sushant.com.codetoart_assignment.ApiCall.MovieResponse;
import sushant.com.codetoart_assignment.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listMovie)RecyclerView listMovie;
    private static String API_KEY="b7cd3340a794e5a2f35e3abb820b497f";
    private ArrayList<MovieResponse> movieDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        movieDataArrayList=new ArrayList<>();
        callMovieApi();
    }

    private void callMovieApi() {
        ApiInterFace apiService = BuilderClass.getClient().create(ApiInterFace.class);
        Call<MovieData> call = apiService.getupcomingMovies(API_KEY);
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                if (response.code()==200&&response.body()!=null) {
                    movieDataArrayList = response.body().getResults();
                    Gson gson=new Gson();
                    Log.i("test", "Response " + gson.toJson(response.body().getResults()).toString());
                    setDataToView();
                }
            }
            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                Toast.makeText(MainActivity.this,  " Error  "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setDataToView() {
        listMovie.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        MovieAdapter movieAdapter=new MovieAdapter(getApplicationContext(),movieDataArrayList);
        listMovie.setAdapter(movieAdapter);
    }
}
