package com.example.movie.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.movie.Adapter.ActorListAdapter;
import com.example.movie.Adapter.CategoryEachFilmListAdapter;
import com.example.movie.databinding.ActivityDetailBinding;
import com.example.movie.domain.FilmItem;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private StringRequest mStringRequest;
    private int idFilm;
    private RecyclerView.Adapter adapterActorList, adapterCategory;
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        idFilm = getIntent().getIntExtra("id", 0);

        initView();
        sendRequest();
    }

    private void sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this);
        binding.progressBarDetail.setVisibility(View.GONE);

        mStringRequest = new StringRequest(Request.Method.GET, "https://moviesapi.ir/api/v1/movies/" + idFilm, response -> {
            Gson gson = new Gson();
            binding.progressBarDetail.setVisibility(View.GONE);

            FilmItem item = gson.fromJson(response, FilmItem.class);

            Glide.with(DetailActivity.this)
                    .load(item.getPoster())
                    .into(binding.picDetail);

            binding.movieNameTxt.setText(item.getTitle());
            binding.movieStar.setText(item.getImdbRating());
            binding.movieTime.setText(item.getRuntime());
            binding.movieSummary.setText(item.getPlot());
            binding.movieActorInfo.setText(item.getActors());

            if (item.getImages() != null) {
                adapterActorList = new ActorListAdapter(item.getImages());
                binding.actorView.setAdapter(adapterActorList);
            }
            if (item.getGenres() != null) {
                adapterCategory = new CategoryEachFilmListAdapter(item.getGenres());
                binding.genreView.setAdapter(adapterCategory);
            }
        }, error -> binding.progressBarDetail.setVisibility(View.GONE));
        mRequestQueue.add(mStringRequest);
    }

    private void initView() {
        binding.actorView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.genreView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        binding.backBtn.setOnClickListener(view -> finish());
    }
}