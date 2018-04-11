package com.example.sigrundish.actio;


import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by sigrundish on 25.2.2018.
 */

public class ActivityFragment  extends Fragment   {
    private Activity mActivity;
    private EditText mTitleField;
    private EditText mDescriptionField;
    private EditText mLocationField;
    private Button bCreateActivity;
    private User user;

    private Button btStartDate,btEndDate,btStartTime,btEndTime;
    private Calendar calendar;
    private TextView twStartTime,twEndTime,twStartDate,twEndDate;
    private int startHour, startMinute,endHour,endMinute, startYear, startMonth, startDay,endYear,endMonth,endDay;
    private Date startTime,endTime;
    final String url = "http://actio-server.herokuapp.com/activities";


    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = new Activity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_activity, container, false);
        calendar = Calendar.getInstance();
        user = (User)getActivity().getIntent().getSerializableExtra("user");

        startHour = calendar.get(Calendar.HOUR_OF_DAY);
        startMinute = calendar.get(Calendar.MINUTE);
        endHour = calendar.get(Calendar.HOUR_OF_DAY);
        endMinute  = calendar.get(Calendar.MINUTE);
        startYear = calendar.get(Calendar.YEAR);
        startMonth = calendar.get(Calendar.MONTH);
        startDay =  calendar.get(Calendar.DAY_OF_MONTH);
        twStartTime = (TextView) v.findViewById(R.id.twStartTime);
        twEndTime =(TextView) v.findViewById(R.id.twEndTime);
        twStartDate = (TextView) v.findViewById(R.id.twStartDate);
        twEndDate = (TextView) v.findViewById(R.id.twEndDate);



        mTitleField = (EditText) v.findViewById(R.id.activity_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (
                    CharSequence s, int start, int count, int after) {
                // blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mActivity.setTitle(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Blank
            }

        });

        mDescriptionField = (EditText) v.findViewById(R.id.activity_description);
        mDescriptionField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (
                    CharSequence s, int start, int count, int after) {
                // blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mActivity.setDescription(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Blank
            }

        });

        mLocationField = (EditText) v.findViewById(R.id.activity_location);
        mLocationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (
                    CharSequence s, int start, int count, int after) {
                // blank
            }
            @Override
            public void onTextChanged(
                    CharSequence s, int start, int before, int count) {
                mActivity.setLocation(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // Blank
            }

        });

        btStartTime = (Button) v.findViewById(R.id.btStartTime);

        btStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        //set selected time to textview
                        startHour = hour;
                        startMinute = min;
                        twStartTime.setText("Starting time " + startHour + ":" + startMinute);
                    }
                }, startHour, startMinute,true);
                dialog.show();
            }
        });

        btEndTime =(Button) v.findViewById(R.id.btEndTime);

        btEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog2=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        //set selected time to textview
                        endHour = hour;
                        endMinute = min;
                        twEndTime.setText("Ending time " + endHour + ":" + endMinute);
                    }
                }, endHour, endMinute,true);
                dialog2.show();
            }
        });

        btStartDate = (Button) v.findViewById(R.id.btStartDate);

        btStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog3 =  new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        startYear = i;
                        startMonth = i1;
                        startDay = i2;
                        twStartDate.setText("The starting date is : " + startDay + "." + (startMonth +1) + "."  + startYear);
                    }
                }, startYear, startMonth, startDay);
                dialog3.show();
            }
        });
        btEndDate = (Button) v.findViewById(R.id.btEndDate);
        btEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog3 =  new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        endYear = i;
                        endMonth = i1;
                        endDay = i2;
                        twEndDate.setText("The ending date is : " + endDay + "." + (endMonth +1) + "."  + endYear);
                    }
                }, startYear, startMonth, startDay);
                dialog3.show();

            }
        });


        bCreateActivity = (Button) v.findViewById(R.id.bCreateActivity);
        bCreateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = new Date((startYear -1900), startMonth, startDay,startHour,startMinute);
                mActivity.setStartTime(startTime);
                endTime = new Date((endYear -1900), endMonth, endDay,endHour,endHour);
                mActivity.setEndTime(endTime);
                if(endTime.before(startTime)){
                    Toast.makeText(getActivity(),
                            "The activity can not end before it starts",Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                RequestQueue queue = Volley.newRequestQueue(getActivity());
                queue.start();
                HashMap<String, String> params = new HashMap<String,String>();
                params.put("title",mActivity.getTitle()); // the entered data as the body.
                params.put("description", mActivity.getDescription()); // the entered data as the body.
                params.put("location", mActivity.getLocation()); // the entered data as the body.
                params.put("startTime",df.format(startTime));
                params.put("endTime",df.format(endTime));
                JsonObjectRequest jsObjRequest = new
                        JsonObjectRequest(Request.Method.POST,
                        url,
                        new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // DisplayText.setText(response.getString("message"));
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // DisplayText.setText("That didn't work!");
                    }
                });
                queue.add(jsObjRequest);
                    Intent userAreaIntent = new Intent(getActivity(), UserAreaActivity.class);
                    //Send the user to next activity.
                    userAreaIntent.putExtra("user", user);
                    getActivity().startActivity(userAreaIntent);


            }}
        });

//        public void showTimePickerDialog(View v) {
//            DialogFragment newFragment = new TimePickerFragment();
//            newFragment.show(getSupportFragmentManager(), "timePicker");
//        }
        return v;
    }



}
