/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexion.Conexion;
import conexion.IBaseDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import vo.Informe;

/**
 *
 * @author Laura Parada
 */
public class InformeDAO implements IBaseDatos<Informe>{
    
    public Informe find(int IDInforme) throws SQLException{
        Informe resultado = null;
        String query="Select * from Informe Where IDInforme ="+ IDInforme;
        Connection connection = Conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id_informe = 0;
            int id_proyecto = 0;
            int categoria = 0;
            int id_cliente = 0;
            String fecha = null;
	    String formato = null;
            String tematica = null;
            String localizacion = null;
	    if (rs.next()){
                resultado = new Informe();
	        id_informe = rs.getInt("id_informe");
	        resultado.setIDInforme(IDInforme);                
                id_proyecto = rs.getInt("id_proyecto");
	        resultado.setIDProyecto(id_proyecto);
                categoria = rs.getInt("categoria");
	        resultado.setCategoria(categoria);
                id_cliente = rs.getInt("id_cliente");
	        resultado.setIDCliente(id_cliente);
                fecha = rs.getString("fecha");
                resultado.setFecha(fecha);
	        formato = rs.getString("formato");
	        resultado.setFormato(formato);
	        tematica = rs.getString("tematica");
                resultado.setTematica(tematica);
                localizacion = rs.getString("localizacion");
                resultado.setLocalizacion(localizacion);
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener Informe");
			e.printStackTrace();
            }
	return resultado;
    }
        
    @Override
    public List<Informe> findAll() throws SQLException {
        List<Informe> informes = null;
	    String query = "SELECT * FROM Informe";
	    Connection connection = Conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id_informe = 0;
            int id_proyecto = 0;
            int categoria = 0;
            int id_cliente = 0;
            String fecha = null;
	    String formato = null;
            String tematica = null;
            String localizacion = null;
	    while (rs.next()){
	    	if(informes == null){
                    informes = new ArrayList<Informe>();
	    	}
	        Informe registro = new Informe();
	        id_informe = rs.getInt("id_informe");
	        registro.setIDInforme(id_informe);               
                id_proyecto = rs.getInt("id_proyecto");
	        registro.setIDProyecto(id_proyecto);
                categoria = rs.getInt("categoria");
	        registro.setCategoria(categoria);
                id_cliente = rs.getInt("id_cliente");
	        registro.setIDCliente(id_cliente);
                fecha = rs.getString("fecha");
	        registro.setFecha(fecha);
	        formato = rs.getString("formato");
	        registro.setFormato(formato);
	        tematica = rs.getString("tematica");
                registro.setTematica(tematica);
                localizacion = rs.getString("localizacion");
                registro.setLocalizacion(localizacion);
                informes.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Informes");
			e.printStackTrace();
		}
	    
	    return informes;
    }

    @Override
    public boolean insert(Informe informe) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
        String query = " insert into Informe" + " values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, informe.getIDInforme());
            preparedStmt.setInt(2, informe.getCategoria());
            preparedStmt.setString(3, informe.getFormato());
            preparedStmt.setString(4, informe.getTematica());           
            preparedStmt.setString(5, informe.getLocalizacion());
            preparedStmt.setString(6, informe.getFecha());
            preparedStmt.setInt(7, informe.getIDProyecto());          
            preparedStmt.setInt(8, informe.getIDCliente());          
	    result= preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean update(Informe informe) throws SQLException {
        boolean result = false; 
	Connection connection = Conexion.getConnection();
	String query = "update Informe set Categoria = ?, Formato = ?, Tematica = ?, Localizacion = ?,"
                + " Fecha = ?, IDProyecto = ?, IDCliente = ? where IDInforme = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
            preparedStmt.setInt(1, informe.getCategoria());
	    preparedStmt.setString(2, informe.getFormato());            
            preparedStmt.setString(3, informe.getTematica());
            preparedStmt.setString(4, informe.getLocalizacion());
            preparedStmt.setString(5, informe.getFecha());
            preparedStmt.setInt(6, informe.getIDProyecto());
            preparedStmt.setInt(7, informe.getIDCliente());
            preparedStmt.setInt(8, informe.getIDInforme());
                    
	    if (preparedStmt.executeUpdate() > 0){
	   	result=true;
	    }
			    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean delete(Informe informe) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
	String query = "delete from Informe where IDInforme = ?";
        System.out.println(query + " " + informe.getIDInforme());
	PreparedStatement preparedStmt=null;
	 try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, informe.getIDInforme());
	    result= preparedStmt.execute();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return result;
    }
    
}
