package pl.sentia.googlerepositories.model;

import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("name")
    public String title;

    @SerializedName("description")
    public String description;

}
