package com.estudoeda.walletcore;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import com.estudoeda.balances.infrastructure.persistence.mapper.BalanceMapper;

@SpringBootTest
@ActiveProfiles("test")
class AppTests {

	@MockitoBean
	private BalanceMapper accountMapper;

	// @Test
	// void contextLoads() {
	// }

}
