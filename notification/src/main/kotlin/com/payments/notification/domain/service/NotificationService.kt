package com.payments.notification.domain.service

import com.payments.notification.domain.model.Transaction
import com.payments.notification.domain.port.output.NotificationProcessor
import org.slf4j.LoggerFactory

class NotificationService(
    val notificationProcessor: NotificationProcessor
) {
    fun process(transaction: Transaction) {
        log.info("Send message to Kafka - {}", transaction)
        notificationProcessor.notify(transaction)
    }

    private val log = LoggerFactory.getLogger(NotificationService::class.java)
}