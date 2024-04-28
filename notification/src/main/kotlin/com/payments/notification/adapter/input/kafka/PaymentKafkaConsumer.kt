package com.payments.notification.adapter.input.kafka

import com.payment.TransactionItem
import com.payments.notification.domain.enums.KeyType
import com.payments.notification.domain.model.Destination
import com.payments.notification.domain.model.Origin
import com.payments.notification.domain.model.Transaction
import com.payments.notification.domain.service.NotificationService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.LocalDateTime

@Component
class PaymentKafkaConsumer(
    val notificationService: NotificationService
) {

    @KafkaListener(topics = ["payment-topic"], containerFactory = "kafkaListenerContainerFactory")
    fun consumePayment(
        @Payload transactionItem: TransactionItem,
        @Header(KafkaHeaders.OFFSET) offset: Long,
        @Header(KafkaHeaders.RECEIVED_PARTITION) partition: Int?,
        @Header(KafkaHeaders.RECEIVED_TOPIC) topic: String?,
        ack: Acknowledgment
    ) {
        try {
            log.info("Consume Kafka message - topic: {}, offset: {}, partition: {}", topic, offset, partition)
            notificationService.process(transactionItem.toTransaction())
        } catch (ex: Exception) {
            log.error("Error processing message: {}", ex.message, ex)
        } finally {
            ack.acknowledge()
        }
    }

    private fun TransactionItem.toTransaction() = Transaction(
        BigDecimal(this.value.toString()),
        Origin(this.origin.agency.toString(), this.origin.account.toString(), this.origin.dac.toString()),
        Destination(this.destination.key.toString(), KeyType.valueOf(this.destination.keyType.toString())),
        LocalDateTime.parse(this.createdAt.toString())
    )

    private val log = LoggerFactory.getLogger(PaymentKafkaConsumer::class.java)
}