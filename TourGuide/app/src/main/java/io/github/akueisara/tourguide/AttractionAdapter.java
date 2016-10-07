package io.github.akueisara.tourguide;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by akueisara on 9/29/2016.
 */
public class AttractionAdapter extends ArrayAdapter<Attraction> {

    public AttractionAdapter(Context context, ArrayList<Attraction> attractions) {
        super(context, 0, attractions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Attraction currentAttraction = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name);
        nameTextView.setText(currentAttraction.getAttractionName());

        TextView phoneTextView = (TextView) listItemView.findViewById(R.id.phone);
        if("".equals(currentAttraction.getAttractionPhone()))
            phoneTextView.setVisibility(View.GONE);
        else {
            phoneTextView.setVisibility(View.VISIBLE);
            phoneTextView.setText(currentAttraction.getAttractionPhone());
        }

        TextView addressTextView = (TextView) listItemView.findViewById(R.id.address);
        addressTextView.setText(currentAttraction.getAttractionAddress());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image_view);
        // Improve picture loading..
        Picasso.with(getContext())
                .load(currentAttraction.getImageResourceUrl())
                .resizeDimen(R.dimen.list_item_image_width,R.dimen.list_item_image_height)
                .centerInside()
                .into(imageView);

        return listItemView;
    }
}
