package com.payments.pixprocessor.adapter.output.kafka

import com.payment.TransactionItem
import com.payments.pixprocessor.domain.model.Transaction
import com.payments.pixprocessor.domain.port.output.MessageProducer
import com.payments.pixprocessor.domain.service.PixService
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class PaymentKafkaProducer(
    val kafkaTemplate: KafkaTemplate<String, TransactionItem>
) : MessageProducer {

    override fun send(transation: Transaction) {
        val event = buildEvent(transation)
        val eventProcessed = kafkaTemplate.send(
            "payment-topic",
            event
        ).get()
        log.info(
            "Item send to Kafka - topic: {}, offset: {}, partition: {}",
            eventProcessed.producerRecord.topic(),
            eventProcessed.recordMetadata.offset(),
            eventProcessed.recordMetadata.partition()
        )
    }

    private fun buildEvent(transation: Transaction): TransactionItem =
        TransactionItem(
            transation.value.toString(),
            transation.key,
            transation.keyType.toString(),
            transation.createdAt.toString()
        )

    private val log = LoggerFactory.getLogger(PixService::class.java)

}