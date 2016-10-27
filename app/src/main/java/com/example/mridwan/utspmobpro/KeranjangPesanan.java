package com.example.mridwan.utspmobpro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mridwan.utspmobpro.adapter.Product;
import com.example.mridwan.utspmobpro.adapter.ProductListAdapter;
import com.example.mridwan.utspmobpro.utils.SharedPreference;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by MR on 25/10/16.
 */
public class KeranjangPesanan extends AppCompatActivity {
    public static final String ARG_ITEM_ID = "favorite_list";

    ListView favoriteList;
    SharedPreference sharedPreference;
    List<Product> favorites;

    ProductListAdapter productListAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_order);


        sharedPreference = new SharedPreference();
        favorites = sharedPreference.getFavorites(getApplicationContext());

        if (favorites == null) {
            showAlert(getResources().getString(R.string.no_favorites_items),
                    getResources().getString(R.string.no_favorites_msg));
        } else {

            if (favorites.size() == 0) {
                showAlert(
                        getResources().getString(R.string.no_favorites_items),
                        getResources().getString(R.string.no_favorites_msg));
            }

            favoriteList = (ListView) findViewById(R.id.list_product);
            if (favorites != null) {
                productListAdapter = new ProductListAdapter(getApplicationContext(), favorites);
                favoriteList.setAdapter(productListAdapter);

                favoriteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView<?> parent, View arg1,
                                            int position, long arg3) {

                    }
                });

                favoriteList
                        .setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                            @Override
                            public boolean onItemLongClick(
                                    AdapterView<?> parent, View view,
                                    int position, long id) {

                                ImageView button = (ImageView) view
                                        .findViewById(R.id.imgbtn_favorite);

                                String tag = button.getTag().toString();
                                if (tag.equalsIgnoreCase("grey")) {
                                    sharedPreference.addFavorite(getApplicationContext(),
                                            favorites.get(position));
                                    Toast.makeText(
                                            getApplicationContext(),
                                            getResources().getString(
                                                    R.string.add_favr),
                                            Toast.LENGTH_SHORT).show();

                                    button.setTag("green");
                                    button.setImageResource(R.drawable.add);
                                } else {
                                    sharedPreference.removeFavorite(getApplicationContext(),
                                            favorites.get(position));
                                    button.setTag("grey");
                                    button.setImageResource(R.drawable.add);
                                    productListAdapter.remove(favorites
                                            .get(position));
                                    Toast.makeText(
                                            getApplicationContext(),
                                            getResources().getString(
                                                    R.string.remove_favr),
                                            Toast.LENGTH_SHORT).show();
                                }
                                return true;
                            }
                        });
            }
        }

        double total = 0;
        if (favorites != null)
        {


            for(int i = 0; i<favorites.size(); i++){
                total += favorites.get(i).getPrice();



            }

        }
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        TextView txtTotal = (TextView)findViewById(R.id.total);
        assert txtTotal != null;
        txtTotal.setText("Rp "+format.format(total));
      //  txtTotal.setText(format.format("Rp"+Double.parseDouble(String.valueOf(total))));


        Log.e("Value", String.valueOf(total));

        Button btSimpan = (Button) findViewById(R.id.simpan);
        assert btSimpan != null;
        btSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favorites = sharedPreference.getFavorites(getApplicationContext());
                sharedPreference.addTransaksi(getApplicationContext(), favorites);
                //sharedPreference.clearFavorite(activity);

                SharedPreferences settings;
                SharedPreferences.Editor editor;

                settings = getSharedPreferences(SharedPreference.PREFS_NAME, Context.MODE_PRIVATE);
                editor = settings.edit();

                editor.remove(SharedPreference.FAVORITES);
                editor.commit();

                Toast.makeText(getApplicationContext(), "Data Telah Masuk", Toast.LENGTH_SHORT).show();

                // Staring MainActivity
                Intent i = new Intent(getApplicationContext(), HistoryPesanan.class);
                startActivity(i);
                finish();
                finish();



            }
        });
    }

    public void showAlert(String title, String message) {
        if (!KeranjangPesanan.this.isFinishing()) {
            AlertDialog alertDialog = new AlertDialog.Builder(KeranjangPesanan.this)
                    .create();
            alertDialog.setTitle(title);
            alertDialog.setMessage(message);
            alertDialog.setCancelable(false);

            // setting OK Button
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            // activity.finish();
                            getFragmentManager().popBackStackImmediate();
                        }
                    });
            alertDialog.show();
        }
    }
}
