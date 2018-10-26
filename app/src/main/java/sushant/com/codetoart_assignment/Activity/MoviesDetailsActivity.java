package sushant.com.codetoart_assignment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sushant.com.codetoart_assignment.ApiCall.MovieData;
import sushant.com.codetoart_assignment.ApiCall.MovieResponse;
import sushant.com.codetoart_assignment.R;

public class MoviesDetailsActivity extends AppCompatActivity {

    @BindView(R.id.imgPoster)  ImageView imgPoster;
    @BindView(R.id.txtTitle)  TextView txtTitle;
    @BindView(R.id.txtOverView)  TextView txtOverView;
    @BindView(R.id.ratingBar)  RatingBar ratingBar;
    private MovieResponse movieResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        if (intent!=null){
            movieResponse= (MovieResponse) intent.getSerializableExtra("list");
             Picasso.with(getApplicationContext()).load(movieResponse.getPoster_path()).into(imgPoster);
             txtTitle.setText("Title :"+movieResponse.getTitle());
             txtOverView.setText("OverView :"+movieResponse.getOverview());
             ratingBar.setRating(Float.parseFloat(movieResponse.getVote_average()));
        }
    }
}
