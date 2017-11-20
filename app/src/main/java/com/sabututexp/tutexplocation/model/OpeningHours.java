package com.sabututexp.tutexplocation.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by s on 20/11/17.
 */

public class OpeningHours {
    @SerializedName("open_now")
    private boolean openNow;

    @SerializedName("weekday_text")
    private List<Object> weekdayText;

    public void setOpenNow(boolean openNow){
        this.openNow = openNow;
    }

    public boolean isOpenNow(){
        return openNow;
    }

    public void setWeekdayText(List<Object> weekdayText){
        this.weekdayText = weekdayText;
    }

    public List<Object> getWeekdayText(){
        return weekdayText;
    }

    @Override
    public String toString(){
        return
                "OpeningHours{" +
                        "open_now = '" + openNow + '\'' +
                        ",weekday_text = '" + weekdayText + '\'' +
                        "}";
    }
}
