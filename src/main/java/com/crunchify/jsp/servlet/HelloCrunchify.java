package com.crunchify.jsp.servlet;
 
import edu.co.sergio.mundo.dao.DepartamentoDAO;
import edu.co.sergio.mundo.vo.Departamento;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.RequestDispatcher;
 
/**
 * @author Crunchify.com
 */
 
public class HelloCrunchify extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // reading the user input
        String id = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        
        //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
        DepartamentoDAO dao = new DepartamentoDAO();
        
        Departamento departamento = new Departamento();
        departamento.setId_departamento(Integer.parseInt(id));
        departamento.setNom_departamento(nombre);
        dao.insert(departamento);
        
        //Listando la informacion  
        List<Departamento> departamentos =  dao.findAll();
        request.setAttribute("departamentos", departamentos);
       
       
        //Redireccionando la informacion
        RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
        redireccion.forward(request, response);
        
        
        
        }
}
