package com.casvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.casvolley.Models.Dec_model;
import com.casvolley.Models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
        EditText name ;
    ArrayList<Dec_model> decleration_list= new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        get_data();
        get_declerations();
    }

    void get_data(){
        String url ="http://lmsgp17.comli.com/opp.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("main activty",response);
                        try {
                            JSONObject response_obj = new JSONObject(response);
                            if (response_obj.getString("Response").equals("Success")){
                                JSONObject user_obj = response_obj.getJSONObject("user");

                                UserModel userModel = new UserModel();
                                userModel.setUser_name(user_obj.getString("user_name"));
                                userModel.setUser_id(user_obj.getString("user_id"));

                                AppController.getInstance().getPrefManager().storeUser(userModel);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("request parsing err",e.toString());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("main activty",error.toString());
                        if (error instanceof NoConnectionError) {
                            String message =  "check your internet connection";
                            Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_LONG).show();
                        }
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String , String> params = new HashMap<>();
                params.put("request", "1") ;
                params.put("user_name" , name.getText().toString().trim());
                params.put("coll_id" , "20131497");
                params.put("isManger" , "false");
                return params;
            }
        };
        int socketTimeout = 10000; // 10 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                5,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest);

    }


    void get_declerations(){
        String url ="http://lmsgp17.comli.com/opp.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("main activty",response);
                        try {
                            JSONObject response_obj = new JSONObject(response);
                            if (response_obj.getString("Response").equals("Success")){
                                JSONArray feed_obj = response_obj.getJSONArray("data");

                                for (int i=0 ; i<feed_obj.length() ; i++){
                                    JSONObject current = feed_obj.getJSONObject(i);
                                    Dec_model temp = new Dec_model();
                                    temp.setDesc(current.getString("description"));
                                    temp.setTitle(current.getString("title"));
                                    decleration_list.add(temp);
                                }
                                //adapter.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("request parsing err",e.toString());

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("main activty",error.toString());
                        if (error instanceof NoConnectionError) {
                            String message =  "check your internet connection";
                            Toast.makeText(getApplicationContext(), "" + message, Toast.LENGTH_LONG).show();
                        }
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String , String> params = new HashMap<>();
                params.put("request", "3") ;
                params.put("level" , "1");
                return params;
            }
        };
        int socketTimeout = 10000; // 10 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                5,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

}
