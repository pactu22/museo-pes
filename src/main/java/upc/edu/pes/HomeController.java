package upc.edu.pes;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		JSONParser parser=new JSONParser();
		
		RestTemplate restTemplate = new RestTemplate();

		String json = restTemplate.getForObject("http://hackingnews.herokuapp.com/rest/news/users", String.class);
		 // json
		JSONArray jArray;
		try {
			jArray = (JSONArray) parser.parse(json);
			for (int i=0;i<jArray.size();i++) {
			    JSONObject jsonObject = (JSONObject) jArray.get(i);
			    System.out.println(jsonObject.get("username"));
			    System.out.println("*********");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//model.addAttribute("res", jArray);
		return "home";
	}
	
}
