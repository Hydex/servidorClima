import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;
////////////////////
import java.net.*;
import java.io.*;
////////////////////
public class HelloWorld extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

                ////////

            try {
                  // Indicamos la URL donde nos conectamos
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
                  System.out.println("El contenido de la URL es: " + inputText);
                  resp.getWriter().print(inputText);
                  in.close();
               
            } catch (MalformedURLException me) {
              System.out.println("URL erronea");
            } catch (IOException ioe) {
              System.out.println("Error IO");
            }
                ////////




        resp.getWriter().print("Ingenieria de Software 2 2 is \n UNSA!\n");
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
