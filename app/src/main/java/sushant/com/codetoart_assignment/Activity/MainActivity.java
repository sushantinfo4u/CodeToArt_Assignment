package sushant.com.codetoart_assignment.Activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Movie;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
    private AlertDialog.Builder builder;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getString(R.string.movielist));
        ButterKnife.bind(this);
        movieDataArrayList=new ArrayList<>();
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        callMovieApi();
    }

    private void callMovieApi() {
        ApiInterFace apiService = BuilderClass.getClient().create(ApiInterFace.class);
        Call<MovieData> call = apiService.getupcomingMovies(API_KEY);
        call.enqueue(new Callback<MovieData>() {
            @Override
            public void onResponse(Call<MovieData> call, Response<MovieData> response) {
                if (response.code()==200&&response.body()!=null) {
                    progressDialog.dismiss();
                    movieDataArrayList = response.body().getResults();
                    Gson gson=new Gson();
                    Log.i("test", "Response " + gson.toJson(response.body().getResults()).toString());
                    setDataToView();
                }
            }
            @Override
            public void onFailure(Call<MovieData> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this,  " Error  "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setDataToView() {
        listMovie.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            MovieAdapter movieAdapter=new MovieAdapter(getApplicationContext(),movieDataArrayList);
        listMovie.setAdapter(movieAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_icon,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.action_settings){
            developerInfo();
        }
        return super.onOptionsItemSelected(item);
    }

    private void developerInfo() {
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom);
        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText("Developed by \n \n Sushant Ratnakar Suryawanshi \n 9175886657 \n sushantinfo4u@gmail.com ");
       /* ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(R.drawable.ic_info);*/

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}

