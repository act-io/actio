package com.example.sigrundish.actio;

import android.app.TimePickerDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

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
    private Button btDate,btStartTime,btEndTime;
    private Calendar calendar;
    private TextView twStartTime,twEndTime,twDate;
    private int startHour, startMinute,endHour,endMinute,yearA,monthA,dayA;
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

        startHour = calendar.get(Calendar.HOUR_OF_DAY);
        startMinute = calendar.get(Calendar.MINUTE);
        endHour = calendar.get(Calendar.HOUR_OF_DAY);
        endMinute  = calendar.get(Calendar.MINUTE);
        yearA = calendar.get(Calendar.YEAR);
        monthA = calendar.get(Calendar.MONTH);
        dayA =  calendar.get(Calendar.DAY_OF_MONTH);
        twStartTime = (TextView) v.findViewById(R.id.twStartTime);
        twEndTime =(TextView) v.findViewById(R.id.twEndTime);
        twDate = (TextView) v.findViewById(R.id.twDate);



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

        btDate = (Button) v.findViewById(R.id.btDate);

        btDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog3 =  new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        yearA = i;
                        monthA = i1;
                        dayA = i2;
                        twDate.setText("Date is : " + dayA + "." + (monthA+1) + "."  + yearA);
                    }
                },yearA,monthA,dayA);
                dialog3.show();
            }
        });


        bCreateActivity = (Button) v.findViewById(R.id.bCreateActivity);
        bCreateActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = new Date((yearA-1900),monthA,dayA,startHour,startMinute);
                mActivity.setStartTime(startTime);
                endTime = new Date((yearA-1900),monthA,dayA,endHour,endHour);
                mActivity.setEndTime(endTime);
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

            }
        });

//        public void showTimePickerDialog(View v) {
//            DialogFragment newFragment = new TimePickerFragment();
//            newFragment.show(getSupportFragmentManager(), "timePicker");
//        }
        return v;
    }



}
