package servlet;

import com.google.gson.Gson;
import dao.PersonalJpaController;
import dto.Personal;
import jakarta.mail.internet.ParseException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PersonalCRUD extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String tipo = request.getParameter("tipo");
            String resultado = "";
            PersonalJpaController perDAO = new PersonalJpaController();
            switch (tipo) {
                case "1": //Listar Personal
                    List<Personal> lista = perDAO.findPersonalEntities();
                    Gson g = new Gson();
                    resultado = g.toJson(lista);
                    resultado = "{\"data\":" + resultado + "}";
                    out.print(resultado);
                    break;
                case "2": //Buscar Personal
                    String codigo = request.getParameter("codigo");
                    Personal per = perDAO.findPersonal(Integer.parseInt(codigo));
                    if (per != null) {
                        Gson gson = new Gson();
                        resultado = gson.toJson(per);
                        out.print(resultado);
                    } else {
                        out.print("{\"resultado\":\"error\"}");
                    }
                    break;
                case "3": //Editar Personal
                    String codiPers = request.getParameter("codigo");
                    String nombPers = request.getParameter("nombres");
                    String appaPers = request.getParameter("appa");
                    String apmaPers = request.getParameter("apma");
                    String pesoPers = request.getParameter("peso");
                    String tipoRol = request.getParameter("rol");
                    String fechaNaciPers = request.getParameter("fecha");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaNacimiento = null;

                    try {
                        fechaNacimiento = dateFormat.parse(fechaNaciPers);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Personal editarPersonal = perDAO.findPersonal(Integer.parseInt(codiPers));
                    editarPersonal.setNombPers(nombPers);
                    editarPersonal.setAppaPers(appaPers);
                    editarPersonal.setApmaPers(apmaPers);
                    editarPersonal.setCodiTipo(Integer.parseInt(tipoRol));
                    editarPersonal.setPesoPers(Double.parseDouble(pesoPers));

                    editarPersonal.setFechNaciPers(fechaNacimiento);

                    try {
                        perDAO.edit(editarPersonal);
                        out.print("{\"resultado\":\"ok\"}");
                    } catch (Exception e) {
                        out.print("{\"resultado\":\"error\"}");
                    }
                    break;
                case "4": //Agregar Personal
                    String codiString = request.getParameter("codigo");
                    String nombString = request.getParameter("nombres");
                    String appaString = request.getParameter("appa");
                    String apmaString = request.getParameter("apma");
                    String pesoString = request.getParameter("peso");
                    String fechaString = request.getParameter("fecha");
                    String logi = request.getParameter("logi");
                    String rol = request.getParameter("rol");
                    SimpleDateFormat dateFormatoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaNacimientoPers = null;

                    try {
                        fechaNacimientoPers = dateFormatoDateFormat.parse(fechaString);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Personal agregarPersonal = new Personal();
                    agregarPersonal.setCodiPers(Integer.parseInt(codiString));
                    agregarPersonal.setNombPers(nombString);
                    agregarPersonal.setAppaPers(appaString);
                    agregarPersonal.setApmaPers(apmaString);
                    agregarPersonal.setPesoPers(Double.parseDouble(pesoString));
                    agregarPersonal.setLogiPers(logi);
                    agregarPersonal.setPassPers(logi);
                    agregarPersonal.setCodiTipo(Integer.parseInt(rol));

                    agregarPersonal.setFechNaciPers(fechaNacimientoPers);

                    try {
                        perDAO.create(agregarPersonal);
                        out.print("{\"resultado\":\"ok\"}");
                    } catch (Exception e) {
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
