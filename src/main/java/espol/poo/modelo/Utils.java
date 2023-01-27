/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package espol.poo.modelo;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Gladys
 */
public class Utils {
    /**
     * NO MODIFICAR ESTE MÉTODO
     */
    public static void sincronizar(){
        System.out.println("sincronizando con repositorio.  Esta acción puede demorar");
        //simula el proceso de sincronización de los datos con una base de datos
        //externa, por ahora lee el archivo linea a linea 
        try(BufferedReader br = new BufferedReader(new FileReader("files/productos.txt"))){
            String linea = null;
            
            while ((linea = br.readLine())!=null){
               Thread.sleep(2000);
                System.out.println("Sincronizando producto en el repositorio");     
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Sincronización finalizada");
    }
}
