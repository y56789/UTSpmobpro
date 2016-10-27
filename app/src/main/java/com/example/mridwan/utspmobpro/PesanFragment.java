package com.example.mridwan.utspmobpro;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mridwan.utspmobpro.adapter.Product;
import com.example.mridwan.utspmobpro.adapter.ProductListAdapter;
import com.example.mridwan.utspmobpro.utils.SharedPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MR on 25/18/2016.
 */

public class PesanFragment extends AppCompatActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemLongClickListener {

    public static final String ARG_ITEM_ID = "product_list";

    ListView productListView;
    List<Product> products, favorite;
    ProductListAdapter productListAdapter;

    SharedPreference sharedPreference;

    public PesanFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_produk);
        setProducts();
        sharedPreference = new SharedPreference();
        productListAdapter = new ProductListAdapter(getApplicationContext(), products);

        productListView = (ListView) findViewById(R.id.list_product);
        productListView.setAdapter(productListAdapter);
        productListView.setOnItemClickListener(this);
        productListView.setOnItemLongClickListener(this);

        Button btSimpan = (Button) findViewById(R.id.simpan);
        assert btSimpan != null;
        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iPemesanan = new Intent(getApplicationContext(), KeranjangPesanan.class);
                startActivity(iPemesanan);
                finish();

            }
        });

    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = (Product) parent.getItemAtPosition(position);
        String apa = Integer.toString(position);

            Intent iOri= new Intent(PesanFragment.this, MisterPizza.class);
            iOri.putExtra("param",apa);
            startActivity(iOri);



        Toast.makeText(getApplicationContext(), product.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> arg0, View view, int position, long arg3) {
        ImageView button = (ImageView) view.findViewById(R.id.imgbtn_favorite);

        String tag = button.getTag().toString();
        if (tag.equalsIgnoreCase("grey")) {
            sharedPreference.addFavorite(getApplicationContext(), products.get(position));
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.add_favr),
                    Toast.LENGTH_SHORT).show();

            button.setTag("green");
            button.setImageResource(R.drawable.add);
        } else {
            sharedPreference.removeFavorite(getApplicationContext(), products.get(position));
            button.setTag("grey");
            button.setImageResource(R.drawable.addnon);
            Toast.makeText(getApplicationContext(),
                    getResources().getString(R.string.remove_favr),
                    Toast.LENGTH_SHORT).show();
        }

        return true;
    }

    private void setProducts() {

        Product product1 = new Product(1, "Meat Lovers",
                "Pizza dengan perpaduan rasa keju dan mayonnaise yang gurih", 75000);
        Product product2 = new Product(2, "American Favourite",
                "Pizza favorit dengan beef pepperoni, daging cincang, jamur dan saus special yang rasanya pasti memuaskan di setiap gigitan.", 75000);
        Product product3 = new Product(3, "Splitza Signature",
                "Bingung mau pizza American Favourite atau Meat Lovers? Pilih dua-duanya saja, ada dua pilihan topping dalam satu pizza!", 90000);
        Product product4 = new Product(4, "Hawaiian Chicken",
                "Rasakan manisnya nanas dengan daging dada ayam, paprika, keju mozarella dan saus tomat spicy, bikin suasana santai kayak di pantai.", 65000);
        Product product5 = new Product(5, "Tuna Melt",
                "Rahasia kelezatan pizza ini ada pada irisan tuna, jagung dan saus mayonnaise yang bakal bikin pengen nambah terus!", 70000);
        Product product6 = new Product(6, "Cheesy Galore",
                "Cheese lovers! Nikmati kelezatan keju mozarella berlapis bumbu khas Italia dan saus spesial yang pasti bikin kamu puas.", 50000);
        Product product7 = new Product(7, "Tuna Melt Spagheti",
                "Gurihnya pasta dengan saus tuna yang creamy, disajikan dengan potongan chicken chunk renyah. Double nikmatnya!", 40000);
        Product product8 = new Product(8, "Papperoni Cheese Fusilli",
                "Pasta fusili diselimuti saus keju cheddar, bertabur pepperoni sapi dan daun peterseli yang begitu lezat di lidah", 35000);
        Product product9 = new Product(9, "Beef Fettuccine",
                "Daging asap gurih dengan taburan daging sapi renyah, jamur, dan berselimut saus creamy yang wajib untuk kamu coba!", 44000);
        Product product10 = new Product(10, "Beef Spagheti",
                "Nikmati daging sapi cincang, saus tomat segar, dan tambahan taburan keju cheddar dalam Beef Spaghetti yang lezat!", 46000);

        products = new ArrayList<Product>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);
    }


}
