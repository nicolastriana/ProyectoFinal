/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.sun.net.httpserver.HttpServer;
import dao.DireccionTecnicaDAO;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Nicolas Triana
 */
public class DireccionTecnicaServlet extends HttpServlet {
    private DireccionTecnicaDAO dao;
}
