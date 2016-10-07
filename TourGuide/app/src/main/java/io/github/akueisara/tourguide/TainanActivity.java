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
 * Created by akueisara on 10/4/2016.
 */
public class TainanActivity extends AppCompatActivity {
    private ArrayList<Attraction> attractions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attraction_list);

        // Create a list of attractions
        attractions = new ArrayList<Attraction>();
        Location chimeiMuseum = new Location(getResources().getString(R.string.tainan_attraction_name_1));
        chimeiMuseum.setLatitude(22.93648);
        chimeiMuseum.setLongitude(120.22779);
        attractions.add(new Attraction(getResources().getString(R.string.tainan_attraction_name_1),
                getResources().getString(R.string.tainan_attraction_phone_1), getResources().getString(R.string.tainan_attraction_address_1),
                getResources().getString(R.string.tainan_attraction_imageurl_1), chimeiMuseum));

        Location anpingTreeHouse = new Location(getResources().getString(R.string.tainan_attraction_name_2));
        anpingTreeHouse.setLatitude(23.00341);
        anpingTreeHouse.setLongitude(120.15949);
        attractions.add(new Attraction(getResources().getString(R.string.tainan_attraction_name_2),
                getResources().getString(R.string.tainan_attraction_phone_2), getResources().getString(R.string.tainan_attraction_address_2),
                getResources().getString(R.string.tainan_attraction_imageurl_2), anpingTreeHouse));

        Location chihkanTower = new Location(getResources().getString(R.string.tainan_attraction_name_3));
        chihkanTower.setLatitude(22.99879);
        chihkanTower.setLongitude(120.20269);
        attractions.add(new Attraction(getResources().getString(R.string.tainan_attraction_name_3),
                getResources().getString(R.string.tainan_attraction_phone_3), getResources().getString(R.string.tainan_attraction_address_3),
                getResources().getString(R.string.tainan_attraction_imageurl_3), chihkanTower));

        Location tenDrumCultureVillage = new Location(getResources().getString(R.string.tainan_attraction_name_4));
        tenDrumCultureVillage.setLatitude(22.93861);
        tenDrumCultureVillage.setLongitude(120.22908);
        attractions.add(new Attraction(getResources().getString(R.string.tainan_attraction_name_4),
                getResources().getString(R.string.tainan_attraction_phone_4), getResources().getString(R.string.tainan_attraction_address_4),
                getResources().getString(R.string.tainan_attraction_imageurl_4), tenDrumCultureVillage));

        Location confuciusTemple = new Location(getResources().getString(R.string.tainan_attraction_name_5));
        confuciusTemple.setLatitude(22.99311);
        confuciusTemple.setLongitude(120.20496);
        attractions.add(new Attraction(getResources().getString(R.string.tainan_attraction_name_5),
                getResources().getString(R.string.tainan_attraction_phone_5), getResources().getString(R.string.tainan_attraction_address_5),
                getResources().getString(R.string.tainan_attraction_imageurl_5), confuciusTemple));

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
