package com.infm.readit.readable;

import android.content.Context;

import com.infm.readit.R;

/**
 * Created by infm on 6/13/14. Enjoy ;)
 */
public class TestSettingsText extends com.infm.readit.readable.Readable {
    public TestSettingsText(Context context) {
        setText(context.getResources().getString(R.string.sample_text));
        setTextType("text/plain");
        setPath(null);
    }

    @Override
    public String getLink() {
        return null;
    }

    @Override
    public void setLink(String link) {

    }

    @Override
    public ChunkData getChunkData() {
        return null;
    }

    @Override
    public void setChunkData(ChunkData data) {

    }
}
