package spb.android.academy.networking.network;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import spb.android.academy.networking.domain.Gif;

public interface Api {
  @GET("search")
  Call<List<Gif>> getSearch(@Query("q") String query);
}
