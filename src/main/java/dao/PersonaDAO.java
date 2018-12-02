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
	    int id_persona = 0;
	    String nombre = null;
            String apellido = null;
            String profesion = null;
            String departamento = null;
	    if (rs.next()){
                resultado = new Persona();
	        id_persona = rs.getInt("id_persona");
	        resultado.setIDPersona(id_persona);
	        nombre = rs.getString("nombre");
	        resultado.setNombre(nombre);
	        apellido = rs.getString("apellido");
                resultado.setApellido(apellido);
                profesion = rs.getString("profesion");
                resultado.setProfesion(profesion);
                departamento = rs.getString("departamento");
                resultado.setDepartamento(departamento);
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
	    int id_persona = 0;
	    String nombre = null;
            String apellido = null;
            String profesion = null;
            String departamento = null;
	    while (rs.next()){
	    	if(personas == null){
                    personas = new ArrayList<Persona>();
	    	}
	        Persona registro = new Persona();
	        id_persona = rs.getInt("id_persona");
	        registro.setIDPersona(id_persona);
	        nombre = rs.getString("nombre");
	        registro.setNombre(nombre); 
	        apellido = rs.getString("apellido");
                registro.setApellido(apellido);
                profesion = rs.getString("profesion");
                registro.setProfesion(profesion);
                departamento = rs.getString("departamento");
                registro.setDepartamento(departamento);
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
        String query = " insert into Persona" + " values (?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, persona.getIDPersona());
            preparedStmt.setString(2, persona.getNombre());
            preparedStmt.setString(3, persona.getApellido());
            preparedStmt.setString(4, persona.getProfesion());       
            preparedStmt.setString(5, persona.getDepartamento());       
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
	String query = "update Persona set Nombre = ?, Apellido = ?, Profesion = ?, Departamento = ?"
                + " where IDPersona = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, persona.getNombre());
            preparedStmt.setString(2, persona.getApellido());
            preparedStmt.setString(3, persona.getProfesion());          
            preparedStmt.setString(3, persona.getDepartamento());          
            preparedStmt.setInt(4, persona.getIDPersona()); 
                    
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
	String query = "delete from Persona where IDPersona = ?";
        System.out.println(query + " " + persona.getIDPersona());
	PreparedStatement preparedStmt=null;
	 try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, persona.getIDPersona());
	    result= preparedStmt.execute();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return result;
    }
}
    
