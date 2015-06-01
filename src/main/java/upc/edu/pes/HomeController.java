package upc.edu.pes;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
	JSONParser parser=new JSONParser();
	
	RestTemplate restTemplate = new RestTemplate();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listadoAllObras(Model model) {
		
		System.out.println("Home: punto de entrada - Lista todas las obras existentes");

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
	
	@RequestMapping(value = "/consultarObra", method = RequestMethod.POST)
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
		
		String json =restTemplate.getForObject("http://museo-project.herokuapp.com/rest/museos/Museo Principal/autoresycolecciones", String.class);
		JSONObject jsonO;
		JSONArray jArrayAutores = null;
		JSONArray jArrayColecciones = null;
		try {
			jsonO = (JSONObject) parser.parse(json);
			jArrayAutores = (JSONArray)jsonO.get("autores");
			System.out.println("AUTORES: " + jArrayAutores.toString());
			
			jArrayColecciones = (JSONArray)jsonO.get("colecciones");
			System.out.println("COLS: " + jArrayColecciones.toString());
			List<String> colecciones = new ArrayList<String>();
			for (int i=0;i<jArrayColecciones.size();i++) {
				jsonO = (JSONObject) jArrayColecciones.get(i);
			    colecciones.add(jsonO.get("nombre").toString());
			}
			model.addAttribute("colecciones", colecciones);
			
			List<Autor> autores = new ArrayList<Autor>();
			for (int i=0;i<jArrayAutores.size();i++) {
			    jsonO = (JSONObject) jArrayAutores.get(i);
			    Autor aut= new Autor ((Long)jsonO.get("id"), jsonO.get("nombre").toString() + " " +jsonO.get("apellidos").toString() );
			    autores.add(aut);
			}
			
			model.addAttribute("autores", autores);
			
			model.addAttribute("obra", new Obra()); // le pasamos el objeto principal vacio
				
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
			
		return "formObra";
	}
	
	@RequestMapping(value = "/nuevaObra", method=RequestMethod.POST)
	public String doSubmit(Obra obra) {  // Ahora pasamos el objeto Obra
	
		
		System.out.println(" " + obra.getTitulo() + " " + obra.getBeacon() + " " +obra.getAutorId()+ " " 
				+obra.getInformacion()+ " " +obra.getEstilo()+ " " + obra.getColeccion());
	
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("titulo", obra.getTitulo());
		jsonObject.put("idBeacon", obra.getBeacon());
		jsonObject.put("estilo", obra.getEstilo());
		jsonObject.put("idAutor", obra.getAutorId());
		jsonObject.put("informacion", obra.getInformacion());
		
		if (!"Ninguna".equals(obra.getColeccion())) {
			jsonObject.put("nombreColeccion", obra.getColeccion());
		}
		
		
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
	
	
	@RequestMapping(value = "/showEditarObra", method = RequestMethod.POST)
	public String editarObra( @RequestParam("idObra") String idObra,  Model model) {
		idObraGlobal = Long.parseLong(idObra);
		
		String json =restTemplate.getForObject("http://museo-project.herokuapp.com/rest/museos/Museo Principal/autoresycolecciones", String.class);
		JSONObject jsonO;
		JSONArray jArrayAutores = null;
		JSONArray jArrayColecciones = null;
		try {
			jsonO = (JSONObject) parser.parse(json);
			jArrayAutores = (JSONArray)jsonO.get("autores");
			System.out.println("AUTORES: " + jArrayAutores.toString());
			
			jArrayColecciones = (JSONArray)jsonO.get("colecciones");
			System.out.println("COLS: " + jArrayColecciones.toString());
			List<String> colecciones = new ArrayList<String>();
			for (int i=0;i<jArrayColecciones.size();i++) {
				jsonO = (JSONObject) jArrayColecciones.get(i);
			    colecciones.add(jsonO.get("nombre").toString());
			}
			model.addAttribute("colecciones", colecciones);
			
			List<Autor> autores = new ArrayList<Autor>();
			for (int i=0;i<jArrayAutores.size();i++) {
			    jsonO = (JSONObject) jArrayAutores.get(i);
			    Autor aut= new Autor ((Long)jsonO.get("id"), jsonO.get("nombre").toString() + " " +jsonO.get("apellidos").toString() );
			    autores.add(aut);
			}
			model.addAttribute("autores", autores);
				
		} catch (ParseException e) {
			e.printStackTrace();
		}
		

		json = restTemplate.getForObject("http://museo-project.herokuapp.com/rest/museos/Museo Principal/obras/"+idObra, String.class);
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(json);
			JSONObject autor = (JSONObject) jsonObject.get("autor");
			Autor autorO = new Autor(Long.parseLong(autor.get("id").toString()), autor.get("nombre").toString() +" " + autor.get("apellidos").toString());
			   
			Obra obra = new Obra(jsonObject.get("titulo").toString(), autorO,
					((Long)jsonObject.get("beacon")),jsonObject.get("informacion").toString(), jsonObject.get("estilo").toString());
			
			obra.setAutorId(autorO.getId());  // esto es importante
			
			JSONObject coleccion = (JSONObject) jsonObject.get("coleccion");
			if(coleccion != null){
				String col = coleccion.get("nombre").toString();
				obra.setColeccion(col);
			}
			else obra.setColeccion("Ninguna");
			System.out.println("EDITANDO: "+ obra.toString());
			model.addAttribute("obra", obra);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "editarObra";
	}
	
	
	@RequestMapping(value = "/editarObra", method=RequestMethod.POST)
	public String doEdit(Obra obra) {  // Ahora pasamos el objeto Obra
		System.out.println("idGLOB: " + idObraGlobal);
		
		/*
		String titulo =request.getParameter("titulo");
		String beacon =request.getParameter("beacon");
		String info =request.getParameter("info");
		String estilo =request.getParameter("estilo");
		String coleccion =request.getParameter("coleccion");
		String autor = request.getParameter("autor");
		*/
		
		JSONObject jsonObject = new JSONObject();
		
		
		jsonObject.put("titulo", obra.getTitulo());
		jsonObject.put("idBeacon", obra.getBeacon());
		jsonObject.put("estilo", obra.getEstilo());
		jsonObject.put("idAutor", obra.getAutorId());
		jsonObject.put("informacion", obra.getInformacion());
		
		if (!"Ninguna".equals(obra.getColeccion())) {
			jsonObject.put("nombreColeccion", obra.getColeccion());
		}
		
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
	
	
	@RequestMapping(value = "/borrarObra", method = RequestMethod.POST)
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
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getRegistrationFrom(Model model){
		model.addAttribute("obra", new Obra());

		return "registrationForm";
	}
	@RequestMapping(value = "newObra", method = RequestMethod.POST)
	public String postRegistrationForm(Obra form, Model model) {
		System.out.println("ALGOO");
		model.addAttribute("titulo", form.getTitulo());
        model.addAttribute("autor", "autor de prueba");
        model.addAttribute("estilo", form.getEstilo());
        model.addAttribute("coleccion", form.getColeccion());
		return "registration_ok";
	}
	 
	
	
}
