package com.example.souvik.autocomplesample.TrueMDAPI;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


public class TrueMDAPI {
	
	static String url;
	static JSONArray medicineSuggestions = null;
	static JSONArray medicineDetails = null;
	static JSONArray medicineAlternatives = null;
	
	public static ArrayList<String> getMedicineSuggestions(String find,String key)
	{
		ArrayList<String> listOfSuggestions= new ArrayList<String> ();
		 try {
	        	
				find= URLEncoder.encode(find, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        url = "http://oaayush-aayush.rhcloud.com/old_api/suggest.json?id="+find+"&key="+key+"&limit=4";
		       
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

		Log.d("Response: ", "> " + jsonStr);
		
		if (jsonStr != null) {
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);
				
				// Getting JSON Array node
				medicineSuggestions = jsonObj.getJSONArray("suggestions");

				// looping through All Contacts
				for (int i = 0; i < medicineSuggestions.length(); i++) {
					JSONObject c = medicineSuggestions.getJSONObject(i);
					
					
					String medicineName = c.getString("suggestion");
					listOfSuggestions.add(medicineName);
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any medicine suggestions from the url");
		}

		
		return listOfSuggestions;
	}
	public static Medicine getMedicineDetails(String find, String key)
	{
		Medicine medicineDetailsObject= new Medicine ();
		 try {
	        	
				find= URLEncoder.encode(find, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        url = "http://oaayush-aayush.rhcloud.com/old_api/medicine.json?id="+find+"&key="+key+"&limit=100";
		       
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

		Log.d("Response: ", "> " + jsonStr);
		
		if (jsonStr != null) {
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);
				
				// Getting JSON Array node
				medicineDetails = jsonObj.getJSONArray("medicine");

				// looping through All Contacts
				for (int i = 0; i < medicineDetails.length(); i++) {
					JSONObject c = medicineDetails.getJSONObject(i);
					
					//String medicineName = c.getString("suggestion");
					
					medicineDetailsObject.setManufacturer(c.getString("manufacturer"));
					medicineDetailsObject.setBrand(c.getString("brand"));
					medicineDetailsObject.setCategory(c.getString("category"));
					medicineDetailsObject.setDClass(c.getString("d_class"));
					medicineDetailsObject.setUnitQty(c.getString("unit_qty"));
					medicineDetailsObject.setUnitType(c.getString("unit_type"));
					medicineDetailsObject.setPackageQty(c.getString("package_qty"));
					medicineDetailsObject.setPackageType(c.getString("package_type"));
					medicineDetailsObject.setPackagePrice(c.getString("package_price"));
					medicineDetailsObject.setUnitPrice(c.getString("unit_price"));
					medicineDetailsObject.setGenericId(c.getString("generic_id"));
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any medicine suggestions from the url");
		}

		
		return medicineDetailsObject;
	}
	public static ArrayList<Medicine> getMedicineAlternatives(String find, String key)
	{
		ArrayList<Medicine> medicineAlternativesList=new ArrayList<Medicine>();
		 try {
	        	
				find= URLEncoder.encode(find, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        url = "http://oaayush-aayush.rhcloud.com/old_api/search.json?id="+find+"&key="+key+"&limit=100";
		       
		ServiceHandler sh = new ServiceHandler();

		// Making a request to url and getting response
		String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

		Log.d("Response: ", "> " + jsonStr);
		
		if (jsonStr != null) {
			try {
				JSONObject jsonObj = new JSONObject(jsonStr);
				
				// Getting JSON Array node
				medicineAlternatives = jsonObj.getJSONArray("drugs");

				// looping through All Contacts
				for (int i = 0; i < medicineAlternatives.length(); i++) {
					JSONObject c = medicineAlternatives.getJSONObject(i);
					
					//String medicineName = c.getString("suggestion");
					
					Medicine medicineDetailsObject= new Medicine ();
					medicineDetailsObject.setManufacturer(c.getString("manufacturer"));
					medicineDetailsObject.setBrand(c.getString("brand"));
					medicineDetailsObject.setCategory(c.getString("category"));
					medicineDetailsObject.setDClass(c.getString("d_class"));
					medicineDetailsObject.setUnitQty(c.getString("unit_qty"));
					medicineDetailsObject.setUnitType(c.getString("unit_type"));
					medicineDetailsObject.setPackageQty(c.getString("package_qty"));
					medicineDetailsObject.setPackageType(c.getString("package_type"));
					medicineDetailsObject.setPackagePrice(c.getString("package_price"));
					medicineDetailsObject.setUnitPrice(c.getString("unit_price"));
					medicineDetailsObject.setGenericId(c.getString("generic_id"));
					
					medicineAlternativesList.add(medicineDetailsObject);
					
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} else {
			Log.e("ServiceHandler", "Couldn't get any medicine suggestions from the url");
		}

		
		return medicineAlternativesList;
	}

}
