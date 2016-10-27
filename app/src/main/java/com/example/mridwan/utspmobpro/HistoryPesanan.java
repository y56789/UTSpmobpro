package com.example.mridwan.utspmobpro;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mridwan.utspmobpro.adapter.Product;
import com.example.mridwan.utspmobpro.adapter.ProductHistoryAdapter;
import com.example.mridwan.utspmobpro.utils.SharedPreference;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by MR on 25/10/16.
 */
public class HistoryPesanan extends AppCompatActivity {
    ListView listHistory;
    SharedPreference sharedPreference;
    List<Product> products;

    Context mContext;
    ProductHistoryAdapter productHistoryAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_history);
        mContext = getApplicationContext();

        sharedPreference = new SharedPreference();
        products = sharedPreference.getHistory(HistoryPesanan.this);

        if(products == null){
            showAlert("History Kosong",
                    "Silahkan buat pemesanan di halaman pesanan");
        }else{

            if(products.size() == 0){
                showAlert("History Kosong",
                        "Silahkan buat pemesanan di halaman pesanan");
            }

            listHistory = (ListView) findViewById(R.id.list_product);
            if(products != null){
                productHistoryAdapter = new ProductHistoryAdapter(HistoryPesanan.this, products);
                listHistory.setAdapter(productHistoryAdapter);
            }
        }

        Button btHapus = (Button) findViewById(R.id.btHapus);
        assert btHapus != null;
        btHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreference.clearHistory(HistoryPesanan.this);
                finish();
            }
        });
        double total = 0;
        if (products != null)
        {
            for(int i = 0; i<products.size(); i++){
                total += products.get(i).getPrice();



            }

        }
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        TextView txtTotal = (TextView)findViewById(R.id.total);

        assert txtTotal != null;
        txtTotal.setText("Rp "+format.format(total));
    }

    public void showAlert(String title, String message) {
        if (!HistoryPesanan.this.isFinishing()) {
            AlertDialog alertDialog = new AlertDialog.Builder(HistoryPesanan.this)
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
