package tw.com.lixin.wm_casino.tools;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.util.SparseArray;

import java.io.IOException;
import java.io.InputStream;

import tw.com.lixin.wm_casino.models.Table;

public class ImageGetTask extends AsyncTask<Void, Void, Boolean> {
    private SparseArray<Table> tables;


    public ImageGetTask(SparseArray<Table> tables) {
        this.tables =  tables;

    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        for(int i = 0; i < tables.size(); i++) {
            Table table = tables.valueAt(i);
            try {
                InputStream in = new java.net.URL("bad").openStream();
                table.dealerImage = BitmapFactory.decodeStream(in);
            }catch(IOException e) {
                Log.e(table.dealerName + " BitError", e.getMessage());
            }
        }

        return true;
    }

    protected void onPostExecute(Boolean result) {

    }
}
