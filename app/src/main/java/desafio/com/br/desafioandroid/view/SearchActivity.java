package desafio.com.br.desafioandroid.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;

import desafio.com.br.desafioandroid.R;
import desafio.com.br.desafioandroid.base.BaseActivity;
import desafio.com.br.desafioandroid.base.Constants;
import desafio.com.br.desafioandroid.databinding.ActivitySearchBinding;
import desafio.com.br.desafioandroid.utils.AndroidUtils;
import desafio.com.br.desafioandroid.viewmodel.SearchViewModel;

public class SearchActivity extends BaseActivity<ActivitySearchBinding, SearchViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);

        if (viewModel == null) {
            viewModel = new SearchViewModel(AndroidUtils.loadJSONFromAsset(this));
        }

        binding.setViewModel(viewModel);

        setUpToolbar(getString(R.string.search_toolbar_title), true);

        viewModel.citySubject
                .subscribe(city -> {
                    Bundle bundle = new Bundle();
                    bundle.putInt(Constants.CITY_ID_ARG, city.getId());
                    startActivity(WeatherActivity.class, bundle);
                });
    }
}
