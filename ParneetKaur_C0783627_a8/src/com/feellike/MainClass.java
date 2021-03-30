package com.feellike;

import javax.swing.*;

public class MainClass {

    public static void main(String[] args){

        String name = JOptionPane.showInputDialog("Enter you name").trim();

        JOptionPane.showMessageDialog(null, "Welcome "+name);
        while(true) {
            String day = null;
            float tmep = 0, windSpeed = 0;
            try {
                day = getDay();
                tmep = getFloat("Ener the temprature for " + day + "in degree C");
                windSpeed = getFloat("Enter the wind speed in Km/h");
            }catch (NullPointerException e){
                System.out.println("thank you!");
                return;
            }
            Temprature temprature = new Temprature(day, tmep, windSpeed);

            JOptionPane.showMessageDialog(null, temprature.outputString());

            int input = JOptionPane.showConfirmDialog(null, "Do you want to calculate for another day");

            if (input != JOptionPane.YES_OPTION) {
                break;
            }
        }
    }

    public static String getDay(){
        String day = JOptionPane.showInputDialog("Enter the day").trim();
        return day;
    }

    public static float getFloat(String prompt){
        float temprature = 0;
        try{
            temprature = Float.parseFloat(JOptionPane.showInputDialog(prompt).trim());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Wrong Input! Try Agian!");
            getFloat(prompt);
        }

        return temprature;
    }

}