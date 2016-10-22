package io.github.akueisara.inventoryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.github.akueisara.inventoryapp.data.ProductContract.ProductEntry;

/**
 * Created by akueisara on 10/19/2016.
 */

public class AddDialogFragment extends DialogFragment {

    public static final int REQUEST_CODE = 0;
    String mImageURI;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View addView = inflater.inflate(R.layout.dialog_add, null);

        Button selectImageButton = (Button) addView.findViewById(R.id.btn_image);
        // set up click listener for the button of "Select Image"
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), REQUEST_CODE);
            }
        });

        final Dialog d = builder.setView(addView)
                .setPositiveButton(R.string.add_product, null)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddDialogFragment.this.getDialog().cancel();
                    }
                })
                .create();

        // set on the listener for the positive button of the dialog
        d.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                Button b = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);

                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        // add wantToCloseDialog to prevent the dialog from closing when the information is not completely filled out
                        Boolean wantToCloseDialog = false;

                        EditText editTextName = (EditText) addView.findViewById(R.id.name);
                        EditText editTextQuantity = (EditText) addView.findViewById(R.id.quantity);
                        EditText editTextPrice = (EditText) addView.findViewById(R.id.price);

                        String name = editTextName.getText().toString().trim();
                        String quantityString = editTextQuantity.getText().toString().trim();
                        String priceString = editTextPrice.getText().toString().trim();

                        // validate all the required infomation
                        if (TextUtils.isEmpty(name)  || TextUtils.isEmpty(quantityString) || TextUtils.isEmpty(priceString)) {
                            Toast.makeText(getActivity(), getString(R.string.product_info_not_empty), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Integer quantity = Integer.parseInt(editTextQuantity.getText().toString().trim());
                            Float price = Float.parseFloat(editTextPrice.getText().toString().trim());
                            insertProduct(name, quantity, price, mImageURI);
                            wantToCloseDialog = true;
                        }

                        // after successfully inserting product, dismiss the dialog
                        if(wantToCloseDialog)
                            d.dismiss();
                    }
                });
            }
        });

        return d;
    }

    private void insertProduct(String name, Integer quantity, Float price, String imagePath) {
        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, name);
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, price);
        if (!"".equals(imagePath))
            values.put(ProductEntry.COLUMN_PRODUCT_IMAGE, imagePath);
        getActivity().getContentResolver().insert(ProductEntry.CONTENT_URI, values);
    }

    // get result data from selecting an image
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            mImageURI = selectedImage.toString();
        }
    }

}
