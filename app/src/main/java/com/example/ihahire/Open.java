
package com.example.ihahire;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Open {

    @SerializedName("is_overnight")
    @Expose
    private Boolean isOvernight;
    @SerializedName("start")
    @Expose
    private String start;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("day")
    @Expose
    private Integer day;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Open() {
    }

    /**
     * 
     * @param start
     * @param isOvernight
     * @param day
     * @param end
     */
    public Open(Boolean isOvernight, String start, String end, Integer day) {
        super();
        this.isOvernight = isOvernight;
        this.start = start;
        this.end = end;
        this.day = day;
    }

    public Boolean getIsOvernight() {
        return isOvernight;
    }

    public void setIsOvernight(Boolean isOvernight) {
        this.isOvernight = isOvernight;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

}
