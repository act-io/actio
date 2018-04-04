package com.example.sigrundish.actio;

import java.io.Serializable;

/**
 * Created by sigrundish on 25/02/2018.
 */

@SuppressWarnings("serial")
public class User implements Serializable {

    private int mId;
    private String mName;
    private String mUsername;
//    private String mEmail;
    private String mPassword;
//    private Date mBirthDate;
    private String mAge;
//    prvate List<int> mInterests = new List<int>();



    public User() {
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getUsername() { return mUsername; }

//    public String getEmail() { return mEmail; }

    public String getPassword() { return mPassword; }

//    public Date getBirthDate() { return mBirthDate; }

    public String getAge() {
        return mAge;
    }

//    public List<int> getInterests() { return mInterests;}


    public void setId(int mId) {
         this.mId = mId;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public void setUsername(String mUsername) { this.mUsername = mUsername; }

//    public void setEmail( String mEmail ) { this.mEmail = mEmail; }

    public void setPassword( String mPassword ) { this.mPassword = mPassword; }

//    public void setBirthDate( Date mBirthDate ) { this.mBirthDate = mBirthDate; }

    public void setAge( String mAge ) { this.mAge = mAge; }

//    public void setInterests( List<int> mInterests ) { this.mInterests = mInterests; }


}
