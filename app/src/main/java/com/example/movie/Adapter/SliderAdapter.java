package com.example.movie.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.example.movie.R;
import com.example.movie.domain.SliderItems;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.sliderviewHolder> {
    private List<SliderItems> sliderItems;
    private ViewPager2 viewPager;
    private Context context;

    public SliderAdapter(List<SliderItems> sliderItems, ViewPager2 viewPager) {
        this.sliderItems = sliderItems;
        this.viewPager = viewPager;
    }

    @NonNull
    @Override
    public sliderviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new sliderviewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slide_item_container, parent,false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull sliderviewHolder holder, int position) {
        holder.setImage(sliderItems.get(position));
        if (position == sliderItems.size()-2){
            viewPager.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    public class sliderviewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public sliderviewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlider);
        }
        void setImage(SliderItems sliderItems){
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(60));

            Glide.with(context)
                    .load(sliderItems.getImage())
                    .apply(requestOptions)
                    .into(imageView);
        }
    }

    private Runnable runnable = new Runnable(){
        @Override
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
