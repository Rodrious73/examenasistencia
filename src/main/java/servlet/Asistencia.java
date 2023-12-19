package servlet;

import dao.AsistenciaJpaController;
import dao.PersonalJpaController;
import dto.Personal;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Asistencia extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String tipo = request.getParameter("tipo");
            PersonalJpaController perDAO = new PersonalJpaController();
            AsistenciaJpaController asisDAO = new AsistenciaJpaController();
            switch (tipo) {
                case "1":
                    String codigo = request.getParameter("codigo");
                    Personal per = perDAO.findPersonal(Integer.parseInt(codigo));
                    if (per != null) {
                        Date fechaActual = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        String fechaActualStr = dateFormat.format(fechaActual);
                        String horaActualStr = timeFormat.format(fechaActual);
                        try {
                            Date fechaActualSeparada = dateFormat.parse(fechaActualStr);
                            Time horaActualSeparada = Time.valueOf(horaActualStr);
                            dto.Asistencia nuevaAsistencia = new dto.Asistencia();
                            nuevaAsistencia.setCodiPers(per.getCodiPers());
                            nuevaAsistencia.setFechAsis(fechaActualSeparada);
                            nuevaAsistencia.setHoraAsisPers(horaActualSeparada);
                            asisDAO.create(nuevaAsistencia);
                            out.print("{\"resultado\":\"ok\"}");
                        } catch (Exception e) {
                            out.print("{\"resultado\":\"error\"}");
                        }
                    } else {
                        out.print("{\"resultado\":\"error\", \"mensaje\":\"No se encontró el personal.\"}");
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
