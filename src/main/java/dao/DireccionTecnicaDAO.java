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
import vo.DireccionTecnica;

/**
 *
 * @author Laura Parada
 */
public class DireccionTecnicaDAO implements IBaseDatos<DireccionTecnica> {

    public DireccionTecnica find(int IDTecnica) throws SQLException{
        DireccionTecnica resultado = null;
        String query="Select * from DireccionTecnica Where IDTecnica ="+ IDTecnica;
        Connection connection = Conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id_tecnica = 0;
	    String nombre_areaT = null;
	    if (rs.next()){
                resultado = new DireccionTecnica();
	        id_tecnica = rs.getInt("id_tecnica");
	        resultado.setIDTecnica(id_tecnica);
	        nombre_areaT = rs.getString("nombre_areaT");
	        resultado.setNombreAreaT(nombre_areaT);	            
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener Direccion Tecnica");
			e.printStackTrace();
            }
	return resultado;
    }
        
    @Override
    public List<DireccionTecnica> findAll() throws SQLException {
            List<DireccionTecnica> dirtec = null;
	    String query = "SELECT * FROM DireccionTecnica";
	    Connection connection = Conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id_tecnica = 0;
	    String nombre_areaT = null;                       
	    while (rs.next()){
	    	if(dirtec == null){
                    dirtec = new ArrayList<DireccionTecnica>();
	    	}
	        DireccionTecnica registro = new DireccionTecnica();
                id_tecnica = rs.getInt("id_tecnica");
	        registro.setIDTecnica(id_tecnica);
	        nombre_areaT = rs.getString("nombre_areaT");
	        registro.setNombreAreaT(nombre_areaT);                
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Direccion Tecnica");
			e.printStackTrace();
		}
	    
	    return dirtec;
    }

    @Override
    public boolean insert(DireccionTecnica direcciontecnica) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
        String query = " insert into DireccionTecnica" + " values (?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, direcciontecnica.getIDTecnica());
            preparedStmt.setString(2, direcciontecnica.getNombreAreaT());        
	    result= preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean update(DireccionTecnica direcciontecnica) throws SQLException {
        boolean result = false; 
	Connection connection = Conexion.getConnection();
	String query = "update DireccionTecnica set NombreAreaT = ? where IDTecnica = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, direcciontecnica.getNombreAreaT());
            preparedStmt.setInt(2, direcciontecnica.getIDTecnica());
                    
	    if (preparedStmt.executeUpdate() > 0){
	   	result=true;
	    }
			    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean delete(DireccionTecnica direcciontecnica) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
	String query = "delete from DireccionTecnica where IDTecnica = ?";
        System.out.println(query + " " + direcciontecnica.getIDTecnica());
	PreparedStatement preparedStmt=null;
	 try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, direcciontecnica.getIDTecnica());
	    result= preparedStmt.execute();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return result;
    }
    
}
