package enginePackage.server;

import enginePackage.client.GreetingService;
import enginePackage.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


@SuppressWarnings("serial")
public class ContenidosServiceImpl extends RemoteServiceServlet  implements
GreetingService  {

	public String greetServer(String input) throws IllegalArgumentException {
		//Aqui carga el contenido del servidor
		return ContenidosServer(input);
	}
	
	
	public String ContenidosServer(String input){
		HttpServletResponse response = (HttpServletResponse) new ServResponse();
		//HttpServletResponse response = new HttpServletResponse();
			try {
				doGet( response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//Obtenir carpeta "input"
		//llegir xml per veure continguts
		
		//gestionar tot =S
		return "";
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

	        }

	        catch(Exception e){
	            System.out.println(e);
	        }
	    }
	
	public String get_contenido(String content){
		
		return "";
	}
	@Override
	public String doGet(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
