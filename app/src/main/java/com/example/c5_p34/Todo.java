package com.example.c5_p34;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Todo {
    private int id;
    private String event;
    private String duedate;

    public Todo ( int nid, String e, String dd){
        setId(nid);
        setEvent(e);
        setDuedate(dd);
    }
    public void setId( int newId ) {
        this.id = newId;
    }

    public void setEvent ( String e){
        this.event = e;
    }

    public void setDuedate (String d){
        this.duedate = d;
    }

    public int getId() {
        return this.id;
    }

    public String getEvent() {
        return this.event;
    }

    public String getDuedate() {
        return this.duedate;
    }
}
