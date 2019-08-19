package tw.com.lixin.wm_casino.holders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

import tw.com.atromoby.widgets.ItemHolder;
import tw.com.lixin.wm_casino.R;
import tw.com.lixin.wm_casino.models.BacTable;
import tw.com.lixin.wm_casino.tools.CasinoGrid;

public class BacHolder extends ItemHolder {

    private BacTable table;

    public BacHolder(BacTable table) {
        super(R.layout.bac_vertical_item);
        this.table = table;
    }

    @Override
    public void onBind() {
        CasinoGrid grid = findViewById(R.id.road_grid);
        ConstraintLayout block = findViewById(R.id.road_grid_block);
        ImageView dealerImg = findViewById(R.id.dealer_img);
        TextView dealerName = findViewById(R.id.dealername_txt);
        TextView winRate = findViewById(R.id.win_rate_txt);
        TextView countDown = findViewById(R.id.countdown_txt);
        TextView dealing = findViewById(R.id.dealing_txt);
        TextView tableName = findViewById(R.id.table_name_txt);

        tableName.setText("Barcarrat" + table.groupID);
        new DownloadImageTask(dealerImg).execute(table.dealerName);
        grid.post(() -> {
            double dim = grid.getMeasuredHeight() / 6.0;
            int wGrid = (int) Math.round(grid.getMeasuredWidth()/dim);
            grid.setGrid(wGrid, 6);
            grid.drawRoad(table.firstGrid);
        });

    }

    @Override
    public void onRecycle() {

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
