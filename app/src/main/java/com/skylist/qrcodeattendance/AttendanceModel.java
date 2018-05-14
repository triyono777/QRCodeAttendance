package com.skylist.qrcodeattendance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AttendanceModel {

    private String studentName, registrationNumber, subject, instituctionURL, date;

    AttendanceModel(){}

    AttendanceModel( String studentName, String registrationNumber, String subject, String instituctionURL, String date){
        this.studentName            = studentName ;
        this.registrationNumber     = registrationNumber;
        this.subject                = subject;
        this.instituctionURL        = instituctionURL;
        this.date                   = date;
    }

    AttendanceModel( String studentName, String registrationNumber, String subject, String instituctionURL ){
        this.studentName            = studentName ;
        this.registrationNumber     = registrationNumber;
        this.subject                = subject;
        this.instituctionURL        = instituctionURL;
        this.date                   = getDateTime();
    }

    private String getDateTime(){
        return new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getInstituctionURL() {
        return instituctionURL;
    }

    public void setInstituctionURL(String instituctionURL) {
        this.instituctionURL = instituctionURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
