package com.kabouzeid.gramophone.model;

import android.content.Context;
import android.widget.ImageView;

import com.kabouzeid.gramophone.R;
import com.kabouzeid.gramophone.util.MusicUtil;
import com.koushikdutta.ion.Ion;

/**
 * @author Karim Abou Zeid (kabouzeid)
 */
public class Album implements SearchEntry {

    public final int id;
    public int artistId;
    public final String title;
    public final String artistName;
    public final int songCount;
    public final int year;

    public Album(final int id, final String title, final String artistName, final int artistId,
                 final int songNumber, final int albumYear) {
        this.id = id;
        this.title = title;
        this.artistName = artistName;
        this.artistId = artistId;
        songCount = songNumber;
        year = albumYear;
    }

    public Album() {
        this.id = -1;
        this.title = "";
        this.artistName = "";
        songCount = -1;
        year = -1;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSubTitle() {
        return artistName;
    }

    @Override
    public void loadImage(final Context context, final ImageView imageView) {
        imageView.setImageResource(R.drawable.default_album_art);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                Ion.with(context)
                        .load(MusicUtil.getAlbumArtUri(id).toString())
                        .withBitmap()
                        .resize(imageView.getWidth(), imageView.getHeight())
                        .centerCrop()
                        .error(R.drawable.default_album_art)
                        .intoImageView(imageView);
            }
        });
    }
}
