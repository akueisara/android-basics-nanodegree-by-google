package io.github.akueisara.inventoryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import io.github.akueisara.inventoryapp.data.ProductContract.ProductEntry;

import static android.R.attr.bitmap;

/**
 * Created by akueisara on 10/18/2016.
 */

public class EditActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, ModifyQuantityDialogFragment.QuantityListener {
    private static final int EXISTING_PRODUCT_LOADER = 0;
    public static final int REQUEST_CODE = 0;

    private Uri mCurrentPetUri;

    private EditText mNameEditText;
    private TextView mQuantityTextView;
    private EditText mPriceEditText;
    private ImageView mImageView;
    private Uri mImageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent = getIntent();
        mCurrentPetUri = intent.getData();
        setTitle(getString(R.string.edit_activity_title));
        getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this);

        mNameEditText = (EditText) findViewById(R.id.edit_text_name);
        mQuantityTextView = (TextView) findViewById(R.id.text_view_quantity);
        mPriceEditText = (EditText) findViewById(R.id.edit_text_price);
        mImageView = (ImageView) findViewById(R.id.image);

        // set click listener to select an image
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), REQUEST_CODE);
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductEntry.COLUMN_PRODUCT_PRICE,
                ProductEntry.COLUMN_PRODUCT_IMAGE};

        return new CursorLoader(this,
                mCurrentPetUri,
                projection,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }
        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
            int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex( ProductEntry.COLUMN_PRODUCT_PRICE);
            int imageColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_IMAGE);

            String name = cursor.getString(nameColumnIndex);
            Integer quantity = cursor.getInt(quantityColumnIndex);
            Float price = cursor.getFloat(priceColumnIndex);
            String imageURI = cursor.getString(imageColumnIndex);

            mNameEditText.setText(name);
            mQuantityTextView.setText(Integer.toString(quantity));
            mPriceEditText.setText(Float.toString(price));
            if(imageURI!=null) {
                mImageView.setImageURI(Uri.parse(imageURI));
            }
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mNameEditText.setText("");
        mQuantityTextView.setText(Integer.toString(0));
        mPriceEditText.setText(Float.toString(0));
        mImageView.setImageDrawable(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(EditActivity.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // get result data from selecting an image
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            mImageURI = Uri.parse(selectedImage.toString());
            mImageView.setImageURI(selectedImage);
        }
    }

    //  for the button of Modify Quantity
    public void onModifyQuantity(View view) {
        Integer quantity = Integer.parseInt(mQuantityTextView.getText().toString().trim());
        ModifyQuantityDialogFragment newFragment = ModifyQuantityDialogFragment.newInstance(quantity);
        newFragment.show(getFragmentManager(), "dialog");
    }

    @Override
    public void onFinishModifyQuantityDialog(String quantity) {
        mQuantityTextView.setText(quantity);
    }

    // for the button of Order
    public void onOrder(View view) {
        String name = mNameEditText.getText().toString().trim();
        String message = createOrderSummary(name);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject) + " " + name);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public String createOrderSummary(String name) {
        String message = "";
        message += getString(R.string.hi) + "\n";
        message += getString(R.string.message) + "\n";
        message += getString(R.string.product_name_title) + " " + name + "\n";
        message += getString(R.string.thank);
        return message;
    }

    // for the button of Save
    public void onSave(View view) {
        saveProduct();
        finish();
    }

    private void saveProduct() {
        String name = mNameEditText.getText().toString().trim();
        Integer quantity = Integer.parseInt(mQuantityTextView.getText().toString().trim());
        Float price = 0.0f;
        if(!"".equals(mPriceEditText.getText().toString().trim()))
            price = Float.parseFloat(mPriceEditText.getText().toString().trim());

        ContentValues values = new ContentValues();
        // name
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, name);

        // image
        Bitmap icLanucher = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        Bitmap bitmap = ((BitmapDrawable)mImageView.getDrawable()).getBitmap();
        if(!equals(icLanucher,bitmap) && mImageURI != null) {
            values.put(ProductEntry.COLUMN_PRODUCT_IMAGE, mImageURI.toString());
        }
        // quantity
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, quantity);
        // price
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, price);

        int rowsAffected = getContentResolver().update(mCurrentPetUri, values, null, null);

        if (rowsAffected == 0) {
            Toast.makeText(this, getString(R.string.update_product_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.update_product_successful), Toast.LENGTH_SHORT).show();
        }
    }

    // for the button of Delete
    public void onDelete(View view) {
        showDeleteConfirmationDialog();
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.delete_dialog_title));
        builder.setPositiveButton(getString(R.string.btn_delete), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteProduct();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteProduct() {
        int rowsDeleted = getContentResolver().delete(mCurrentPetUri, null, null);
        if (rowsDeleted == 0) {
            Toast.makeText(this, getString(R.string.delete_product_failed), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.delete_product_successful), Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    // the method checks if two bitmaps are the same
    public boolean equals(Bitmap bitmap1, Bitmap bitmap2) {
        ByteBuffer buffer1 = ByteBuffer.allocate(bitmap1.getHeight() * bitmap1.getRowBytes());
        bitmap1.copyPixelsToBuffer(buffer1);

        ByteBuffer buffer2 = ByteBuffer.allocate(bitmap2.getHeight() * bitmap2.getRowBytes());
        bitmap2.copyPixelsToBuffer(buffer2);

        return Arrays.equals(buffer1.array(), buffer2.array());
    }

}
