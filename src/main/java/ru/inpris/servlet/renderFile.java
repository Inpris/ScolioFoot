package ru.inpris.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by teo on 19.02.16.
 *
 */
public class renderFile extends HttpServlet {

//    public void getLoginFailed() throws IOException{
//        FacesContext context = FacesContext.getCurrentInstance();
//        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//        HttpSession se = request.getSession();
//
//        if (se.getAttribute("isLoggedIn").equals(false)) {
//            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//
//            PrintWriter wb = response.getWriter();
//            wb.write("<body bgcolor=\"#D0F5A9\">");
//            wb.write("<p align=\"center\">Login failed.</p>");
//            wb.write("</body>");
//        }
//    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		try {
//			boolean isAdmin = request.isUserInRole("admin");
//			boolean isUser = request.isUserInRole("user");
//			log.debug("This is admin: " + isAdmin + ", is user: " + isUser);
//
//			String guR = request.getRequestURI();
//			log.debug("This is guR: " + guR);
//			String guP = request.getParameter("rep_name");
//			log.debug("This is parameter rep_name: " + guP);
//
//			response.addHeader("Content-Type", "text/html");  // ставим заголовок до вывода контента
//
//			if (guP.equalsIgnoreCase("boreholeLayerReport")) {
//				log.trace("boreholeLayerReport...");
//				String project = request.getParameter("project");
//				log.debug("This is parameter project: " + project);
//                if (project.equals("0")){
//                    PrintWriter wb = response.getWriter();
//                    wb.write("<body bgcolor=\"#D0F5A9\">");
//                    wb.write("<p align=\"center\">Project not selected.</p>");
//                    wb.write("</body>");
//                }else {
//				boreholeLayerReport.buildReport(project, response.getOutputStream());}
//			}
//			else if (guP.equalsIgnoreCase("catalogProjectReport")) {
//				log.trace("catalogProjectController...");
//				String project = request.getParameter("project");
//
//                if (project.equals("0")){
//                    PrintWriter wb = response.getWriter();
//                    wb.write("<body bgcolor=\"#D0F5A9\">");
//                    wb.write("<p align=\"center\">Project not selected.</p>");
//                    wb.write("</body>");
//                }else {
//                    catalogProjectReport.buildReport(project, response.getOutputStream());}
//			}
//			else if (guP.equalsIgnoreCase("groundwaterList")) {
//				String _dateStart = request.getParameter("dateStart");
//				log.debug("This is parameter dateStart: " + _dateStart);
//				String _dateEnd = request.getParameter("dateEnd");
//				log.debug("This is parameter dateEnd: " + _dateEnd);
//
//				log.trace("groundwaterList...");
//				groundWaterReport.buildReport(_dateStart, _dateEnd, response.getOutputStream());
//			}
//			else if (guP.equalsIgnoreCase("boreholeCatalog")) {
//				String _dateStart = request.getParameter("dateStart");
//				log.debug("This is parameter dateStart: " + _dateStart);
//				String _dateEnd = request.getParameter("dateEnd");
//				log.debug("This is parameter dateEnd: " + _dateEnd);
//
//				log.trace("boreholeCatalog...");
//				boreholeCatalog.buildReport(_dateStart, _dateEnd, response.getOutputStream());
//			} else {
//				// какой-то новый репорт?
//				PrintWriter wb = response.getWriter();
//				wb.write("<body>");
//				wb.write("Отчет не известен: ");
//				wb.write(guP);
//				wb.write("</body>");
//			}
//
//		} catch (SQLException e) {
//			log.error("SQLException", e);
//		} catch (ParseException e) {
//			log.error("ParseException", e);
//		} catch (URISyntaxException e) {
//			log.error("URISyntaxException", e);
//		}
	}
}