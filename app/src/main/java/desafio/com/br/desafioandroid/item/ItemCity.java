package desafio.com.br.desafioandroid.item;


import com.genius.groupie.Item;

import desafio.com.br.desafioandroid.R;
import desafio.com.br.desafioandroid.databinding.ItemCityBinding;
import desafio.com.br.desafioandroid.model.City;
import desafio.com.br.desafioandroid.viewmodel.ItemCityViewModel;
import desafio.com.br.desafioandroid.viewmodel.SearchViewModel.OnCityListener;

public class ItemCity extends Item<ItemCityBinding> {

    private ItemCityViewModel viewModel;

    public ItemCity(City city, OnCityListener listener) {
        viewModel = new ItemCityViewModel(city, listener);
    }

    @Override
    public int getLayout() {
        return R.layout.item_city;
    }

    @Override
    public void bind(ItemCityBinding viewBinding, int position) {
        viewBinding.setViewModel(viewModel);
    }
}
