package ru.inpris.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by E_Torkhov on 19.02.2016.
 *
 */
public class renderXML extends HttpServlet {
    private final static int BUFSIZE = 65536;
    private final static String WAR_PATH = System.getProperty("REPORT_PATH", "/tmp/pmh_reports");
    private final static String ENCODING = "UTF-8";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet( request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


       File file = new File(request.getRequestURI());
        System.out.println("renderXML: '"+ file.getName()+"'");
        String name = WAR_PATH + "/" + file.getName();
        String path = file.getPath();
        //InputStream is = this.getClass().getResourceAsStream( WAR_PATH + file.getName());		//InputStream is = this.getClass().getResourceAsStream( WAR_PATH + file.getName());
        InputStream is = null;
        try {
            is = new FileInputStream("D://tmp//groundwaterList.xml");        //InputStream is = this.getClass().getResourceAsStream( WAR_PATH + file.getName());

            byte[] buf = new byte[BUFSIZE];
            int len;
            OutputStream os = response.getOutputStream();

            response.setContentType("application/vnd.ms-excel");

            while ((len = is.read(buf)) > 0) {
                String line = new String(buf, 0, len, ENCODING);
                os.write(line.getBytes(ENCODING));
            }
        } finally {
            if ( is != null ) {
                is.close();
            }
        }
        File f = new File(WAR_PATH + "\\" + file.getName());
        //	f.delete();
    }}
