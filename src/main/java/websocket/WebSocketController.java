package websocket;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {
	
	@RequestMapping("/index")
	public String index(){
		return "chat/client";
	}
}
