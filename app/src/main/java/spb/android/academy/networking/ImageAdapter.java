package spb.android.academy.networking;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import spb.android.academy.networking.domain.Gif;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

  private List<Gif> items = new ArrayList<>();

  @NonNull @Override public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    return new ImageViewHolder(inflater.inflate(R.layout.item_image, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
    final Gif hit = items.get(position);
    Picasso.get()
        .load(hit.getImagesStruct().getPreview().getUrl())
        .fit()
        .centerCrop()
        .placeholder(R.drawable.loading)
        .into(holder.imageView);
  }

  public void replaceWith(List<Gif> items) {
    this.items = items;
    notifyDataSetChanged();
  }

  @Override public int getItemCount() {
    return items.size();
  }

  class ImageViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    ImageViewHolder(View itemView) {
      super(itemView);
      imageView = itemView.findViewById(R.id.image);
    }
  }
}
