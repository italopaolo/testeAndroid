package desafio.com.br.desafioandroid.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


import cn.pedant.SweetAlert.SweetAlertDialog;
import desafio.com.br.desafioandroid.App;
import desafio.com.br.desafioandroid.R;
import desafio.com.br.desafioandroid.base.Constants;
import desafio.com.br.desafioandroid.model.CityWeather;
import desafio.com.br.desafioandroid.model.SavedCityWeather;

public class AndroidUtils {

    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("citylist.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static boolean isNetworkAvailable() {
        Context context = App.get();
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService
                (Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static void hideKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void alertDialog(Context context, String message) {
        new MaterialDialog.Builder(context)
                .content(message)
                .positiveText(android.R.string.ok)
                .show();
    }

    public static SweetAlertDialog getProgressDialog(Context context, String message) {

        SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
        dialog.getProgressHelper().setBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        dialog.setTitleText(message);
        dialog.setContentText(null);
        dialog.setCancelable(true);
        return dialog;
    }


}
