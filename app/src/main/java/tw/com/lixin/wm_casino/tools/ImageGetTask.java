package tw.com.lixin.wm_casino.tools;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import tw.com.lixin.wm_casino.LobbyActivity;
import tw.com.lixin.wm_casino.models.Table;

public class ImageGetTask extends AsyncTask<Void, Void, Boolean> {
    List<Table> tables;
     LobbyActivity activity;

    public ImageGetTask(LobbyActivity activity, List<Table> tables) {
        this.tables =  tables;
        this.activity = activity;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        for(Table table: tables){
            try {
                InputStream in = new java.net.URL(table.dealerImageUrl).openStream();
                table.dealerImage = BitmapFactory.decodeStream(in);
            }catch(IOException e) {
                Log.e(table.dealerName + " BitError", e.getMessage());
            }
        }

        return true;
    }

    protected void onPostExecute(Boolean result) {
        activity.dealerImgLoaded();
        activity = null;
    }
}
