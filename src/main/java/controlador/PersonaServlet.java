package controlador;


 
import dao.PersonaDAO;
import vo.Persona; 
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
 
/**
 * @author Crunchify.com
 */
 
public class PersonaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // reading the user input
            String id_persona = request.getParameter("id_persona");
            String nombre = request.getParameter("nombre");
            
            //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
            PersonaDAO dao = new PersonaDAO();
            
            Persona persona = new Persona();
            persona.setIDPersona(Integer.parseInt(id_persona));
            persona.setNombre(nombre);
            dao.insert(persona);
            
            //Listando la informacion
            List<Persona> personas =  dao.findAll();
            request.setAttribute("personas", personas);
            
            
            //Redireccionando la informacion
            RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
            redireccion.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(PersonaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        }
}
