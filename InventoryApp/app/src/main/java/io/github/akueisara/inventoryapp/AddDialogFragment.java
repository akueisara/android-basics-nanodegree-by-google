package io.github.akueisara.inventoryapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.github.akueisara.inventoryapp.data.ProductContract.ProductEntry;

public class AddDialogFragment extends DialogFragment {

    String mImageURI;

    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View addView = inflater.inflate(R.layout.dialog_add, null);

        Button selectImageButton = (Button) addView.findViewById(R.id.btn_image);
        // set up click listener for the button of "Select Image"
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermissionREAD_EXTERNAL_STORAGE(getActivity())) {
                    startActivityForResult(new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                            PermissionUtils.MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
                }
            }
        });

        final Dialog addDialog = builder.setView(addView)
                .setPositiveButton(R.string.add_product, null)
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddDialogFragment.this.getDialog().cancel();
                    }
                })
                .create();

        // set on the listener for the positive button of the dialog
        addDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {

                Button positiveButton = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);

                positiveButton.setOnClickListener(new View.OnClickListener() {

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
                            addDialog.dismiss();
                    }
                });
            }
        });

        return addDialog;
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

        if (requestCode == PermissionUtils.MY_PERMISSIONS_READ_EXTERNAL_STORAGE && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            mImageURI = selectedImage.toString();
        }
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(final Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {

                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                        PermissionUtils.showPermissionDialog(context.getString(R.string.external_storage), context, Manifest.permission.READ_EXTERNAL_STORAGE);

                    } else {

                        // No explanation needed, we can request the permission.
                        requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PermissionUtils.MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
                    }
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PermissionUtils.MY_PERMISSIONS_READ_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    startActivityForResult(new Intent(Intent.ACTION_PICK,
                                    android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
                            PermissionUtils.MY_PERMISSIONS_READ_EXTERNAL_STORAGE);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(getActivity(), getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
                }
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
