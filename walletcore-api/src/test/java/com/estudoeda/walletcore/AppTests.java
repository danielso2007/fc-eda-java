package com.estudoeda.walletcore;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import com.estudoeda.walletcore.infrastructure.persistence.mapper.AccountMapper;
import com.estudoeda.walletcore.infrastructure.persistence.mapper.ClientMapper;
import com.estudoeda.walletcore.infrastructure.persistence.mapper.TransactionMapper;
import com.estudoeda.walletcore.interfaces.adapters.mapper.AccountAdapterMapper;
import com.estudoeda.walletcore.interfaces.adapters.mapper.ClientAdapterMapper;
import com.estudoeda.walletcore.interfaces.adapters.mapper.TransactionAdapterMapper;

@SpringBootTest
@ActiveProfiles("test")
class AppTests {

	@MockitoBean
	private AccountMapper accountMapper;

	@MockitoBean
	private ClientMapper clientMapper;

	@MockitoBean
	private TransactionMapper transactionMapper;

	@MockitoBean
	private AccountAdapterMapper accountAdapterMapper;

	@MockitoBean
	private ClientAdapterMapper clientAdapterMapper;

	@MockitoBean
	private TransactionAdapterMapper transactionAdapterMapper;

	// @Test
	// void contextLoads() {
	// }

}
