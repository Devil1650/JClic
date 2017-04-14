package enginePackage.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import enginePackage.client.GreetingService;
import enginePackage.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		// Verify that the input is valid. 
		/*if (!FieldVerifier.isValidName(input)) {
			// If the input is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(
					"Name must be at least 4 characters long");
		}*/
//System.out.print(input);
		String serverInfo = getServletContext().getServerInfo();
		String userAgent = getThreadLocalRequest().getHeader("User-Agent");

		// Escape data from the client to avoid cross-site script vulnerabilities.
		input = escapeHtml(input);
		userAgent = escapeHtml(userAgent);

		//TODO: resposta del servidor 
		/*return "Hello, " + input + "!<br><br>I am running " + serverInfo
				+ ".<br><br>It looks like you are using:<br>" + userAgent;
		*/
		String contenedor = null;
		if (input.equals("triangles"))
			contenedor = "<div style=\"width:560;text-align:center;font-size:x-small;\"><iframe src=\"http://clic.xtec.cat/db/jclicApplet.jsp?project=http://clic.xtec.cat/projects/triangul/jclic/triangul.jclic.zip&skin=@mini.xml&lang=ca\" frameborder=0 width=760 height=640 scrolling=\"no\">		</iframe><br><a href=\"http://clic.xtec.cat/db/act_ca.jsp?id=3931\">zonaClic - activitats - Los triángulos</a>	</div>";
		else if(input.equals("seres_vivos"))
			contenedor = "<div style=\"width:560;text-align:center;font-size:x-small;\"><iframe src=\"http://clic.xtec.cat/db/jclicApplet.jsp?project=https://clic.xtec.cat/projects/serviv/jclic/serviv.jclic.zip&lang=es\" frameborder=2 width=760 height=640 scrolling=\"no\">		</iframe><br><a href=\"http://clic.xtec.cat/db/act_ca.jsp?id=3931\">zonaClic - activitats - Los triángulos</a>	</div>";
		
		
		else contenedor = "<div> Hello no triangles, imput is: "+input+"</div>";
		
		return contenedor;
		
		
		//TODO: code to get xml and then parse it
		/*
			HttpServletResponse response = (HttpServletResponse) new ServResponse();
			//HttpServletResponse response = new HttpServletResponse();
				try {
					//doGet( response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return "";*/
		
	}
	
	public boolean isTextNode(Node n){
        return n.getNodeName().equals("#text");
    }

	
	public void doGet(HttpServletResponse response) throws ServletException,IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            DocumentBuilderFactory docFactory =  DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse("/JClic/JClic_Contenidos/Triangulos/triangul.jclic"); //fichero xml
            out.println("<table border=2><tr><th>Name</th><th>Address</th></tr>");
            Element  element = doc.getDocumentElement(); 
            NodeList personNodes = element.getChildNodes(); 

            for (int i=0; i<personNodes.getLength(); i++){

                 Node emp = personNodes.item(i);
                 if (isTextNode(emp))
                 continue;

                 NodeList NameDOBCity = emp.getChildNodes(); 
                 out.println("<tr>");

                 for (int j=0; j<NameDOBCity.getLength(); j++ ){

                     Node node = NameDOBCity.item(j);
                     if ( isTextNode(node)) 
                     continue;
                    out.println("<td>"+(node.getFirstChild().getNodeValue())+"</td>");

                 } 

                 out.println("</tr>");
             }

             out.println("</table>");

        }

        catch(Exception e){
            System.out.println(e);
        }
    }

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
		}

	//@Override
	public String doGet(String name) throws IllegalArgumentException {
		HttpServletResponse response = (HttpServletResponse) new ServResponse();
		 response.setContentType("text/html");
	       
			

	        try{
	        	 PrintWriter out;
					out = response.getWriter();
	            DocumentBuilderFactory docFactory =  DocumentBuilderFactory.newInstance();
	            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	            Document doc = docBuilder.parse("\\JClic_Contenidos\\Triangulos\\triangul.jclic"); //fichero xml
	            out.println("<table border=2><tr><th>Name</th><th>Address</th></tr>");
	            Element  element = doc.getDocumentElement(); 
	            NodeList personNodes = element.getChildNodes(); 

	            for (int i=0; i<personNodes.getLength(); i++){

	                 Node emp = personNodes.item(i);
	                 if (isTextNode(emp))
	                 continue;

	                 NodeList NameDOBCity = emp.getChildNodes(); 
	                 out.println("<tr>");

	                 for (int j=0; j<NameDOBCity.getLength(); j++ ){
	                     Node node = NameDOBCity.item(j);
	                     if ( isTextNode(node)) 
	                     continue;
	                    out.println("<td>"+(node.getFirstChild().getNodeValue())+"</td>");
	                 } 

	                 out.println("</tr>");
	             }
	             out.println("</table>");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	        catch(Exception e){
	            System.out.println(e);
	        }
	        	return "";
	        
	}
}
