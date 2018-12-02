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
            int id_tecnica = 0;
            int id_gerencia = 0;
            int id_administracion = 0;
	    String nombre = null;
            String apellido = null;
            String profesion = null;
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
                id_tecnica = rs.getInt("id_tecnica");
	        resultado.setIDTecnica(id_tecnica);
                id_gerencia = rs.getInt("id_gerencia");
	        resultado.setIDGerencia(id_gerencia);
                id_administracion = rs.getInt("id_administracion");
	        resultado.setIDAdministracion(id_administracion);
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
            int id_tecnica = 0;
            int id_gerencia = 0;
            int id_administracion = 0;
	    String nombre = null;
            String apellido = null;
            String profesion = null;
	    while (rs.next()){
	    	if(personas == null){
                    personas = new ArrayList<Persona>();
	    	}
	        Persona registro = new Persona();
	        id_persona = rs.getInt("id");
	        registro.setIDPersona(id_persona);
	        nombre = rs.getString("nombre");
	        registro.setNombre(nombre); 
	        apellido = rs.getString("apellido");
                registro.setApellido(apellido);
                profesion = rs.getString("profesion");
                registro.setProfesion(profesion);
                personas.add(registro);
                id_tecnica = rs.getInt("id_tecnica");
	        registro.setIDTecnica(id_tecnica);
                id_gerencia = rs.getInt("id_gerencia");
	        registro.setIDGerencia(id_gerencia);
                id_administracion = rs.getInt("id_administracion");
	        registro.setIDAdministracion(id_administracion);
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
        String query = " insert into Persona" + " values (?,?,?,?,?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, persona.getIDPersona());
            preparedStmt.setString(2, persona.getNombre());
            preparedStmt.setString(3, persona.getApellido());
            preparedStmt.setString(4, persona.getProfesion());
            preparedStmt.setInt(5, persona.getIDTecnica());
            preparedStmt.setInt(6, persona.getIDGerencia());
            preparedStmt.setInt(7, persona.getIDAdministracion());            
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
	String query = "update Persona set Nombre = ?, Apellido = ?, Profesion = ?, IDTecnica = ?,"
                + " IDGerencia = ?, IDAdministracion = ? where IDPersona = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, persona.getNombre());
            preparedStmt.setString(2, persona.getApellido());
            preparedStmt.setString(3, persona.getProfesion());
            preparedStmt.setInt(4, persona.getIDTecnica()); 
            preparedStmt.setInt(5, persona.getIDGerencia()); 
            preparedStmt.setInt(6, persona.getIDAdministracion()); 
            preparedStmt.setInt(7, persona.getIDPersona()); 
                    
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
    
