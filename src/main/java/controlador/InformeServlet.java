package controlador;


 
import dao.InformeDAO;
import vo.Informe;
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
 
public class InformeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // reading the user input
            String id_informe = request.getParameter("id_informe");
            String nombre = request.getParameter("nombre");
            
            //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
            InformeDAO dao = new InformeDAO();
            
            Informe informe = new Informe();
            informe.setIDInforme(Integer.parseInt(id_informe));
            informe.setNombre(nombre);
            dao.insert(informe);
            
            //Listando la informacion
            List<Informe> informes =  dao.findAll();
            request.setAttribute("informes", informes);
            
            
            //Redireccionando la informacion
            RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
            redireccion.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(InformeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        }
}
