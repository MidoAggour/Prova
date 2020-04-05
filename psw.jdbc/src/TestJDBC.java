import java.sql.*;
import java.sql.DriverManager;
public class TestJDBC {
    public static void main(String args[]){
        org.postgresql.Driver drv;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
         Connection  con =null;
        System.out.println("Ciao a tutti");
        String url="jdbc:postgresql://localhost:5432/psw";
        String utente="postgres";
        String psw="admin";

        try {
          con=  DriverManager.getConnection(url,utente,psw);
            Statement st=con.createStatement();
            st.executeUpdate("insert into cliente values (3 , 'ciao bello')");
            ResultSet rs=st.executeQuery( "select * from cliente");
            while(rs.next()) {
                System.out.println("id: " + rs.getInt(1) + "nome: " + rs.getString(2));
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
                if(con!=null) {
                    try {
                        con.close();

                    }catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

