package psw.servlet;

import psw.models.Cliente;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "clientiListServlet", urlPatterns = {"/clienti"})
public class clientiListServlet extends HttpServlet {

    @Resource(mappedName = "java:/ProvaPostgresDS")
    DataSource db;

    Connection connection=null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            connection = db.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente");
            List<Cliente> lc= new LinkedList<Cliente>();
            while (rs.next()) {
                Cliente c= new Cliente();
                c.setId(rs.getInt(1));
                c.setNome(rs.getString(2));
                lc.add(c);
            }
            rs.close();
            st.close();
            request.setAttribute("clienti",lc);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            RequestDispatcher rd= request.getRequestDispatcher("/clienti.jsp");
            rd.forward(request,response);
        }

    }

}
