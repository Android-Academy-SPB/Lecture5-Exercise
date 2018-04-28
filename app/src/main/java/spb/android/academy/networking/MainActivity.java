package spb.android.academy.networking;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import spb.android.academy.networking.domain.Gif;

public class MainActivity extends AppCompatActivity {

  private ImageAdapter adapter;
  private RecyclerView recyclerView;
  private TextView statusText;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initRecyclerView();

    statusText = findViewById(R.id.statusText);
    final EditText searchQueryEdit = findViewById(R.id.searchQueryEdit);
    final Button submitBtn = findViewById(R.id.submitBtn);
    submitBtn.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        searchImages(searchQueryEdit.getText().toString());
        hideKeyboard();
      }
    });
  }

  private void searchImages(String query) {
    showMessage(getString(R.string.searching));

    getApp().getApi().getSearch(query).enqueue(new Callback<List<Gif>>() {
      @Override public void onResponse(Call<List<Gif>> call, Response<List<Gif>> response) {
        // Check if call went through and update the UI accordingly.
        if (response.code() == 200) {
          final List<Gif> gifs = response.body();
          showContent(gifs);
        } else {
          showMessage(getString(R.string.error_with_code, response.code()));
        }
      }

      @Override public void onFailure(Call<List<Gif>> call, Throwable t) {
        // HTTP call failed, show something to the user.
        showMessage(getString(R.string.something_went_wrong));
      }
    });
  }

  private void initRecyclerView() {
    recyclerView = findViewById(R.id.recyclerView);
    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
    recyclerView.setLayoutManager(layoutManager);
    adapter = new ImageAdapter();
    recyclerView.setAdapter(adapter);
  }

  private void showMessage(String message) {
    statusText.setText(message);
    recyclerView.setVisibility(View.INVISIBLE);
    statusText.setVisibility(View.VISIBLE);
  }

  private void showContent(List<Gif> gifs) {
    adapter.replaceWith(gifs);
    recyclerView.setVisibility(View.VISIBLE);
    statusText.setVisibility(View.INVISIBLE);
  }


  private void hideKeyboard() {
    View view = this.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }

  private App getApp() {
    return ((App) getApplication());
  }
}
