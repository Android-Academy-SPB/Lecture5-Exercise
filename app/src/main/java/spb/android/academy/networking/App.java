package spb.android.academy.networking;

import android.app.Application;
import spb.android.academy.networking.network.Api;
import spb.android.academy.networking.network.NetworkModule;

public class App extends Application {

  private Api api;

  @Override public void onCreate() {
    super.onCreate();

    api = new NetworkModule(this).getApi();
  }

  public Api getApi() {
    return api;
  }
}
