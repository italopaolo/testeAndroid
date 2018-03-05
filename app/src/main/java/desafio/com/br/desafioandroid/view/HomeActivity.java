package desafio.com.br.desafioandroid.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import desafio.com.br.desafioandroid.R;
import desafio.com.br.desafioandroid.base.BaseActivity;
import desafio.com.br.desafioandroid.base.Constants;
import desafio.com.br.desafioandroid.databinding.ActivityHomeBinding;
import desafio.com.br.desafioandroid.viewmodel.HomeViewModel;

public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        if (viewModel == null) {
            viewModel = new HomeViewModel();
        }

        binding.setViewModel(viewModel);

        setUpToolbar(getString(R.string.home_toolbar_title), false);

        binding.fab.setOnClickListener(view -> startActivity(SearchActivity.class));

        viewModel.favoriteSubject.subscribe(cityId -> {
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.CITY_ID_ARG, cityId);
            startActivity(WeatherActivity.class, bundle);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.init();
    }
}
