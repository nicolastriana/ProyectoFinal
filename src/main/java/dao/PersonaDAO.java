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
import vo.Persona;

/**
 *
 * @author Laura Parada
 */
public class PersonaDAO implements IBaseDatos<Persona>{
    
    public Persona find(int ID) throws SQLException{
        Persona resultado = null;
        String query="Select * from Persona Where ID ="+ ID;
        Connection connection = Conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id = 0;
	    String nombre = null;
            String apellido = null;
            String profesion = null;
	    if (rs.next()){
                resultado = new Persona();
	        id = rs.getInt("id");
	        resultado.setID(id);
	        nombre = rs.getString("nombre");
	        resultado.setNombre(nombre);
	        apellido = rs.getString("apellido");
                resultado.setApellido(apellido);
                profesion = rs.getString("profesion");
                resultado.setProfesion(profesion);
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener persona");
			e.printStackTrace();
            }
	return resultado;
    }
        
    @Override
    public List<Persona> findAll() throws SQLException {
        List<Persona> personas = null;
	    String query = "SELECT * FROM Persona";
	    Connection connection = Conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id = 0;
	    String nombre = null;
            String apellido = null;
            String profesion = null;
	    while (rs.next()){
	    	if(personas == null){
                    personas = new ArrayList<Persona>();
	    	}
	        Persona registro = new Persona();
	        id = rs.getInt("id");
	        registro.setID(id);
	        nombre = rs.getString("nombre");
	        registro.setNombre(nombre); 
	        apellido = rs.getString("apellido");
                registro.setApellido(apellido);
                profesion = rs.getString("profesion");
                registro.setProfesion(profesion);
                personas.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Personas");
			e.printStackTrace();
		}
	    
	    return personas;
    }

    @Override
    public boolean insert(Persona persona) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
        String query = " insert into Persona" + " values (?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, persona.getID());
            preparedStmt.setString(2, persona.getNombre());
            preparedStmt.setString(3, persona.getApellido());
            preparedStmt.setString(4, persona.getProfesion());
	    result= preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean update(Persona persona) throws SQLException {
        boolean result = false; 
	Connection connection = Conexion.getConnection();
	String query = "update Persona set Nombre = ?, Apellido = ?, Profesion = ? where ID = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, persona.getNombre());
            preparedStmt.setString(2, persona.getApellido());
            preparedStmt.setString(3, persona.getProfesion());
            preparedStmt.setInt(4, persona.getID()); 
                    
	    if (preparedStmt.executeUpdate() > 0){
	   	result=true;
	    }
			    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean delete(Persona persona) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
	String query = "delete from Persona where ID = ?";
        System.out.println(query + " " + persona.getID());
	PreparedStatement preparedStmt=null;
	 try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, persona.getID());
	    result= preparedStmt.execute();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return result;
    }
}
    
