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
import vo.GerenciaGeneral;

/**
 *
 * @author Laura Parada
 */
public class GerenciaGeneralDAO implements IBaseDatos<GerenciaGeneral>{
    
    public GerenciaGeneral find(int IDGerencia) throws SQLException{
        GerenciaGeneral resultado = null;
        String query="Select * from GerenciaGeneral Where IDGerencia ="+ IDGerencia;
        Connection connection = Conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id_gerencia = 0;	    
	    if (rs.next()){
                resultado = new GerenciaGeneral();
	        id_gerencia = rs.getInt("id_gerencia");
	        resultado.setIDGerencia(id_gerencia);	        	            
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener Gerencia General");
			e.printStackTrace();
            }
	return resultado;
    }
    
    @Override
    public List<GerenciaGeneral> findAll() throws SQLException {
        List<GerenciaGeneral> gerengene = null;
	    String query = "SELECT * FROM GerenciaGeneral";
	    Connection connection = Conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id_gerencia = 0;                       
	    while (rs.next()){
	    	if(gerengene == null){
                    gerengene = new ArrayList<GerenciaGeneral>();
	    	}
	        GerenciaGeneral registro = new GerenciaGeneral();
                id_gerencia = rs.getInt("id_gerencia");
	        registro.setIDGerencia(id_gerencia);                        
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Gerencia General");
			e.printStackTrace();
		}
	    
	    return gerengene;
    }

    @Override
    public boolean insert(GerenciaGeneral gerenciageneral) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
        String query = " insert into GerenciaGeneral" + " values (?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, gerenciageneral.getIDGerencia());                
	    result= preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean update(GerenciaGeneral gerenciageneral) throws SQLException {
        boolean result = false; 
	Connection connection = Conexion.getConnection();
	String query = "update GerenciaGeneral set IDGerencia = ? where IDGerencia = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, gerenciageneral.getIDGerencia());
            preparedStmt.setInt(2, gerenciageneral.getIDGerencia());
                    
	    if (preparedStmt.executeUpdate() > 0){
	   	result=true;
	    }
			    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean delete(GerenciaGeneral gerenciageneral) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
	String query = "delete from GerenciaGeneral where IDGerencia = ?";
        System.out.println(query + " " + gerenciageneral.getIDGerencia());
	PreparedStatement preparedStmt=null;
	 try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, gerenciageneral.getIDGerencia());
	    result= preparedStmt.execute();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return result;
    }
    
}
