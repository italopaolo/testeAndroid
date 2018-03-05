package desafio.com.br.desafioandroid.viewmodel;


import android.databinding.ObservableField;

import desafio.com.br.desafioandroid.base.BaseViewModel;
import desafio.com.br.desafioandroid.model.City;
import desafio.com.br.desafioandroid.viewmodel.SearchViewModel.OnCityListener;

public class ItemCityViewModel extends BaseViewModel{

    public final ObservableField<String> cityName = new ObservableField<>();

    private City city;
    private OnCityListener listener;

    public ItemCityViewModel(City city, OnCityListener listener) {
        this.city = city;
        this.listener = listener;
        cityName.set(city.getName());
    }

    public void onClick(){
        listener.onCityClicked(city);
    }
}
