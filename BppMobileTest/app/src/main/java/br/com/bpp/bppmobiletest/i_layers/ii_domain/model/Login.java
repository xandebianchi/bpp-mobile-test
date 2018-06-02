package br.com.bpp.bppmobiletest.i_layers.ii_domain.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Login {

    @PrimaryKey
    private int localPrivateId;

    @SerializedName("email")
    @Expose
    private String email;

    // LOGIN ANSWERS
    private String status,
                   message;
    private int code;

    public Login(int localPrivateId, String email, String status, String message, int code) {
        this.localPrivateId = localPrivateId;
        this.email = email;
        this.status = status;
        this.message = message;
        this.code = code;
    }

    // GETTERS

    public int getLocalPrivateId() {
        return localPrivateId;
    }

    public String getEmail() {
        return email;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    // SETTERS

    public void setLocalPrivateId(int localPrivateId) {
        this.localPrivateId = localPrivateId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(int code) {
        this.code = code;
    }
}