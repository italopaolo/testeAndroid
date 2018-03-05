package desafio.com.br.desafioandroid.view;


import android.databinding.DataBindingUtil;
import android.databinding.generated.callback.OnClickListener;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

import desafio.com.br.desafioandroid.R;
import desafio.com.br.desafioandroid.base.BaseActivity;
import desafio.com.br.desafioandroid.base.Constants;
import desafio.com.br.desafioandroid.databinding.ActivityWeatherBinding;
import desafio.com.br.desafioandroid.viewmodel.WeatherViewModel;
import rx.subscriptions.CompositeSubscription;

public class WeatherActivity extends BaseActivity<ActivityWeatherBinding, WeatherViewModel> {

    private CompositeSubscription subscriptions = new CompositeSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);

        Bundle bundle = getIntent().getBundleExtra(Constants.BUNDLE);

        if (viewModel == null) {
            assert bundle != null;
            viewModel = new WeatherViewModel(bundle.getInt(Constants.CITY_ID_ARG));
        }

        binding.setViewModel(viewModel);

        setUpToolbar(getString(R.string.detail_toolbar_title), true);

        binding.fab.setOnClickListener(view -> {
            viewModel.saveFavorite();
            Snackbar.make(view, "Favorito salvo", Snackbar.LENGTH_SHORT).show();
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!subscriptions.hasSubscriptions()) {
            subscriptions.add(viewModel.callError.subscribe(aVoid -> finish()));

            subscriptions.add(viewModel.showProgressDialog.subscribe(aBoolean -> {
                if (aBoolean) showProgressDialog();
                else hideProgressDialog();
            }));
        }
    }
}
