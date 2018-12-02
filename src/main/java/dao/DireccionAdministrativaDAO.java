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
import vo.DireccionAdministrativa;

/**
 *
 * @author Laura Parada
 */
public class DireccionAdministrativaDAO implements IBaseDatos<DireccionAdministrativa> {
    
    public DireccionAdministrativa find(int IDAdministracion) throws SQLException{
        DireccionAdministrativa resultado = null;
        String query="Select * from DireccionAdministrativa Where IDAdministracion ="+ IDAdministracion;
        Connection connection = Conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id_administracion = 0;
            String nombre_areaA = null;
	    if (rs.next()){
                resultado = new DireccionAdministrativa();
	        id_administracion = rs.getInt("id_adminstracion");
	        resultado.setIDAdministracion(id_administracion);
	        nombre_areaA = rs.getString("nombre_areaA");
	        resultado.setNombreAreaA(nombre_areaA);
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener Direccion Administrativa");
			e.printStackTrace();
            }
	return resultado;
    }
        
    @Override
    public List<DireccionAdministrativa> findAll() throws SQLException {
        List<DireccionAdministrativa> dirad = null;
	    String query = "SELECT * FROM DireccionAdministrativa";
	    Connection connection = Conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id_administracion = 0;
	    String nombre_areaA = null;                       
	    while (rs.next()){
	    	if(dirad == null){
                    dirad = new ArrayList<DireccionAdministrativa>();
	    	}
	        DireccionAdministrativa registro = new DireccionAdministrativa();                        
                id_administracion = rs.getInt("id_administracion");
	        registro.setIDAdministracion(id_administracion);
	        nombre_areaA = rs.getString("nombre_areaA");
	        registro.setNombreAreaA(nombre_areaA);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Direccion Administrativa");
			e.printStackTrace();
		}
	    
	    return dirad;
    }

    @Override
    public boolean insert(DireccionAdministrativa direccionadministrativa) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
        String query = " insert into DireccionAdministrativa" + " values (?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, direccionadministrativa.getIDAdministracion());
            preparedStmt.setString(2, direccionadministrativa.getNombreAreaA());        
	    result= preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean update(DireccionAdministrativa direccionadministrativa) throws SQLException {
        boolean result = false; 
	Connection connection = Conexion.getConnection();
	String query = "update DireccionAdministriva set NombreAreaA = ? where IDAdministracion = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, direccionadministrativa.getNombreAreaA());
            preparedStmt.setInt(2, direccionadministrativa.getIDAdministracion());
                    
	    if (preparedStmt.executeUpdate() > 0){
	   	result=true;
	    }
			    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean delete(DireccionAdministrativa direccionadministrativa) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
	String query = "delete from DireccionAdministrativa where IDAdministracion = ?";
        System.out.println(query + " " + direccionadministrativa.getIDAdministracion());
	PreparedStatement preparedStmt=null;
	 try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, direccionadministrativa.getIDAdministracion());
	    result= preparedStmt.execute();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return result;
    }
}
    
