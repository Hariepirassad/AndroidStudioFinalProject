package fr.esilv.s8.project.viewholders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import fr.esilv.s8.project.R;
import fr.esilv.s8.project.interfaces.OnInfosSelectedListener;
import fr.esilv.s8.project.models.Infos;

/**
 * Created by Harry on 20/03/2017.
 */

public class ItemsViewHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView description;
    private TextView publishedAt;
    private TextView channelTitle;
    private ImageView thumbnails;

    private OnInfosSelectedListener onInfosSelectedListener;

    public ItemsViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        thumbnails = (ImageView) itemView.findViewById(R.id.thumbnails);
        publishedAt = (TextView) itemView.findViewById(R.id.publicationDate);
        channelTitle = (TextView) itemView.findViewById(R.id.channelTitle);
    }

    public void bind(final Infos infos) {
        title.setText(infos.getSnippet().getTitle());
        description.setText(infos.getSnippet().getDescription());
        Picasso.with(itemView.getContext()).load(infos.getSnippet().getThumbnails().getMedium().getUrl()).into(thumbnails);
        publishedAt.setText(infos.getSnippet().getPublishedAt().toString());
        channelTitle.setText(infos.getSnippet().getChannelTitle());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onInfosSelectedListener == null) {
                    return;
                }
                onInfosSelectedListener.onInfosSelected(infos);
            }
        });
    }

    public void setOnInfosSelectedListener(OnInfosSelectedListener onInfosSelectedListener) {
        this.onInfosSelectedListener = onInfosSelectedListener;
    }
}
