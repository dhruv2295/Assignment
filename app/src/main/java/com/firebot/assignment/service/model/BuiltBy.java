
package com.firebot.assignment.service.model;


import com.google.gson.annotations.SerializedName;

public class BuiltBy {

    @SerializedName("avatar")
    private String mAvatar;
    @SerializedName("href")
    private String mHref;
    @SerializedName("username")
    private String mUsername;

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public String getHref() {
        return mHref;
    }

    public void setHref(String href) {
        mHref = href;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

}
