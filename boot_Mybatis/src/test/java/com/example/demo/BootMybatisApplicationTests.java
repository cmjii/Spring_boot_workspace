package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.BoardVO;
import com.example.demo.repository.BoardMapper;


//BoardVO에 builder어노테이션 주입
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootMybatisApplication.class)
class BootMybatisApplicationTests {
	@Autowired
	private BoardMapper mapper;
	
	@Test
	void contextLoads() {
		//BoardVO를 생성하겠습니다.
		for(int i=0; i<300; i++) {			
			BoardVO bvo = BoardVO.builder().title("Title"+i)
					.writer("tester@test.com")
					.content("Test Content")
					.build();
			mapper.insert(bvo);
		}
		
	}

}
