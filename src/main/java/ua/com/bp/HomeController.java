package ua.com.bp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ua.com.bp.model.PCcontent;
import ua.com.bp.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
//	TODO: ExceptionProcessor! Handle all exceptions.
	
//	public static String adress="http://localhost:8080/my-WiStore";// //SpringA
//	public static String adressImg="http://localhost:8080";
	public static String adress="http://"+PCinfo.getLocalIP()+":8080/my-WiStore";// //SpringA
//	public static String adressImg="http://"+PCinfo.getLocalIP()+":8080";

//	for testing on jetty plugin.
//	mvn jetty:run
//	public static String adress="http://localhost:7777";
//	public static String adressImg="http://localhost:7777";
	
	private PCcontent pccontent=new PCcontent();////
	private MakeHTML makeHTML = new MakeHTML();
	
	public static String pathToTempDir="../temp/";
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome home!!!");
		model.addAttribute("rootItems", pccontent.getRootList());
//		тут возвращаем на страницу со списком всех доступных дисков
		return "root";
	}
	
	@RequestMapping(value = "/dir", method = RequestMethod.GET)
	public String dir(@RequestParam("dirList") String param, Model model) {
		logger.info("Welcome dir:"+param);
		model.addAttribute("path",param);
		model.addAttribute("curFolder",param);
		model.addAttribute("uplAdress",adress+"/ulHttp");
		model.addAttribute("serverAdress",makeHTML.makeHrefHome(adress));
		model.addAttribute("dirItems", pccontent.getDirList(param));
//		тут возвращаем страницу со списком всех файлов и папок по текущему пути(принимаем его в параметре)
		return "dirList";
	}
	
	@RequestMapping(value = "/dlHttp", method = RequestMethod.GET)
	public HttpServletResponse dlHttp(@RequestParam("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) {
		logger.info("Welcome dlHttp, fileName="+fileName);
		Download download = new DlHttp();
		response = download.dlHttp(fileName, response, request);
//		1.добавить проверку кодировки и корректности имени файла
//		в окне "куда сохранить файл"
//		2.выгружаем файл
		return response;
	}
	
	@RequestMapping(value = "/ulHttp", method = RequestMethod.POST)
	public String ulHttp(HttpServletRequest request, HttpServletResponse response, Model model) {
		logger.info("Welcome ulHttp!");
		Upload u = new UlHttp();
		u.ulHttp(request, response);
//		1.при загрузке обязательно проверяем куда пытаемся сохранить файл!!!(можно ли туда сохранять?!)
//		2.загружаем файл, переходим по ссылке, что б обновилась страница
		return dir(u.getUploadPath(),model);
	}
	
}