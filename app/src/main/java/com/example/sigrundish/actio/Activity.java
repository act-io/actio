package com.example.sigrundish.actio;

import android.app.DatePickerDialog;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * Created by sigrundish on 25/02/2018.
 */

public class Activity {

    private UUID mId;
    private String mTitle;
    private String mDescription;
//    private User mUser;
//    private List<User> mGuests;
    private String mLocation;
    private Date startTime;
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    //    private LocalDateTime mBeginTime;
//    private LocalDateTime mEndTime;
//    private Date mDate;
//    private List<Interest> mRelatedInterests;


    public Activity() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() { return mDescription; }

    public String getLocation() { return mLocation; }

//    public User getOwner() { return mOwner; }

//    public List<User> getGuests() { return mGuests; }

//    public LocalDateTime getBeginTime() { return mBeginTime;}
//
//    public LocalDateTime getmEndTime() { return mEndTime; }
//
//    public Date getDate() {
//        return mDate;
//    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setDescription(String mDescription) { this.mDescription = mDescription; }

    public void setLocation( String mLocation ) { this.mLocation = mLocation; }

//    public void setOwner(User owner) {this.mOwner = mOwner; }
//
//    public void setGuests(List<User> guests) {this.mGuests = mGuests; }

//    public void setBeginTime(LocalDateTime mBeginTime) { this.mBeginTime = mBeginTime;}
//
//    public void setmEndTime(LocalDateTime mEndTime) { this.mEndTime = mEndTime; }
//
//    public void setDate(Date mDate) { this.mDate = mDate; }


}
