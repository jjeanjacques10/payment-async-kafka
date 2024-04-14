package com.payments.notification.adapter.input.kafka

import com.payment.TransactionItem
import com.payments.notification.domain.service.NotificationService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class PaymentKafkaConsumer(
    val notificationService: NotificationService
) {

    @KafkaListener(topics = ["payment-topic"], containerFactory = "kafkaListenerContainerFactory")
    fun consumePayment(
        @Payload transaction: TransactionItem,
        @Header(KafkaHeaders.OFFSET) offset: Long,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int?,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String?,
        ack: Acknowledgment
    ) {
        try {
            log.info("Consume Kafka message - topic: {}, offset: {}, partition: {}", topic, offset, partition)
            // notificationService.process(transaction)
        } catch (ex: Exception) {
            log.error("Error processing message: {}", ex.message, ex)
        } finally {
            ack.acknowledge()
        }
    }

    private val log = LoggerFactory.getLogger(PaymentKafkaConsumer::class.java)
}