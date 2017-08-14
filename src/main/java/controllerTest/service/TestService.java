package controllerTest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(isolation=Isolation.READ_COMMITTED)
public class TestService {
	public void test(){
		System.out.println("-------------this is a test----------------");
	}
}
