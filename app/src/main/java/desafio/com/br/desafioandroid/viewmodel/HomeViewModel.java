package desafio.com.br.desafioandroid.viewmodel;


import android.databinding.ObservableBoolean;

import com.genius.groupie.GroupAdapter;

import desafio.com.br.desafioandroid.base.BaseViewModel;
import desafio.com.br.desafioandroid.item.ItemFavorite;
import desafio.com.br.desafioandroid.model.CityWeather;
import desafio.com.br.desafioandroid.model.SavedCityWeather;
import desafio.com.br.desafioandroid.utils.SaveManager;
import rx.subjects.PublishSubject;

public class HomeViewModel extends BaseViewModel {

    public final ObservableBoolean hasFavorite = new ObservableBoolean();
    public final GroupAdapter favoriteAdapter = new GroupAdapter();
    public final PublishSubject<Integer> favoriteSubject = PublishSubject.create();

    public HomeViewModel() {
        init();
    }

    public void init() {
        favoriteAdapter.clear();
        SavedCityWeather favorites = SaveManager.getFavorites();
        if (favorites != null && !favorites.getSaved().isEmpty()) {
            hasFavorite.set(true);
            for (CityWeather cityWeather : favorites.getSaved()) {
                favoriteAdapter.add(new ItemFavorite(cityWeather,
                        city -> favoriteSubject.onNext(city.getId())));
            }
        } else
            hasFavorite.set(false);

    }

    public interface OnFavoriteListener {
        void onFavoriteClicked(CityWeather city);
    }
}
