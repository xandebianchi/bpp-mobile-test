package br.com.bpp.bppmobiletest.i_layers.ii_domain.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Invoice {

    @NonNull
    @PrimaryKey
    @SerializedName("transactionId")
    @Expose
    private String transactionId;

    @SerializedName("transactionDate")
    @Expose
    private String transactionDate;

    @SerializedName("transactionFormattedDate")
    @Expose
    private String transactionFormattedDate;

    @SerializedName("transactionCurrency")
    @Expose
    private String transactionCurrency;

    @SerializedName("transactionAmount")
    @Expose
    private Float transactionAmount;

    @SerializedName("billingCurrency")
    @Expose
    private String billingCurrency;

    @SerializedName("billingAmount")
    @Expose
    private Float billingAmount;

    @SerializedName("transactionStatus")
    @Expose
    private String transactionStatus;

    @SerializedName("transactionName")
    @Expose
    private String transactionName;

    @SerializedName("merchantName")
    @Expose
    private String merchantName;

    @SerializedName("mccCode")
    @Expose
    private String mccCode;

    @SerializedName("mccDescription")
    @Expose
    private String mccDescription;

    public Invoice(@NonNull String transactionId, String transactionDate,
                   String transactionFormattedDate, String transactionCurrency,
                   Float transactionAmount, String billingCurrency, Float billingAmount,
                   String transactionStatus, String transactionName, String merchantName,
                   String mccCode, String mccDescription) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.transactionFormattedDate = transactionFormattedDate;
        this.transactionCurrency = transactionCurrency;
        this.transactionAmount = transactionAmount;
        this.billingCurrency = billingCurrency;
        this.billingAmount = billingAmount;
        this.transactionStatus = transactionStatus;
        this.transactionName = transactionName;
        this.merchantName = merchantName;
        this.mccCode = mccCode;
        this.mccDescription = mccDescription;
    }

    // GETTERS

    @NonNull
    public String getTransactionId() {
        return transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionFormattedDate() {
        return transactionFormattedDate;
    }

    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    public Float getTransactionAmount() {
        return transactionAmount;
    }

    public String getBillingCurrency() {
        return billingCurrency;
    }

    public Float getBillingAmount() {
        return billingAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getMccCode() {
        return mccCode;
    }

    public String getMccDescription() {
        return mccDescription;
    }

    // SETTERS

    public void setTransactionId(@NonNull String transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionFormattedDate(String transactionFormattedDate) {
        this.transactionFormattedDate = transactionFormattedDate;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    public void setTransactionAmount(Float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setBillingCurrency(String billingCurrency) {
        this.billingCurrency = billingCurrency;
    }

    public void setBillingAmount(Float billingAmount) {
        this.billingAmount = billingAmount;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setMccCode(String mccCode) {
        this.mccCode = mccCode;
    }

    public void setMccDescription(String mccDescription) {
        this.mccDescription = mccDescription;
    }
}