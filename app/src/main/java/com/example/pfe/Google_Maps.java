package com.example.pfe;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Google_Maps extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {

    GoogleMap Map;
    Location CurrentLocation;
    FusedLocationProviderClient mFusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    Intent intent;
    String Adresse_Mdc;
    Polyline CurrentPolyLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google__maps);


        intent = getIntent();
        Adresse_Mdc = intent.getStringExtra(Profil_Mdc_search.Adresse_Mdc_geo);


        //toolbar

        Toolbar toolbar_inscription = findViewById(R.id.toolbar_GoogleMaps);
        setSupportActionBar(toolbar_inscription);
        getSupportActionBar().setTitle("Map");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //getMap

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        FetchLastLocation();


    }

    private void FetchLastLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }

        Task<Location> Task = mFusedLocationProviderClient.getLastLocation();
        Task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    CurrentLocation = location;
                    Log.d("resultat", "onMapReady: 2");
                    Toast.makeText(Google_Maps.this, CurrentLocation.getLatitude()+" "+CurrentLocation.getLongitude(), Toast.LENGTH_LONG).show();
                    Log.d("resultat", "onMapReady: 3");
                    SupportMapFragment MapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.GoogleMaps_Frag);
                    MapFragment.getMapAsync(Google_Maps.this);
                }
            }
        });

    }


    //toolbar return arrow
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Map = googleMap;
        LatLng latlng1;
        Log.d("resultat", "onMapReady: 1");

        latlng1 = getLocationFromAddress(this,Adresse_Mdc+", Algerie");
        LatLng latlng = new LatLng(CurrentLocation.getLatitude(),CurrentLocation.getLongitude());
        MarkerOptions mMarkerOptions = new MarkerOptions().position(latlng).title("Ma Position");
        MarkerOptions mMarkerOptions1 = new MarkerOptions().position(latlng1).title("La position de Medecin");




        Map.animateCamera(CameraUpdateFactory.newLatLng(latlng));
        Map.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng,15));
        //Toast.makeText(this, latlng1.latitude+" "+latlng1.longitude, Toast.LENGTH_SHORT).show();
        Map.addMarker(mMarkerOptions);
        Map.addMarker(mMarkerOptions1);
        String url = getUrl(mMarkerOptions.getPosition(),mMarkerOptions1.getPosition(),"driving");
        new FetchURL(this).execute(url, "driving");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    FetchLastLocation();
                }
                break;
        }
    }




    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            Address location = address.get(0);
            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return p1;
    }








    //iténitaire

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.Maps_Key);
        return url;
    }





    public class FetchURL extends AsyncTask<String, Void, String> {
        Context mContext;
        String directionMode = "driving";

        public FetchURL(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        protected String doInBackground(String... strings) {
            // For storing data from web service
            String data = "";
            directionMode = strings[1];
            try {
                // Fetching the data from web service
                data = downloadUrl(strings[0]);
                Log.d("resultat", "Background task data " + data.toString());
            } catch (Exception e) {
                Log.d("resultat", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            PointsParser parserTask = new PointsParser(mContext, directionMode);
            // Invokes the thread for parsing the JSON data
            parserTask.execute(s);
        }

        private String downloadUrl(String strUrl) throws IOException {
            String data = "";
            InputStream iStream = null;
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL(strUrl);
                // Creating an http connection to communicate with url
                urlConnection = (HttpURLConnection) url.openConnection();
                // Connecting to url
                urlConnection.connect();
                // Reading data from url
                iStream = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                data = sb.toString();
                Log.d("resultat", "Downloaded URL: " + data.toString());
                br.close();
            } catch (Exception e) {
                Log.d("resultat", "Exception downloading URL: " + e.toString());
            } finally {
                iStream.close();
                urlConnection.disconnect();
            }
            return data;
        }
    }

    //2

    public class PointsParser extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {
        TaskLoadedCallback taskCallback;
        String directionMode = "driving";

        public PointsParser(Context mContext, String directionMode) {
            this.taskCallback = (TaskLoadedCallback) mContext;
            this.directionMode = directionMode;
        }

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("mylog", jsonData[0].toString());
                DataParser parser = new DataParser();
                Log.d("mylog", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("mylog", "Executing routes");
                Log.d("mylog", routes.toString());

            } catch (Exception e) {
                Log.d("mylog", e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            PolylineOptions lineOptions = null;
            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();
                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);
                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
                    points.add(position);
                }
                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                if (directionMode.equalsIgnoreCase("walking")) {
                    lineOptions.width(10);
                    lineOptions.color(Color.MAGENTA);
                } else {
                    lineOptions.width(20);
                    lineOptions.color(Color.BLUE);
                }
                Log.d("mylog", "onPostExecute lineoptions decoded");
            }

            // Drawing polyline in the Google Map for the i-th route
            if (lineOptions != null) {
                //mMap.addPolyline(lineOptions);
                taskCallback.onTaskDone(lineOptions);

            } else {
                Log.d("mylog", "without Polylines drawn");
            }
        }
    }

    //last one

    public class DataParser {
        public List<List<HashMap<String, String>>> parse(JSONObject jObject) {

            List<List<HashMap<String, String>>> routes = new ArrayList<>();
            JSONArray jRoutes;
            JSONArray jLegs;
            JSONArray jSteps;
            try {
                jRoutes = jObject.getJSONArray("routes");
                /* Traversing all routes */
                for (int i = 0; i < jRoutes.length(); i++) {
                    jLegs = ((JSONObject) jRoutes.get(i)).getJSONArray("legs");
                    List path = new ArrayList<>();
                    /* Traversing all legs */
                    for (int j = 0; j < jLegs.length(); j++) {
                        jSteps = ((JSONObject) jLegs.get(j)).getJSONArray("steps");

                        /* Traversing all steps */
                        for (int k = 0; k < jSteps.length(); k++) {
                            String polyline = "";
                            polyline = (String) ((JSONObject) ((JSONObject) jSteps.get(k)).get("polyline")).get("points");
                            List<LatLng> list = decodePoly(polyline);

                            /* Traversing all points */
                            for (int l = 0; l < list.size(); l++) {
                                HashMap<String, String> hm = new HashMap<>();
                                hm.put("lat", Double.toString((list.get(l)).latitude));
                                hm.put("lng", Double.toString((list.get(l)).longitude));
                                path.add(hm);
                            }
                        }
                        routes.add(path);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
            }
            return routes;
        }


        /*
         * Method to decode polyline points
         * Courtesy : https://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
         */
        private List<LatLng> decodePoly(String encoded) {

            List<LatLng> poly = new ArrayList<>();
            int index = 0, len = encoded.length();
            int lat = 0, lng = 0;

            while (index < len) {
                int b, shift = 0, result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lat += dlat;

                shift = 0;
                result = 0;
                do {
                    b = encoded.charAt(index++) - 63;
                    result |= (b & 0x1f) << shift;
                    shift += 5;
                } while (b >= 0x20);
                int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
                lng += dlng;

                LatLng p = new LatLng((((double) lat / 1E5)),
                        (((double) lng / 1E5)));
                poly.add(p);
            }

            return poly;
        }
    }

    /*public interface TaskLoadedCallback {
        void onTaskDone(Object... values);
    }*/

    @Override
    public void onTaskDone(Object... values) {
        if (CurrentPolyLine != null){
            CurrentPolyLine.remove();
        }else{
            CurrentPolyLine = Map.addPolyline((PolylineOptions) values[0]);
        }

    }
}
