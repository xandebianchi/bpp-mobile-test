package br.com.bpp.bppmobiletest.iii_utils;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;

import br.com.bpp.bppmobiletest.R;

public class MyAlertDialog {

    private Dialog dialog;

    private static final short ALPHA_ANIMATION_DURATION = 500;

    private MyAlertDialog(final Builder builder) {
        dialog = new Dialog(builder.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = LayoutInflater.from(builder.context).inflate(R.layout.view_dialog_alert_dialog, null);
        dialog.setContentView(view);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogFadeInOut;

        View viewSeparador = view.findViewById(R.id.viewSeparador);

        ScrollView scvDetalhes = view.findViewById(R.id.scvDetalhes);

        AppCompatTextView txtTitulo = view.findViewById(R.id.txtTitulo),
                          txtDescricao = view.findViewById(R.id.txtDescricao),
                          txtDetalhes = view.findViewById(R.id.txtDetalhes);

        AppCompatButton btnDetalhes = view.findViewById(R.id.btnDetalhes),
                        btnPositiveButton = view.findViewById(R.id.btnPositiveButton),
                        btnNegativeButton = view.findViewById(R.id.btnNegativeButton),
                        btnNeutralButton = view.findViewById(R.id.btnNeutralButton);

        ObjectAnimator animationDetalhesFadeIn = ObjectAnimator.ofFloat(txtDetalhes, "alpha",  0, 1f),
                       animationDetalhesFadeOut = ObjectAnimator.ofFloat(txtDetalhes, "alpha",  1f, 0);

        animationDetalhesFadeIn.setDuration(ALPHA_ANIMATION_DURATION);
        animationDetalhesFadeIn.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                txtDetalhes.setAlpha(1.0f);
                btnDetalhes.setBackgroundColor(ContextCompat.getColor(builder.context, R.color.corFundoBotaoDetalhesInvertido));
                btnDetalhes.setCompoundDrawablesWithIntrinsicBounds(builder.context.getDrawable(R.drawable.ic_seta_alert_dialog_invertida), null, null, null);
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

        animationDetalhesFadeOut.setDuration(ALPHA_ANIMATION_DURATION);
        animationDetalhesFadeOut.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {}

            @Override
            public void onAnimationEnd(Animator animator) {
                txtDetalhes.setAlpha(0.0f);
                btnDetalhes.setBackgroundColor(ContextCompat.getColor(builder.context, R.color.corFundoBotaoDetalhes));
                btnDetalhes.setCompoundDrawablesWithIntrinsicBounds(builder.context.getDrawable(R.drawable.ic_seta_alert_dialog), null, null, null);
            }

            @Override
            public void onAnimationCancel(Animator animator) {}

            @Override
            public void onAnimationRepeat(Animator animator) {}
        });

        if (builder.isDivider) {
            viewSeparador.setVisibility(View.VISIBLE);
            viewSeparador.setBackgroundColor(builder.dividerColor);
        } else {
            viewSeparador.setVisibility(View.GONE);
        }

        if (builder.titulo.isEmpty()) {
            txtTitulo.setVisibility(View.GONE);
        } else {
            txtTitulo.setVisibility(View.VISIBLE);
            txtTitulo.setText(builder.titulo);
        }

        if (builder.descricao.isEmpty() && builder.descricaoSpannable.length() == 0) {
            txtDescricao.setVisibility(View.GONE);
        } else {
            txtDescricao.setVisibility(View.VISIBLE);

            if (!builder.descricao.isEmpty()) {
                txtDescricao.setText(builder.descricao);
            } else {
                txtDescricao.setText(builder.descricaoSpannable);
            }
        }

        if (builder.detalhes.isEmpty()) {
            btnDetalhes.setVisibility(View.GONE);
            scvDetalhes.setVisibility(View.GONE);
        } else {
            txtDetalhes.setText(builder.detalhes);

            btnDetalhes.setVisibility(View.VISIBLE);
            scvDetalhes.setVisibility(View.VISIBLE);
            btnDetalhes.setOnClickListener(view1 -> {
                if (txtDetalhes.getAlpha() == 0.0f) {
                    animationDetalhesFadeIn.start();
                } else {
                    animationDetalhesFadeOut.start();
                }
            });
        }

        if (builder.positiveText.isEmpty()) {
            btnPositiveButton.setVisibility(View.GONE);
        } else {
            btnPositiveButton.setVisibility(View.VISIBLE);
            btnPositiveButton.setText(builder.positiveText);
            btnPositiveButton.setOnClickListener(v -> builder.onPositiveListener.onPositive(dialog));
        }

        if (builder.negativeText.isEmpty()) {
            btnNegativeButton.setVisibility(View.GONE);
        } else {
            btnNegativeButton.setVisibility(View.VISIBLE);
            btnNegativeButton.setText(builder.negativeText);
            btnNegativeButton.setOnClickListener(v -> builder.onNegativeListener.onNegative(dialog));
        }

        if (builder.neutralText.isEmpty()) {
            btnNeutralButton.setVisibility(View.GONE);
        } else {
            btnNeutralButton.setVisibility(View.VISIBLE);
            btnNeutralButton.setText(builder.neutralText);
            btnNeutralButton.setOnClickListener(v -> builder.onNeutralListener.onNeutral(dialog));
        }

        if (!builder.typeface.isEmpty()) {
            Typeface ttf = Typeface.createFromAsset(builder.context.getAssets(), "fonts/" + builder.typeface);
            txtTitulo.setTypeface(ttf);
            txtDescricao.setTypeface(ttf);
            txtDetalhes.setTypeface(ttf);
            btnPositiveButton.setTypeface(ttf, Typeface.BOLD);
            btnNegativeButton.setTypeface(ttf, Typeface.BOLD);
            btnNeutralButton.setTypeface(ttf, Typeface.BOLD);
        }

        dialog.setCancelable(builder.isCancelable);
        dialog.setCanceledOnTouchOutside(builder.isCancelableTouchOutside);

        dialog.show();
    }

    public static class Builder {
        // valores default
        private final Context context;
        private String titulo = "";
        private String descricao = "";
        private SpannableStringBuilder descricaoSpannable;
        private String detalhes = "";

        private boolean isCancelable = true;
        private boolean isCancelableTouchOutside = false;

        private String positiveText = "";
        private onPositiveListener onPositiveListener = myAlertDialog -> {};

        private String negativeText = "";
        private onNegativeListener onNegativeListener = myAlertDialog -> {};

        private String neutralText = "";
        private onNeutralListener onNeutralListener = myAlertDialog -> {};

        private String typeface = "";

        private boolean isDivider = false;
        private int dividerColor = 0;

        public Builder(Context context) {
            this.context = context;

            this.separador(false, 0)
                .cancelable(true, true);
        }

        public MyAlertDialog show() {
            return new MyAlertDialog(this);
        }

        /*
        Título
        */
        public Builder titulo(String mTitulo) {
            this.titulo = mTitulo;
            return this;
        }

        /*
        Descrição
        */
        public Builder descricao(String mDescricao) {
            this.descricao = mDescricao;
            return this;
        }

        /*
        Descrição
        */
        public Builder descricaoSpannable(SpannableStringBuilder mDescricaoSpannable) {
            this.descricaoSpannable = mDescricaoSpannable;
            return this;
        }

        /*
        Detalhes
        */
        public Builder detalhes(String mDetalhes) {
            this.detalhes = mDetalhes;
            return this;
        }

        /*
        Flag Cancelable
        */
        public Builder cancelable(boolean isCancelable, boolean isCancelableTouchOutside) {
            this.isCancelable = isCancelable;
            this.isCancelableTouchOutside = isCancelableTouchOutside;
            return this;
        }

        /*
        Positive button e click event handler
        */
        public Builder positiveButton(String positiveText, onPositiveListener onPositiveListener) {
            this.positiveText = positiveText;
            this.onPositiveListener = onPositiveListener;
            return this;
        }

        /*
        Negative button e click event handler
        */
        public Builder negativeButton(String negativeText, onNegativeListener onNegativeListener) {
            this.negativeText = negativeText;
            this.onNegativeListener = onNegativeListener;
            return this;
        }

        /*
        Neutral button e click event handler
        */
        public Builder neutralButton(String neutralText, onNeutralListener onNeutralListener) {
            this.neutralText = neutralText;
            this.onNeutralListener = onNeutralListener;
            return this;
        }

        /*
        Custom typeface, aplicada ao título, mensagens e botões
        -- Adicionar .ttf no diretório assets/fonts
        -- Colocar o nome do arquivo da fonte com extensão no String type
        */
        public Builder typeface(String typeface) {
            this.typeface = typeface;
            return this;
        }

        /*
       Separador entre a barra e o conteúdo
       -- Height (layout): 4dp
       */
        Builder separador(boolean isDivider, int dividerColor) {
            this.isDivider = isDivider;
            this.dividerColor = dividerColor;
            return this;
        }
    }

    public interface onPositiveListener {
        void onPositive(Dialog myAlertDialog);
    }

    public interface onNegativeListener {
        void onNegative(Dialog myAlertDialog);
    }

    public interface onNeutralListener {
        void onNeutral(Dialog myAlertDialog);
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
