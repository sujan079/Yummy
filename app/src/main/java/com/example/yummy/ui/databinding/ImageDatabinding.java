package com.example.yummy.ui.databinding;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.example.yummy.R;

public class ImageDatabinding {
    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, int imgRes) {
        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imgRes)
                .into(view);
    }


    @BindingAdapter("imageUrl")
    public static void setImage(ImageView view, String imgUrl) {
        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imgUrl)
                .into(view);
    }

}
