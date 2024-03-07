package com.notificationapi.notificationapi.crossCutting.utils;

public class UtilDefaultObject {


    private UtilDefaultObject(){

    };


    public static Object defaultValue(Object valorEntrada, Object valorPorDefecto){
        if(isDefault(valorEntrada)){
            return valorPorDefecto;
        }
        return  valorEntrada;
    }

    private static boolean isDefault(Object valor){
        return valor.equals(null);
    }

}
