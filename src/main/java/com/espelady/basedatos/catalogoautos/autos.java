/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espelady.basedatos.catalogoautos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Usuario iTC
 */
public class autos {
    int Id;
    String Modelo;
    String Marca;
    String Color;
    float precio;

    public autos(int Id, String Modelo, String Marca, String Color, float precio) {
        this.Id = Id;
        this.Modelo = Modelo;
        this.Marca = Marca;
        this.Color = Color;
        this.precio = precio;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void GuardarAuto(){
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "INSERT INTO camisas (Id, modelo, marca,color,precio) VALUES (null,?,?,?,?)";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getModelo());
            parametro.setString(2, this.getMarca());
            parametro.setString(3, this.getColor());
            parametro.setFloat(4, this.getPrecio());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
     public void ModificarAuto(){
        Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "UPDATE autos SET modelo=?, marca=?,color=?,precio=? WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setString(1, this.getModelo());
            parametro.setString(2, this.getMarca());
            parametro.setString(3, this.getColor());
            parametro.setFloat(4, this.getPrecio());
            parametro.setInt(5, this.getId());


            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
     
    
    public ArrayList<autos> ObtenerAuto(){
        Connection conexionDb = ConexionDb.getConnection();
        ResultSet rsAuto;
        
        var autos = new ArrayList<autos>();
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "SELECT * FROM autos";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            
            //Ejecutar la sentecia SQL
             rsAuto=parametro.executeQuery();           
            
            while(rsAuto.next()){              
                    autos.add(new autos(rsAuto.getInt("Id"),rsAuto.getString("modelo"),rsAuto.getString("marca"),rsAuto.getString("color"),rsAuto.getFloat("precio")));

            }
            
            conexionDb.close();
            return autos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
        
    }

    void EliminarAuto() {
Connection conexionDb = ConexionDb.getConnection();
        
        //Ejecutar operaciones en la BD
        //Crear la sentencia SQL
        String sentenciaSql = "DELETE FROM autos WHERE Id=?";
        try {
            //Configurar los paramewtros de la sentencia SQL
            PreparedStatement parametro = conexionDb.prepareStatement(sentenciaSql);
            parametro.setInt(1, this.getId());

            //Ejecutar la sentecia SQL
            parametro.execute();
            conexionDb.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
