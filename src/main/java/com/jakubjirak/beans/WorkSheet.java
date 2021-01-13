package com.jakubjirak.beans;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;

public class WorkSheet {
    private LocalDate date;
    private Integer hours;
    private String units;
    private String description;

    public WorkSheet(LocalDate date, Integer hours, String units, String description) {
        this.date = date;
        this.hours = hours;
        this.units = units;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object[] toObject(){
        String pattern = "dd. MM. yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formattedDate = simpleDateFormat.format(date.toDate());
        Object[] object = { formattedDate, hours, units, description};
        return object;
    }
}
