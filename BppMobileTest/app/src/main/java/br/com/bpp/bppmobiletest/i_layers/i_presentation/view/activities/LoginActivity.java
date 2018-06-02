package br.com.bpp.bppmobiletest.i_layers.i_presentation.view.activities;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.rilixtech.materialfancybutton.MaterialFancyButton;

import javax.inject.Inject;

import br.com.bpp.bppmobiletest.R;
import br.com.bpp.bppmobiletest.i_layers.i_presentation.viewmodel.LoginViewModel;
import br.com.bpp.bppmobiletest.iii_utils.MyAlertDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.AndroidInjection;

public class LoginActivity extends AppCompatActivity {

    // ===============
    // MVVM/AAC FIELDS
    // ===============

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private LoginViewModel viewModel;

    // ===================
    // PRESENTATION FIELDS
    // ===================

    @BindView(R.id.txtEmail)
    TextInputEditText txtEmail;

    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;

    @BindView(R.id.txtSenha)
    TextInputEditText txtSenha;

    @BindView(R.id.tilSenha)
    TextInputLayout tilSenha;

    @BindView(R.id.btnLogar)
    MaterialFancyButton btnLogar;

    @BindView(R.id.rtlLogin)
    RelativeLayout rtlLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        // =================
        // PRESENTATION CODE
        // =================

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity_login);
        ButterKnife.bind(this);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.corLaranjaPrincipal));

        rtlLogin.requestFocus();

        // =============
        // MVVM/AAC CODE
        // =============

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel.class);
        viewModel.getLogin().observe(this, login -> {
            if (login != null) {
                if (login.getStatus().equals(getString(R.string.strSuccess))) {
                   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                   startActivity(intent);
                } else {
                    viewModel.deleteLogin();

                    new MyAlertDialog.Builder(LoginActivity.this)
                            .titulo(getString(R.string.strErro))
                            .cancelable(false, false)
                            .positiveButton(getString(R.string.str_ok), Dialog::dismiss)
                            .descricao(login.getMessage())
                            .show();
                }
            }
        });
    }

    @OnClick(R.id.btnLogar)
    public void onViewClicked() {
        viewModel.onButtonLoginClick(txtEmail.getText().toString(), txtSenha.getText().toString());
    }
}