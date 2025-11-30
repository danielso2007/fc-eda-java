package com.estudoeda.balances.infrastructure.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.estudoeda.balances.application.domain.event.BalanceUpdatedWalletEvent;
import com.estudoeda.balances.application.usercase.BalanceUserCase;

@Component
public class BalanceEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(BalanceEventConsumer.class);

    private final BalanceUserCase balanceUserCase;

    public BalanceEventConsumer(final BalanceUserCase balanceUserCase) {
        this.balanceUserCase = balanceUserCase;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group.id.config}", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(final BalanceUpdatedWalletEvent balanceUpdatedWalletEvent) {
        if (logger.isInfoEnabled()) {
            logger.info("Balanço recebido da transação: {} | Conta: {}", balanceUpdatedWalletEvent.getTransactionId(),
                    balanceUpdatedWalletEvent.getAccountId());
        }
        balanceUserCase.receberTransacao(balanceUpdatedWalletEvent);
    }
}
