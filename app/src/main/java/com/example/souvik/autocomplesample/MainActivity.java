package com.example.souvik.autocomplesample;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;


import com.example.souvik.autocomplesample.TrueMDAPI.TrueMDAPI;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity {

    String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


//
//        ArrayList medicineSuggestions=com.example.souvik.autocomplesample.TrueMDAPI.getMedicineSuggestions("crocin",key);
//        for(int i =0; i< medicineSuggestions.size();i++){
//            Log.d("medicineSuggestions :",medicineSuggestions.get(i).toString());
//        }

//        String[] COUNTRIES = new String[] {
//                "Belgium", "France", "Italy", "Germany", "Spain", "India"
//        };

        AutoCompleteTextView medicine = (AutoCompleteTextView)findViewById(R.id.input_medicine_name);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
//        medicine.setAdapter(adapter);
        medicine.setAdapter(new MedicineAutoCompleteAdapter(this));
        medicine.setThreshold(1);
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

        return super.onOptionsItemSelected(item);
    }



    private class MedicineAutoCompleteAdapter extends ArrayAdapter<String> implements Filterable {

        private LayoutInflater mInflater;
//        private Geocoder mGeocoder;
//        private StringBuilder mSb = new StringBuilder();

        public MedicineAutoCompleteAdapter(final Context context) {
            super(context, -1);
            mInflater = LayoutInflater.from(context);
//            mGeocoder = new Geocoder(context);
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            final TextView tv;
            if (convertView != null) {
                tv = (TextView) convertView;
            } else {
                tv = (TextView) mInflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
            }

            tv.setText((getItem(position)));
            return tv;
        }

//        private String createFormattedAddressFromAddress(final Address address) {
//            mSb.setLength(0);
//            final int addressLineSize = address.getMaxAddressLineIndex();
//            for (int i = 0; i < addressLineSize; i++) {
//                mSb.append(address.getAddressLine(i));
//                if (i != addressLineSize - 1) {
//                    mSb.append(", ");
//                }
//            }
//            return mSb.toString();
//        }

        @Override
        public Filter getFilter() {
            Filter myFilter = new Filter() {
                @Override
                protected FilterResults performFiltering(final CharSequence constraint) {
                    List<String> medicineSuggestions =null  ;
                    if (constraint != null) {
                        try {
                            Log.d("pF constraint", (String) constraint);
                            medicineSuggestions= TrueMDAPI.getMedicineSuggestions((String) constraint, key);
                            for(int i =0; i< medicineSuggestions.size();i++){
                                Log.d("medicineSuggestions :",medicineSuggestions.get(i).toString());
                            }

//                            medicineSuggestions.add("one");
//                            medicineSuggestions.add("one1");
//                            medicineSuggestions.add("one2");
//                            medicineSuggestions.add("one3");


//                            addressList = mGeocoder.getFromLocationName((String) constraint, 5);
                        } catch (Exception e) {
                            Log.d("Exception e : ", e.toString());
                        }
                    }

                    if (medicineSuggestions == null) {
                        medicineSuggestions = new ArrayList<>();
                    }

                    final FilterResults filterResults = new FilterResults();
                    filterResults.values = medicineSuggestions;
                    filterResults.count = medicineSuggestions.size();

                    return filterResults;
                }

                @SuppressWarnings("unchecked")
                @Override
                protected void publishResults(final CharSequence constraint, final FilterResults results) {
                    clear();
                    int i=0;
                    for (String medicine : (List<String>) results.values) {
                        i++;
                        add(medicine);
                        if(i==5)break;
                    }
                    if (results.count > 0) {
                        notifyDataSetChanged();
                    } else {
                        notifyDataSetInvalidated();
                    }
                }

//                @Override
//                public CharSequence convertResultToString(final Object resultValue) {
//                    return resultValue == null ? "" : ((Address) resultValue).getAddressLine(0);
//                }
            };
            return myFilter;
        }
    }










}
