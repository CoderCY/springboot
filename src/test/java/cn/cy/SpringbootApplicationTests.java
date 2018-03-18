package cn.cy;

import cn.cy.dao.inter.UserInfoInter;
import cn.cy.dao.inter.demo.TestOneInter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

	@Autowired
	private UserInfoInter userInfoInter;

	@Autowired
	private TestOneInter userInfoRepository;

	@Test
	public void contextLoads() {
		List<Map<String, String>> list = userInfoInter.userList();
		System.out.println(list.size());
		List<Map<String, String>> listOne = userInfoRepository.userList("dbone");
		System.out.println(listOne.size());
		List<Map<String, String>> listTwo = userInfoRepository.userList("dbtwo");
		System.out.println(listTwo.size());
	}

}
