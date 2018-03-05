package desafio.com.br.desafioandroid.base;


import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class DataBinder {

    @BindingAdapter({"android:visibility"})
    public static void setVisibility(View view, boolean isVisible) {

        int visibility = isVisible ? View.VISIBLE : View.GONE;
        view.setVisibility(visibility);
    }

    @BindingAdapter({"imageUrl", "placeholder"})
    public static void setImageUrl(ImageView imageView, String url, Drawable placeholder) {
        Context context = imageView.getContext();

        Picasso.with(context)
                .load(url)
                .placeholder(placeholder)
                .resize(300, 300)
                .centerCrop()
                .noFade()
                .into(imageView);
    }
}
