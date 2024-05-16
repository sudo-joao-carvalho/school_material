package pt.isec.pd.aula5.time;

import java.io.Serializable;

public class Time implements Serializable
{
    public static final long serialVersionUID = 1L;
    private int hour;
    private int minute;
    private int seconds;
    
    public Time(int hour, int minutes, int seconds)
    {
        this.hour = hour;
        this.minute = minutes;
        this.seconds = seconds;                
    }

    public int getHour() 
    {
        return hour;
    }

    public void setHour(int hour) 
    {
        this.hour = hour;
    }

    public int getMinutes() 
    {
        return minute;
    }

    public void setMinutes(int minutes) 
    {
        this.minute = minutes;
    }

    public int getSeconds() 
    {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }   
    
    @Override
    public String toString()
    {
        return String.format("%02d:%02d:%02d", hour, minute, seconds);
        //return hour + ":" + minute + ":" + second;
    }
}