package sushant.com.codetoart_assignment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import sushant.com.codetoart_assignment.Activity.MoviesDetailsActivity;
import sushant.com.codetoart_assignment.ApiCall.MovieResponse;
import sushant.com.codetoart_assignment.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    private Context context;
    private ArrayList<MovieResponse> movieResponsesList;
    public MovieAdapter(Context context, ArrayList<MovieResponse> movieResponses) {
        this.context = context;
        this.movieResponsesList = movieResponses;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final MovieResponse movieData=movieResponsesList.get(position);

        if (movieData.getAdult().equals("true"))
            holder.txtAdult.setText("ADULT");
        else
            holder.txtAdult.setVisibility(View.GONE);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, MoviesDetailsActivity.class);
                intent.putExtra("list",  movieData);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        String[] movieTitle=movieData.getTitle().split(" ");
        holder.txtMovieName.setText(movieTitle[0]);
        holder.txtReleaseDate.setText(movieData.getRelease_date());

        String baseImageUrl="https://image.tmdb.org/t/p/w500"+movieData.getPoster_path();
        Picasso.with(context).load(baseImageUrl).into(holder.imgPoster);
//        Picasso.with(context).load("tCBxnZwLiY1BOKw3tH6AxHZdqPh.jpg").into(holder.imgPoster);
    }
    @Override
    public int getItemCount() {
        return movieResponsesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout mainLayout;
        private ImageView imgPoster;
        private TextView txtMovieName,txtReleaseDate,txtAdult;
        public ViewHolder(View itemView) {
            super(itemView);
            imgPoster=itemView.findViewById(R.id.imgPoster);
            txtMovieName=itemView.findViewById(R.id.txtMovieName);
            txtReleaseDate=itemView.findViewById(R.id.txtReleaseDate);
            txtAdult=itemView.findViewById(R.id.txtAdult);
            mainLayout=itemView.findViewById(R.id.mainLayout);
        }
    }
}
