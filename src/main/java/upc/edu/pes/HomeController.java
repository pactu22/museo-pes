package upc.edu.pes;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import upc.edu.pes.model.Autor;
import upc.edu.pes.model.Obra;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private  Long idObraGlobal;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	JSONParser parser=new JSONParser();
	
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listadoAllObras( Model model) {

		String json = restTemplate.getForObject("http://museo-project.herokuapp.com/rest/museos/Museo Principal/obras", String.class);
		System.out.println("JSON: " + json);
		// json
		JSONArray jArray;
		List<Obra> obras = new ArrayList<Obra>();
		try {
			jArray = (JSONArray) parser.parse(json);
			for (int i=0;i<jArray.size();i++) {
			    JSONObject jsonObject = (JSONObject) jArray.get(i);
			    JSONObject autor = (JSONObject) jsonObject.get("autor");
			    Autor autorO = new Autor(Long.parseLong(autor.get("id").toString()), autor.get("nombre").toString() +" " + autor.get("apellidos").toString());
			    Obra o = new Obra(((Long)jsonObject.get("id")), jsonObject.get("titulo").toString(), autorO );
			    obras.add(o);
			    System.out.println("*********" + o.toString());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("obras", obras);
		return "listadoObras";
	}
	
	@RequestMapping(value = "/consultarObra", method = RequestMethod.GET)
	public String getObra( @RequestParam("idObra") String idObra,  Model model) {
		System.out.println("ID: " + idObra);
		String json = restTemplate.getForObject("http://museo-project.herokuapp.com/rest/museos/Museo Principal/obras/"+idObra, String.class);
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(json);
			JSONObject autor = (JSONObject) jsonObject.get("autor");
			Autor autorO = new Autor(Long.parseLong(autor.get("id").toString()), autor.get("nombre").toString() +" " + autor.get("apellidos").toString());
		    
			Obra o = new Obra(jsonObject.get("titulo").toString(), autorO,
					((Long)jsonObject.get("beacon")),jsonObject.get("informacion").toString(), jsonObject.get("estilo").toString());
			JSONObject coleccion = (JSONObject) jsonObject.get("coleccion");
			if(coleccion != null) o.setColeccion(coleccion.get("nombre").toString());
			System.out.println(o.toString());
			model.addAttribute("obra", o);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "obra";
	}
	@RequestMapping(value = "/showNuevaObra", method = RequestMethod.GET)
	public String nuevaObra(Model model) {
		String json = restTemplate.getForObject("http://museo-project.herokuapp.com/rest/museos/Museo Principal/colecciones", String.class);
		JSONObject jsonObject = null;
		JSONArray jArray;
		List<String> colecciones = new ArrayList<String>();
		try {
			jArray = (JSONArray) parser.parse(json);
			for (int i=0;i<jArray.size();i++) {
			    jsonObject = (JSONObject) jArray.get(i);
			    colecciones.add(jsonObject.get("nombre").toString());
			}
		}catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("SIZE: COL " + colecciones.size());
		
		model.addAttribute("colecciones", colecciones);
		
		//OTRA LLAMADA
		json = restTemplate.getForObject("http://museo-project.herokuapp.com/rest/autores", String.class);
		jsonObject = null;
		
		List<Autor> autores = new ArrayList<Autor>();
		try {
		jArray = (JSONArray) parser.parse(json);
			for (int i=0;i<jArray.size();i++) {
			    jsonObject = (JSONObject) jArray.get(i);
			    Autor aut= new Autor ((Long)jsonObject.get("id"), jsonObject.get("nombre").toString() + " " +jsonObject.get("apellidos").toString() );
			    autores.add(aut);
			}
		}catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("autores", autores);
		return "formObra";
	}
	
	@RequestMapping(value = "/nuevaObra", method=RequestMethod.POST)
	public String doSubmit(HttpServletRequest request, Model model, HttpSession session) {
		String titulo =request.getParameter("titulo");
		String beacon =request.getParameter("beacon");
		String autor =request.getParameter("autor");
		String info =request.getParameter("info");
		String estilo =request.getParameter("estilo");
		String coleccion =request.getParameter("coleccion");
		System.out.println(" " + titulo + " " +beacon + " " +autor+ " " +info+ " " +estilo+ " " +coleccion);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("titulo", titulo);
		jsonObject.put("idBeacon", Long.parseLong(beacon));
		jsonObject.put("estilo", estilo);
		jsonObject.put("idAutor", 1);
		if(!coleccion.equals("Ninguna"))jsonObject.put("nombreColeccion", coleccion);
		jsonObject.put("informacion", info);
		
		String url = "http://museo-project.herokuapp.com/rest/museos/Museo Principal/obras";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
		System.out.println("JSON: " + jsonObject.toJSONString());
		ResponseEntity<String> loginResponse = restTemplate
				  .exchange(url, HttpMethod.POST, entity, String.class);
		
		if (loginResponse.getStatusCode() == HttpStatus.OK) {
				} else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
					System.out.println("TODO MAL");
				}
				
		
		return  "redirect:/";
	}
	@RequestMapping(value = "/showEditarObra", method = RequestMethod.GET)
	public String editarObra( @RequestParam("idObra") String idObra,  Model model) {
		idObraGlobal = Long.parseLong(idObra);
		String json = restTemplate.getForObject("http://museo-project.herokuapp.com/rest/museos/Museo Principal/colecciones", String.class);
		JSONObject jsonObject = null;
		JSONArray jArray;
		List<String> colecciones = new ArrayList<String>();
		try {
			jArray = (JSONArray) parser.parse(json);
			for (int i=0;i<jArray.size();i++) {
			    jsonObject = (JSONObject) jArray.get(i);
			    colecciones.add(jsonObject.get("nombre").toString());
			}
		}catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("SIZE: " + colecciones.size());
		
		model.addAttribute("colecciones", colecciones);
		
		
		System.out.println("ID: " + idObra);
		json = restTemplate.getForObject("http://museo-project.herokuapp.com/rest/museos/Museo Principal/obras/"+idObra, String.class);
		jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(json);
			JSONObject autor = (JSONObject) jsonObject.get("autor");
			Autor autorO = new Autor(Long.parseLong(autor.get("id").toString()), autor.get("nombre").toString() +" " + autor.get("apellidos").toString());
			   
			Obra o = new Obra(jsonObject.get("titulo").toString(), autorO,
					((Long)jsonObject.get("beacon")),jsonObject.get("informacion").toString(), jsonObject.get("estilo").toString());
			Object coleccion = jsonObject.get("coleccion");
			if(coleccion != null) o.setColeccion(coleccion.toString());
			System.out.println("COL: "+o.getColeccion());
			model.addAttribute("obra", o);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
	
		return "editarObra";
	}
	@RequestMapping(value = "/borrarObra", method = RequestMethod.GET)
	public String borrarObra( @RequestParam("idObra") String idObra,  Model model) {
		System.out.println("ID: " + idObra);
		
		String url = "http://museo-project.herokuapp.com/rest/museos/Museo Principal/obras/"+idObra+"/borrar";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<String> loginResponse = restTemplate
				  .exchange(url, HttpMethod.POST, null, String.class);
		
		if (loginResponse.getStatusCode() == HttpStatus.OK) {
				  System.out.println("TODO BIEN" );
				} else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				  // nono... bad credentials
					System.out.println("TODO MAL");
			}
				
		return "redirect:/";
	}

	
	@RequestMapping(value = "/editarObra", method=RequestMethod.POST)
	public String doEdit(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("idGLOB: " + idObraGlobal);
		String titulo =request.getParameter("titulo");
		String beacon =request.getParameter("beacon");
		String info =request.getParameter("info");
		String estilo =request.getParameter("estilo");
		String coleccion =request.getParameter("coleccion");
		String autor = request.getParameter("autor");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("titulo", titulo);
		jsonObject.put("idBeacon", Long.parseLong(beacon));
		jsonObject.put("estilo", estilo);
		jsonObject.put("idAutor", Long.parseLong(autor));
		if(!coleccion.equals("Ninguna"))jsonObject.put("nombreColeccion", coleccion);
		jsonObject.put("informacion", info);
		
		String url = "http://museo-project.herokuapp.com/rest/museos/Museo Principal/obras/"+idObraGlobal;
		System.out.println("URL: " +url);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);
		System.out.println("JSON: " + jsonObject.toJSONString());
		ResponseEntity<String> loginResponse = restTemplate
				  .exchange(url, HttpMethod.PUT, entity, String.class);
		
		if (loginResponse.getStatusCode() == HttpStatus.OK) {
				 // System.out.println("TODO BIEN" + loginResponse.getBody().toString());
				} else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				  // nono... bad credentials
					System.out.println("TODO MAL");
				}
				
		
		return "redirect:/";
	}
	
	
	
}
