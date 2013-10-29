import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;
////////////////////
import java.net.*;
import java.io.*;
////////////////////
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

///////////////////
public class HelloWorld extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
     
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

      //  String url = "jdbc:mysql://localhost:3306/testdb";
        String url =  "jdbc:mysql://addons-sso.heroku.com/apps/isw2unsa2013/addons/cleardb:ignite"
        String user = "b2cd9b7818f84e";
        String password = "8cd6dee1";

        try {
            con = DriverManager.getConnection(url, user, password);
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()");

            if (rs.next()) {
                System.out.println(rs.getString(1));
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(Version.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);

        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(Version.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
        }





                ////////



            try {
                  // Indicamos la URL donde nos conectamos
//                  URL url = new URL("http://weather.yahooapis.com/forecastrss?w=418442");
                  URL url = new URL("http://weather.yahooapis.com/forecastrss?w=418442");
                  // Buffer con los datos recibidos
                  BufferedReader in = null;
               
                  try {
                        // Volcamos lo recibido al buffer
                        in = new BufferedReader(new InputStreamReader(
                        url.openStream()));
                  } 
                  catch(Throwable t){}

                  // Transformamos el contenido del buffer a texto
                  String inputLine;
                  String inputText="";

                  // Mientras haya cosas en el buffer las volcamos a las
                  // cadenas de texto 
                  while ((inputLine = in.readLine()) != null)
                  {
                    inputText = inputText + inputLine;
                  }

                  // Mostramos el contenido y cerramos la entrada
                  //System.out.println("\n\nEl contenido de la URL es: \n\n" + inputText);
                  
                  //System.out.println(inputText.indexOf("temp=\""));
                  String temp = inputText.substring(inputText.indexOf("temp=\""), inputText.indexOf("temp=\"")+10);
                 // System.out.println("Temperatura en Arequipa"+temp);
                


                  String example="<!DOCTYPE html><html><body><form action=\"demo_form.asp\" method=\"get\"><input list=\"browsers\" name=\"browser\"><datalist id=\"browsers\"><option value=\"Internet Explorer\"><option value=\"Firefox\"><option value=\"Chrome\"><option value=\"Opera\"><option value=\"Safari\"></datalist><input type=\"submit\"></form><p><b>Temperatura en Arequipa "+ temp+"</p></body></html>";

                  //"<!DOCTYPE HTML><html><head><style type=\"text/css\">#div1 {width:350px;height:70px;padding:10px;border:1px solid #aaaaaa;}</style><script>function allowDrop(ev){ev.preventDefault();}function drag(ev){ev.dataTransfer.setData(\"Text\",ev.target.id);}function drop(ev){ev.preventDefault();var data=ev.dataTransfer.getData(\"Text\");ev.target.appendChild(document.getElementById(data));}</script></head><body><p>Drag the W3Schools image into the rectangle:</p><div id=\"div1\" ondrop=\"drop(event)\" ondragover=\"allowDrop(event)\"></div><br><img id=\"drag1\" src=\"img_logo.gif\" draggable=\"true\" ondragstart=\"drag(event)\" width=\"336\" height=\"69\"></body></html>";

                  resp.getWriter().print(example);
                 // resp.getWriter().print(inputText);
                  in.close();
               
            } catch (MalformedURLException me) {
              System.out.println("URL erronea");
            } catch (IOException ioe) {
              System.out.println("Error IO");
            }
                ////////




       // resp.getWriter().print("Ingenieria de Software 2 2 is \n UNSA!\n"+inputText);
    }

    public static void main(String[] args) throws Exception{
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloWorld()),"/*");
        server.start();
        server.join();   
    }
}
