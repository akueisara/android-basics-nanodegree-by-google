package io.github.akueisara.inventoryapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ModifyQuantityDialogFragment extends DialogFragment {
    int mQuantity;

    public static interface QuantityListener {
        void onFinishModifyQuantityDialog(String quantity);
    }

    private QuantityListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (QuantityListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement QuantityListener");
        }
    }

    static ModifyQuantityDialogFragment newInstance(int quantity) {
        ModifyQuantityDialogFragment f = new ModifyQuantityDialogFragment();
        Bundle args = new Bundle();
        args.putInt("quantity", quantity);
        f.setArguments(args);
        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mQuantity = getArguments().getInt("quantity");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_modify_quantity, null);
        final EditText QuantityEditText = (EditText) view.findViewById(R.id.edit_text_quantity);
        QuantityEditText.setText(Integer.toString(mQuantity));
        builder.setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String quantity = getString(R.string.default_product_quantity);
                        if(! TextUtils.isEmpty(QuantityEditText.getText().toString().trim()))
                            quantity = QuantityEditText.getText().toString().trim();
                        mListener.onFinishModifyQuantityDialog(quantity);
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ModifyQuantityDialogFragment.this.getDialog().cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        return alertDialog;
    }
}
