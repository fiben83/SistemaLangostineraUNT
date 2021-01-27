/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.util.ArrayList;
import java.util.Iterator;
import modelos.Opcion;
import modelos.SubOpcion;


public class HelperUrl {
    
    public static Boolean validUrl(ArrayList<Opcion> opciones,String url){
    	System.out.println("Estoy en Helper.Validar URL");
    	System.out.println("La URL actual es "+url);
        Boolean flag = false;
        if(url.equals("jsp/home") || url.equals("VistaPerfil")){
            flag = true;
        }else{
            Iterator op = opciones.iterator();
            while(op.hasNext()){
                Opcion opcion = (Opcion)op.next();
                ArrayList<SubOpcion> subopciones = opcion.getSubopciones();
                Iterator su = subopciones.iterator();
                while(su.hasNext()){
                    SubOpcion sub = (SubOpcion)su.next();
                    if(sub.getUrl().equals(url)){
                        flag =true;
                        break;
                    }
                }
            }
        }
        return flag;
    }
}
