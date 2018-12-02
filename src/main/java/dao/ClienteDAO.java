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
import vo.Cliente;

/**
 *
 * @author Laura Parada
 */
public class ClienteDAO implements IBaseDatos<Cliente>{
    
    public Cliente find(int IDCliente) throws SQLException{
        Cliente resultado = null;
        String query="Select * from Cliente Where IDCliente ="+ IDCliente;
        Connection connection = Conexion.getConnection();
        try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id_cliente = 0;
	    String nombre_cliente = null;
            String siglas = null;            
	    if (rs.next()){
                resultado = new Cliente();
	        id_cliente = rs.getInt("id_cliente");
	        resultado.setIDCliente(id_cliente);
	        nombre_cliente = rs.getString("nombre_cliente");
	        resultado.setNombreCliente(nombre_cliente);
	        siglas = rs.getString("siglas");
                resultado.setSigla(siglas);                
	    }
	    st.close();
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener cliente");
			e.printStackTrace();
            }
	return resultado;
    }
    
    @Override
    public List<Cliente> findAll() throws SQLException {
        List<Cliente> clientes = null;
	    String query = "SELECT * FROM Cliente";
	    Connection connection = Conexion.getConnection();
	    try {
	    Statement st = connection.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    int id_cliente = 0;
	    String nombre_cliente = null;
            String siglas = null;            
	    while (rs.next()){
	    	if(clientes == null){
                    clientes = new ArrayList<Cliente>();
	    	}
	        Cliente registro = new Cliente();
                id_cliente = rs.getInt("id_cliente");
	        registro.setIDCliente(id_cliente);
	        nombre_cliente = rs.getString("nombre_cliente");
	        registro.setNombreCliente(nombre_cliente); 
	        siglas = rs.getString("siglas");
                registro.setSigla(siglas);
                clientes.add(registro);
	    }
	    st.close();
	    
	    } catch (SQLException e) {
			System.out.println("Problemas al obtener la lista de Clientes");
			e.printStackTrace();
		}
	    
	    return clientes;
    }

    @Override
    public boolean insert(Cliente cliente) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
        String query = " insert into Cliente" + " values (?,?,?)";
        PreparedStatement preparedStmt = null;
        try {
            preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, cliente.getIDCliente());
            preparedStmt.setString(2, cliente.getNombreCliente());
            preparedStmt.setString(3, cliente.getSigla());           
	    result= preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean update(Cliente cliente) throws SQLException {
        boolean result = false; 
	Connection connection = Conexion.getConnection();
	String query = "update Cliente set NombreCliente = ?, Siglas = ? where IDCliente = ?";
	PreparedStatement preparedStmt=null;
	try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setString(1, cliente.getNombreCliente());
            preparedStmt.setString(2, cliente.getSigla());
            preparedStmt.setInt(3, cliente.getIDCliente());
                    
	    if (preparedStmt.executeUpdate() > 0){
	   	result=true;
	    }
			    
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return result;
    }

    @Override
    public boolean delete(Cliente cliente) throws SQLException {
        boolean result = false;
	Connection connection = Conexion.getConnection();
	String query = "delete from Cliente where IDCliente = ?";
        System.out.println(query + " " + cliente.getIDCliente());
	PreparedStatement preparedStmt=null;
	 try {
	    preparedStmt = connection.prepareStatement(query);
	    preparedStmt.setInt(1, cliente.getIDCliente());
	    result= preparedStmt.execute();
	} catch (SQLException e) {
	  e.printStackTrace();
	}
	return result;
    }
    
}
