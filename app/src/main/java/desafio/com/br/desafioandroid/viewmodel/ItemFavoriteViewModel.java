package desafio.com.br.desafioandroid.viewmodel;


import android.databinding.ObservableField;

import desafio.com.br.desafioandroid.base.BaseViewModel;
import desafio.com.br.desafioandroid.model.CityWeather;
import desafio.com.br.desafioandroid.viewmodel.HomeViewModel.OnFavoriteListener;

public class ItemFavoriteViewModel extends BaseViewModel {

    public final ObservableField<String> city = new ObservableField<>();
    public final ObservableField<String> weather = new ObservableField<>();
    public final ObservableField<String> temperature = new ObservableField<>();

    private CityWeather cityWeather;
    private OnFavoriteListener listener;

    public ItemFavoriteViewModel(CityWeather cityWeather, OnFavoriteListener listener) {
        this.cityWeather = cityWeather;
        this.listener = listener;
        init(cityWeather);
    }

    public void onClick() {
        listener.onFavoriteClicked(cityWeather);
    }

    private void init(CityWeather cityWeather) {
        city.set(cityWeather.getName());
        if (!cityWeather.getWeather().isEmpty())
            weather.set(cityWeather.getWeather().get(0).getDescription());
        temperature.set(cityWeather.getMain().getTemp() + "°C");
    }


}
