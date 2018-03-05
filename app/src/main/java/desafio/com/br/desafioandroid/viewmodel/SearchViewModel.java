package desafio.com.br.desafioandroid.viewmodel;


import com.genius.groupie.GroupAdapter;
import com.google.gson.Gson;

import java.util.List;

import desafio.com.br.desafioandroid.base.BaseViewModel;
import desafio.com.br.desafioandroid.item.ItemCity;
import desafio.com.br.desafioandroid.model.City;
import desafio.com.br.desafioandroid.model.Data;
import rx.subjects.PublishSubject;

public class SearchViewModel extends BaseViewModel {

    public final PublishSubject<City> citySubject = PublishSubject.create();
    public final GroupAdapter cityAdapter = new GroupAdapter();

    public SearchViewModel(String json) {
        init(json);
    }

    private void init(String json) {
        Data data = new Gson().fromJson(json, Data.class);
        List<City> cityList = data.getCities();

        for (City city : cityList) {
            cityAdapter.add(new ItemCity(city, citySubject::onNext));
        }
    }

    public interface OnCityListener {
        void onCityClicked(City city);
    }
}
