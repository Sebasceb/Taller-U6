/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.poo.modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Gladys
 */
public class Producto implements Comparable<Producto>{
    private int id;
    private String nombre;
    private Categoria categoria;

    public Producto() {
    }

    public Producto(int id, String nombre, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Producto other = (Producto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Producto o) {
        return Integer.compare(id, o.id);
    }
    
    public static ArrayList<Producto> cargarProductos(String ruta){
        ArrayList<Producto> productos = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(ruta))){
            String linea = null;
            
            while ((linea = br.readLine())!=null){
                String[] datos = linea.split(",");
                Categoria cat = Categoria.valueOf(datos[2]);
                productos.add(new Producto(Integer.valueOf(datos[0]),datos[1],cat));
                     
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return productos;
    }
    
    public static void guardarProducto(String ruta, int id, String nombre, Categoria categoria) throws IOException{
        FileWriter fw = new FileWriter(ruta,true);
        fw.write(String.valueOf(id)+","+nombre+","+categoria+"\n");
        fw.close();
    }
    
}
