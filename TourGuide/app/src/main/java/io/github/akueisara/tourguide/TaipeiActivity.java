package io.github.akueisara.tourguide;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by akueisara on 9/29/2016.
 */
public class TaipeiActivity  extends AppCompatActivity {
    private ArrayList<Attraction> attractions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attraction_list);

        // Create a list of attractions
        attractions = new ArrayList<Attraction>();
        Location elephantMountain = new Location(getResources().getString(R.string.taipei_attraction_name_1));
        elephantMountain.setLatitude(25.0262737);
        elephantMountain.setLongitude(121.5694812);
        attractions.add(new Attraction(getResources().getString(R.string.taipei_attraction_name_1), getResources().getString(R.string.taipei_attraction_phone_1), getResources().getString(R.string.taipei_attraction_address_1), getResources().getString(R.string.taipei_attraction_imageurl_1), elephantMountain));

        Location longshanTemple = new Location(getResources().getString(R.string.taipei_attraction_name_2));
        longshanTemple.setLatitude(25.03699);
        longshanTemple.setLongitude(121.49993);
        attractions.add(new Attraction(getResources().getString(R.string.taipei_attraction_name_2), getResources().getString(R.string.taipei_attraction_phone_2), getResources().getString(R.string.taipei_attraction_address_2), getResources().getString(R.string.taipei_attraction_imageurl_2), longshanTemple));

        Location maokongGondola = new Location(getResources().getString(R.string.taipei_attraction_name_3));
        maokongGondola.setLatitude(24.96894);
        maokongGondola.setLongitude(121.58825);
        attractions.add(new Attraction(getResources().getString(R.string.taipei_attraction_name_3), getResources().getString(R.string.taipei_attraction_phone_3), getResources().getString(R.string.taipei_attraction_address_3), getResources().getString(R.string.taipei_attraction_imageurl_3), maokongGondola));

        Location chiangKaiShekMemorialHall = new Location(getResources().getString(R.string.taipei_attraction_name_4));
        chiangKaiShekMemorialHall.setLatitude(25.03568);
        chiangKaiShekMemorialHall.setLongitude(121.51967);
        attractions.add(new Attraction(getResources().getString(R.string.taipei_attraction_name_4), getResources().getString(R.string.taipei_attraction_phone_4), getResources().getString(R.string.taipei_attraction_address_4), getResources().getString(R.string.taipei_attraction_imageurl_4), chiangKaiShekMemorialHall));

        Location nationalPalaceMuseum = new Location(getResources().getString(R.string.taipei_attraction_name_5));
        nationalPalaceMuseum.setLatitude(25.10236);
        nationalPalaceMuseum.setLongitude(121.54849);
        attractions.add(new Attraction(getResources().getString(R.string.taipei_attraction_name_5),
                getResources().getString(R.string.taipei_attraction_phone_5), getResources().getString(R.string.taipei_attraction_address_5),
                getResources().getString(R.string.taipei_attraction_imageurl_5), nationalPalaceMuseum));

        AttractionAdapter adapter = new AttractionAdapter(this, attractions);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                double lat = attractions.get(position).getLocation().getLatitude();
                double lon = attractions.get(position).getLocation().getLongitude();
                String keyword = attractions.get(position).getAttractionName();
                Uri uri = Uri.parse("geo:" + lat + "," + lon + "?q=" + Uri.encode(keyword));

                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
            }
        });
    }
}
