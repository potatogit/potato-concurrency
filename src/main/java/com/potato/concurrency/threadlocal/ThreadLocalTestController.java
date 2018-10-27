package com.potato.concurrency.threadlocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/threadlocal")
public class ThreadLocalTestController {

	@RequestMapping("/test")
	@ResponseBody
	public long test() {
		return RequestHolder.getId();
	}
}
