package psw.servlet;

import javax.annotation.Resource;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Arrays;

@WebServlet(name = "CiaoServlet", urlPatterns = {"/ciao"}, initParams = {@WebInitParam(name= "cont", value = "0")})
public class CiaoServlet extends HttpServlet {

    int cont;

    @Resource(mappedName = "java:/ProvaPostgresDS")
    DataSource db;

    public void init(){
        ServletConfig config = getServletConfig();
        cont= Integer.parseInt(config.getInitParameter("cont"));

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        cont++;
        Connection con = null;

        out.println("<HTML>" +
                "<HEAD><TITLE> Titolo </TITLE> </HEAD>" +
                "<BODY>"+"Numero di accessi: "+cont);
        out.println("<p>Request Method: "+request.getMethod()+"<p>");
        out.println("<p>Request URI: "+request.getRequestURI()+"<p>");
        out.println("<p>Request Protocol: "+request.getProtocol()+"<p>");
        out.println("<p>Request Query String : "+ request.getQueryString()+"<p>");
        out.println("<p>Primo valore di cicc: "+ request.getParameter("cicc")+"<p>");
        out.println("<p>Tutti i valori di cicc: "+ Arrays.toString(request.getParameterValues("cicc"))+"<p>");

        Cookie cc= new Cookie("cicc", request.getParameter("cicc"));
        cc.setMaxAge(100000); //valore positivo il cookie dura tanto
        //negativo appena finisce la sessione si perde

        response.addCookie(cc);

        Cookie[] vCookies = request.getCookies();
        for(Cookie cx : vCookies){
            out.println("<p>Cookie:  "+cx.getName()+" "+cx.getValue()+"<p>");
        }

        try {
            con = db.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente");
            out.println("<UL>");
            while (rs.next()) {
                out.println("<LI>");
                out.println("id: " + rs.getLong(1) + " nome: " + rs.getString(2)+ "</LI>");

            }
            out.println("</UL>");
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            out.println("</BODY>"+
                    "</HTML>");
        }



    }
}
