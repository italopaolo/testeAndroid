package desafio.com.br.desafioandroid.utils;


import android.text.TextUtils;

import com.google.gson.Gson;

import desafio.com.br.desafioandroid.model.CityWeather;
import desafio.com.br.desafioandroid.model.SavedCityWeather;

import static desafio.com.br.desafioandroid.App.getPreferencesManager;
import static desafio.com.br.desafioandroid.base.Constants.SharedPreferences.SAVED_CITY_WEATHER;

public class SaveManager {

    public static SavedCityWeather getFavorites() {
        if (!TextUtils.isEmpty(getPreferencesManager().getString(SAVED_CITY_WEATHER)))
            return new Gson().fromJson(
                    getPreferencesManager().getString(SAVED_CITY_WEATHER),
                    SavedCityWeather.class);
        return null;
    }

    public static void saveFavorite(CityWeather cityWeather) {
        SavedCityWeather saved = getFavorites();

        if (saved != null) {
            for (CityWeather favorite : saved.getSaved()) {
                if (favorite.getId() == cityWeather.getId()) {
                    //do this to update the data
                    saved.getSaved().remove(favorite);
                    saved.getSaved().add(cityWeather);
                    getPreferencesManager().setValue(SAVED_CITY_WEATHER, new Gson().toJson(saved));
                    return;
                }
            }

            saved.getSaved().add(cityWeather);
        } else {
            saved = new SavedCityWeather();
            saved.getSaved().add(cityWeather);
        }
        getPreferencesManager().setValue(SAVED_CITY_WEATHER, new Gson().toJson(saved));

    }

}
