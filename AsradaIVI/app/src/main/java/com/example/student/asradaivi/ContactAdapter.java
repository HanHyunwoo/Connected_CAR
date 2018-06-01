package com.example.student.asradaivi;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends BaseAdapter {
    ArrayList<Contact> list;
    Context context;
    LinearLayout container;
    private boolean flagFirst;
    ImageView btn_call;
    public ContactAdapter() {

    }

    public ContactAdapter(Context context, LinearLayout container) {
        list = new ArrayList<>();

        flagFirst = true;
        this.context = context;
        this.container = container;
    }

    public void initContacts() {
        if (!flagFirst) {
            return;
        }

        flagFirst = false;

        String[] arrProjection = {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
        };

        String[] arrPhoneProjection = {
                ContactsContract.CommonDataKinds.Phone.NUMBER
        };

        Cursor clsCursor = context.getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                arrProjection,
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1"
                , null, null
        );

        while (clsCursor.moveToNext()) {
            String contactId = clsCursor.getString(0);
            String contactName = clsCursor.getString(1);

            Cursor clsPhoneCursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    arrPhoneProjection,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId
                    , null, null
            );

            while (clsPhoneCursor.moveToNext()) {
                list.add(new Contact(contactId, contactName, clsPhoneCursor.getString(0)));
            }

            clsPhoneCursor.close();
        }
        clsCursor.close();

        notifyDataSetChanged();
    }

    public boolean isFlagFirst() {
        return flagFirst;
    }

    public void setFlagFirst(boolean flagFirst) {
        this.flagFirst = flagFirst;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vw = null;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vw = inflater.inflate(R.layout.call_list, container, true);

        TextView name = vw.findViewById(R.id.tv_name);
        TextView number = vw.findViewById(R.id.tv_number);
        btn_call  = vw.findViewById(R.id.btn_call);

        Contact user = list.get(i);
        name.setText("이름 : " + user.getName());
        number.setText("NUMBER : " + user.getPhone());

        clickCall(user.getPhone());
        return vw;
    }


    public void clickCall(final String number){

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog;
                AlertDialog.Builder builder = new AlertDialog.Builder((Activity)view.getContext());
                builder.setTitle("Alert");
                builder.setMessage("전화할꺼냐?");

                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent  = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+number));
                        context.startActivity(intent);
                    }
                });

                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });
    }
}
