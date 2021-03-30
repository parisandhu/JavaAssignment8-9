package com.feellike;

public class Temprature{
    private String day;
    private float temprature, windSpeed;

    public Temprature(String day, float temprature, float windSpeed){
        this.day = day;
        this.temprature = temprature;
        this.windSpeed = windSpeed;
    }

    public float feelsLike(){
        return temprature * windSpeed; // formula for feels like temprature
    }

    public String outputString(){
        StringBuilder output = new StringBuilder();
        output.append("day - "+day+"\n");
        output.append("temprature - ").append(temprature).append("\n");
        output.append("Wind speed - ").append(windSpeed);
        output.append("\nFeels Like temprature - ").append(feelsLike());
        return output.toString();
    }
}
