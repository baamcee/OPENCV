package com.labis.cee.opencv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


public class HomeActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView gridview = (GridView) findViewById(R.id.gridview);

        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(HomeActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, ShowImageActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });

        // Get the message from the intent
       /* Intent intent = getIntent();
        String message = LoginHandle.getInstance().getLoggedUser().getUsername(); //intent.getStringExtra("name");
        System.out.println("messa: "+message);
        TextView l = new TextView(this);
        l.setText(message);

        l.setTextSize(56);
        setContentView(l);

        ImageView iw = new ImageView(this);

        try {
            byte[] imgbyte = LoginHandle.getInstance().getLoggedUser().getUserItems().iterator().next().getByteImage();
            String imgString = Base64.encodeToString((byte[]) imgbyte, Base64.NO_WRAP);
            System.out.println(imgString);
            byte[] encodeByte = Base64.decode(imgString, Base64.DEFAULT);
            iw.setImageBitmap(BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length));
        }catch (Exception e){
            e.printStackTrace();
        }

        setContentView(iw);*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if (id == R.id.shot){
            Intent i = new Intent(this,ImageManipulationsActivity.class);
            //i.setPackage("org.opencv.engine.BIND");
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed()
    {
       
    }


}
