package br.com.qi.movies;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class MoviesApplicationTests {

	@Test
	void contextLoads() {
		assertTrue(true, "O contexto da aplicação foi carregado com sucesso.");
	}

}
