package com.example.mridwan.utspmobpro;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mridwan.utspmobpro.R;

/**
 * Created by MR on 25/10/16.
 */
public class MisterPizza extends AppCompatActivity {

    Intent tangkap;
    TextView judul , konten;
    ImageView gambar;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_s1);

        judul= (TextView)findViewById(R.id.txtJudul);
        konten= (TextView)findViewById(R.id.txtKonten);
        gambar= (ImageView)findViewById(R.id.imgGambar);
        tangkap = getIntent();


String alamat;

        String paramHasil = tangkap.getStringExtra("param");

        if (paramHasil.equals("0"))
        {
            judul.setText("Meat Lovers");
            gambar.setImageResource(R.drawable.pizza1);
            konten.setText("Pizza dengan perpaduan rasa keju dan mayonnaise yang gurih");

        }else  if (paramHasil.equals("1"))

        {
            judul.setText("American Favourite");
            gambar.setImageResource(R.drawable.pizza2);
            konten.setText("Pizza favorit dengan beef pepperoni, daging cincang, jamur dan saus special yang rasanya pasti memuaskan di setiap gigitan.");

        }else  if (paramHasil.equals("2"))
        {
            judul.setText("Splitza Signature");
            gambar.setImageResource(R.drawable.pizza3);
            konten.setText("Bingung mau pizza American Favourite atau Meat Lovers? Pilih dua-duanya saja, ada dua pilihan topping dalam satu pizza!");

        }else  if (paramHasil.equals("3"))
        {
            judul.setText("Hawaiian Chicken");
            gambar.setImageResource(R.drawable.pizza4);
            konten.setText("Rasakan manisnya nanas dengan daging dada ayam, paprika, keju mozarella dan saus tomat spicy, bikin suasana santai kayak di pantai.");

        }else  if (paramHasil.equals("4"))
        {
            judul.setText("Tuna Melt");
            gambar.setImageResource(R.drawable.pizza5);
            konten.setText("Rahasia kelezatan pizza ini ada pada irisan tuna, jagung dan saus mayonnaise yang bakal bikin pengen nambah terus!");

        }else  if (paramHasil.equals("5"))
        {
            judul.setText("Cheesy Galore");
            gambar.setImageResource(R.drawable.pizza6);
            konten.setText("Cheese lovers! Nikmati kelezatan keju mozarella berlapis bumbu khas Italia dan saus spesial yang pasti bikin kamu puas.");

        }else  if (paramHasil.equals("6"))
        {
            judul.setText("Tuna Melt Spagheti");
            gambar.setImageResource(R.drawable.pasta7);
            konten.setText("Gurihnya pasta dengan saus tuna yang creamy, disajikan dengan potongan chicken chunk renyah. Double nikmatnya!");

        }else  if (paramHasil.equals("7"))
        {
            judul.setText("Papperoni Cheese Fusilli");
            gambar.setImageResource(R.drawable.pasta8);
            konten.setText("Pasta fusili diselimuti saus keju cheddar, bertabur pepperoni sapi dan daun peterseli yang begitu lezat di lidah");

        }else  if (paramHasil.equals("8"))
        {
            judul.setText("Beef Fettuccine");
            gambar.setImageResource(R.drawable.pasta9);
            konten.setText("Daging asap gurih dengan taburan daging sapi renyah, jamur, dan berselimut saus creamy yang wajib untuk kamu coba!");

        }else  if (paramHasil.equals("9"))
        {
            judul.setText("Beef Spagheti");
            gambar.setImageResource(R.drawable.pasta10);
            konten.setText("Nikmati daging sapi cincang, saus tomat segar, dan tambahan taburan keju cheddar dalam Beef Spaghetti yang lezat!");

        }

    }
}
