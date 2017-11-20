package com.sabututexp.tutexplocation.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by s on 20/11/17.
 */

public class DebugLog {
    @SerializedName("line")
    private List<Object> line;

    public void setLine(List<Object> line){
        this.line = line;
    }

    public List<Object> getLine(){
        return line;
    }

    @Override
    public String toString(){
        return
                "DebugLog{" +
                        "line = '" + line + '\'' +
                        "}";
    }
}
