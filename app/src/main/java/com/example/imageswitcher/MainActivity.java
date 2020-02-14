package com.example.imageswitcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory {
    LinearLayout linearLayoutHorizontal;
    ImageSwitcher imgSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSwitcher = findViewById(R.id.imgswitch);
        linearLayoutHorizontal = (LinearLayout) findViewById(R.id.horizontallayout);
        imgSwitcher.setFactory(MainActivity.this);
        //animations for image switcher
        imgSwitcher.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));
        imgSwitcher.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));

        for (int index = 0; index < Car.carimage.length; index++) {
            final int i = index;
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(Car.carimage[index]);
            setLayotParamsForImageView(imageView);
            imageView.setPadding(100, 100, 100, 100);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgSwitcher.setImageResource(Car.carimage[i]);
                    Toast.makeText(MainActivity.this, "This is" + Car.carnames[i], Toast.LENGTH_SHORT).show();

                }
            });
            linearLayoutHorizontal.addView(imageView);
        }
    }

    public void setLayotParamsForImageView(ImageView imageView) {
        imageView.setLayoutParams(new LinearLayout.LayoutParams(1000, 1000));
    }

    @Override
    public View makeView() {
        ImageView imgView = new ImageView(MainActivity.this);
        imgView.setScaleType(ImageView.ScaleType.FIT_XY);
        imgView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return imgView;
    }
}

