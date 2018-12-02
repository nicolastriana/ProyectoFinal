package controlador;


 
import dao.ClienteDAO;
import vo.Cliente;
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
 
public class ClienteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // reading the user input
            String id_cliente = request.getParameter("id_cliente");
            String nombre = request.getParameter("nombre");
            
            //Se debe incluir validaciones - Lo recuerda: Gestion de Excepciones.
            ClienteDAO dao = new ClienteDAO();
            
            Cliente cliente = new Cliente();
            cliente.setIDCliente(Integer.parseInt(id_cliente));
            cliente.setNombreCliente(nombre);
            dao.insert(cliente);
            
            //Listando la informacion
            List<Cliente> clientes =  dao.findAll();
            request.setAttribute("clientes", clientes);
            
            
            //Redireccionando la informacion
            RequestDispatcher redireccion = request.getRequestDispatcher("index.jsp");
            redireccion.forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        }
}
