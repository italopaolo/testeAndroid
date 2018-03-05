package desafio.com.br.desafioandroid.base;


import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import desafio.com.br.desafioandroid.R;
import desafio.com.br.desafioandroid.utils.AndroidUtils;
import rx.subscriptions.CompositeSubscription;

public class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    public Toolbar toolbar;
    protected T binding;
    protected V viewModel;
    private CompositeSubscription subscriptions = new CompositeSubscription();
    private SweetAlertDialog mProgressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!subscriptions.hasSubscriptions()) {
            subscriptions.add(viewModel
                    .errorSubject
                    .subscribe(this::showErrors));
        }
    }

    public void setUpToolbar(String title, boolean showBackButton) {
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            assert getSupportActionBar() != null;
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            TextView textView = toolbar.findViewById(R.id.txt_title);
            textView.setText(title);
        }
        if (showBackButton) {
            toolbar.setNavigationIcon(R.drawable.ic_back);
            toolbar.setNavigationOnClickListener(view -> {
                AndroidUtils.hideKeyboard(getApplicationContext(), view);
                finish();
            });
        }
    }

    public void showErrors(Throwable e) {
        e.printStackTrace();
        AndroidUtils.alertDialog(this, e.getMessage());
    }

    public void startActivity(Class classs) {
        Intent intent = new Intent();
        intent.setClass(this, classs);
        startActivity(intent);
    }

    public void startActivity(Class classs, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, classs);
        intent.putExtra(Constants.BUNDLE, bundle);
        startActivity(intent);
    }

    public void showProgressDialog() {
        showProgressDialog(getString(R.string.msg_loading_items));
    }

    public void showProgressDialog(String message) {
        hideProgressDialog();
        mProgressDialog = AndroidUtils.getProgressDialog(this, message);
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }


}
