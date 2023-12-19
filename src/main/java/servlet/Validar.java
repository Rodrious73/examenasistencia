package servlet;

import dao.PersonalJpaController;
import dto.Personal;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Validar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            PersonalJpaController perDAO = new PersonalJpaController();

            String accion = request.getParameter("accion");
            String logi = request.getParameter("logi");
            String pass = request.getParameter("pass");
            String tipo = request.getParameter("tipo");
            switch (accion) {
                case "1": //Validar Inicio de Sesion
                    boolean validar = perDAO.iniciarSesion(logi, pass, Integer.parseInt(tipo));
                    if (validar) {
                        if (Integer.parseInt(tipo)==1) {
                            out.print("{\"resultado\":\"1\"}");
                        }else{
                            out.print("{\"resultado\":\"2\"}");
                        }   
                    } else {
                        out.print("{\"resultado\":\"error\"}");
                    }
                    break;
                case "2": //Cambiar Contraseña
                    String codiPers = request.getParameter("codiPers");
                    String nuevoPass = request.getParameter("pass");

                    Personal per = perDAO.findPersonal(Integer.parseInt(codiPers));
                    if (per != null) {
                        per.setPassPers(nuevoPass);

                        try {
                            perDAO.edit(per);
                            out.print("{\"resultado\":\"ok\"}");
                        } catch (Exception e) {
                            out.print("{\"resultado\":\"error\"}");
                        }
                    } else {
                        out.print("{\"resultado\":\"error\"}");
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
