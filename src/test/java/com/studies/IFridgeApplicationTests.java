package com.studies;

import com.studies.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class IFridgeApplicationTests {

	@Test
	public void contextLoads() {
	}

}
