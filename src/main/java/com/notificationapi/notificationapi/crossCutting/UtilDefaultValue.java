package com.notificationapi.notificationapi.crossCutting;

public class UtilDefaultValue {


    private UtilDefaultValue(){

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
