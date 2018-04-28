package spb.android.academy.networking.domain;

import com.google.gson.annotations.SerializedName;

public class Image {
  @SerializedName("url") private String url;
  @SerializedName("width") private int width;
  @SerializedName("height") private int height;

  public String getUrl() {
    return url;
  }
}
