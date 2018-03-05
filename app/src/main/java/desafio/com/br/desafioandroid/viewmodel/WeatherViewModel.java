package desafio.com.br.desafioandroid.viewmodel;


import android.databinding.ObservableField;

import desafio.com.br.desafioandroid.base.BaseViewModel;
import desafio.com.br.desafioandroid.base.Constants;
import desafio.com.br.desafioandroid.model.CityWeather;
import desafio.com.br.desafioandroid.service.Service;
import desafio.com.br.desafioandroid.utils.SaveManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

import static desafio.com.br.desafioandroid.base.Constants.APPID;
import static desafio.com.br.desafioandroid.base.Constants.LANG;
import static desafio.com.br.desafioandroid.base.Constants.UNITS;

public class WeatherViewModel extends BaseViewModel {

    public final ObservableField<String> city = new ObservableField<>();
    public final ObservableField<String> temperature = new ObservableField<>();
    public final ObservableField<String> minTemperature = new ObservableField<>();
    public final ObservableField<String> maxTemperature = new ObservableField<>();
    public final ObservableField<String> description = new ObservableField<>();
    public final ObservableField<String> icon = new ObservableField<>();

    public final PublishSubject<Boolean> showProgressDialog = PublishSubject.create();
    public final PublishSubject<Void> callError = PublishSubject.create();

    private int cityId;
    private CityWeather cityWeather;

    public WeatherViewModel(int cityId) {
        this.cityId = cityId;
        init();
    }

    public void saveFavorite() {
        SaveManager.saveFavorite(cityWeather);
    }

    private void init() {
        Service.getInstance()
                .getWeather(cityId, APPID, LANG, UNITS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(() -> showProgressDialog.onNext(true))
                .subscribe(response -> {
                            cityWeather = response;
                            showData(response);
                            showProgressDialog.onNext(false);
                        }
                        , error -> {
                            showProgressDialog.onNext(false);
                            errorSubject.onNext(new Throwable(error.getMessage()));
                            callError.onNext(null);
                        });
    }

    private void showData(CityWeather cityWeather) {
        city.set(cityWeather.getName());
        temperature.set(cityWeather.getMain().getTemp() + "°C");
        minTemperature.set(cityWeather.getMain().getTempMin() + "°C");
        maxTemperature.set(cityWeather.getMain().getTempMax() + "°C");
        if (!cityWeather.getWeather().isEmpty()) {
            description.set(cityWeather.getWeather().get(0).getDescription());
            icon.set(Constants.ICON_BASE_URL + cityWeather.getWeather().get(0).getIcon() + ".png");
        }
    }
}
