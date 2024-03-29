package com.labis.cee.opencv;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.labis.cee.opencv.login.LoginHandle;
import com.labis.cee.opencv.login.StatefulRestTemplate;
import com.labis.cee.opencv.model.Item;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.net.URI;


public class ShowImageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        ImageView imageView = (ImageView) findViewById(R.id.item_img);

        Intent intent = getIntent();
        final int position =intent.getIntExtra("position",0);

        Item items[] = new Item[ LoginHandle.getInstance().getLoggedUser().getUserItems().size()];
        LoginHandle.getInstance().getLoggedUser().getUserItems().toArray(items);

        long id = 0;

        try {
            byte[] imgbyte = items[position].getByteImage();
            id = items[position].getItemId();
            String imgString = Base64.encodeToString((byte[]) imgbyte, Base64.NO_WRAP);
            byte[] encodeByte = Base64.decode(imgString, Base64.DEFAULT);
            imageView.setImageBitmap(BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length));
        }catch (Exception e){
            e.printStackTrace();
        }

        final long finalId = id;
        findViewById(R.id.delete_btn).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new HttpRequestTask().execute(finalId);
                    }
                }
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class HttpRequestTask extends AsyncTask<Long, Void, Item> {

        StatefulRestTemplate statefulRestTemplate = LoginHandle.getInstance().getStatefulRestTemplate();

        @Override
        protected Item doInBackground(Long... params) {
            try {
                long id = params[0];
                System.out.println("id: "+id);
                HttpEntity<Item> result = statefulRestTemplate.exchangeForOur(
                        URI.create(LoginHandle.BASE_URL + "/picture/" + id + "/delete"),
                        HttpMethod.GET, Item.class);
                LoginHandle.getInstance().getLoggedUser().getUserItems().remove(
                        Item.findById(LoginHandle.getInstance().getLoggedUser().getUserItems(), id));

            } catch (Exception e) {
                Log.e("ShowImageActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Item item) {
            Intent intent = new Intent(ShowImageActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }
}
