package com.payments.pixprocessor.domain.service

import com.payments.pixprocessor.domain.model.Transaction
import com.payments.pixprocessor.domain.port.output.MessageProducer
import org.slf4j.LoggerFactory

class PixService(
    val messageProducer: MessageProducer
) {
    fun process(transaction: Transaction) {
        log.info("Send message to Kafka - {}", transaction)
        messageProducer.send(transaction)
    }

    private val log = LoggerFactory.getLogger(PixService::class.java)
}