/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author Laura Parada
 */
public class Informe {
    
    private int IDInforme;
    private int Categoria;
    private String Formato;
    private String Tematica;
    private String Localizacion;
    private String Fecha;
    private int IDProyecto;
    private int IDCliente;

    public int getIDCliente() {
        return IDCliente;
    }

    public void setIDCliente(int IDCliente) {
        this.IDCliente = IDCliente;
    }
    
    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int Categoria) {
        this.Categoria = Categoria;
    }
   
    public int getIDInforme() {
        return IDInforme;
    }

    public void setIDInforme(int IDInforme) {
        this.IDInforme = IDInforme;
    }

    public String getFormato() {
        return Formato;
    }

    public void setFormato(String Formato) {
        this.Formato = Formato;
    }

    public String getTematica() {
        return Tematica;
    }

    public void setTematica(String Tematica) {
        this.Tematica = Tematica;
    }

    public String getLocalizacion() {
        return Localizacion;
    }

    public void setLocalizacion(String Localizacion) {
        this.Localizacion = Localizacion;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public int getIDProyecto() {
        return IDProyecto;
    }

    public void setIDProyecto(int IDProyecto) {
        this.IDProyecto = IDProyecto;
    }

    
    
}
