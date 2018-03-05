package desafio.com.br.desafioandroid.item;


import com.genius.groupie.Item;

import desafio.com.br.desafioandroid.R;
import desafio.com.br.desafioandroid.databinding.ItemCityBinding;
import desafio.com.br.desafioandroid.databinding.ItemFavoriteBinding;
import desafio.com.br.desafioandroid.model.City;
import desafio.com.br.desafioandroid.model.CityWeather;
import desafio.com.br.desafioandroid.viewmodel.HomeViewModel;
import desafio.com.br.desafioandroid.viewmodel.HomeViewModel.OnFavoriteListener;
import desafio.com.br.desafioandroid.viewmodel.ItemCityViewModel;
import desafio.com.br.desafioandroid.viewmodel.ItemFavoriteViewModel;
import desafio.com.br.desafioandroid.viewmodel.SearchViewModel.OnCityListener;

public class ItemFavorite extends Item<ItemFavoriteBinding> {

    private ItemFavoriteViewModel viewModel;

    public ItemFavorite(CityWeather cityWeather, OnFavoriteListener listener) {
        viewModel = new ItemFavoriteViewModel(cityWeather, listener);
    }

    @Override
    public int getLayout() {
        return R.layout.item_favorite;
    }

    @Override
    public void bind(ItemFavoriteBinding viewBinding, int position) {
        viewBinding.setViewModel(viewModel);
    }
}
