package com.payments.pixprocessor.adapter.output.kafka

import com.payment.Destination
import com.payment.Origin
import com.payment.TransactionItem
import com.payments.pixprocessor.domain.model.Transaction
import com.payments.pixprocessor.domain.port.output.MessageProducer
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.ExecutionException

@Component
class PaymentKafkaProducer(
    val kafkaTemplate: KafkaTemplate<String, TransactionItem>
) : MessageProducer {

    override fun send(transation: Transaction) {
        val event = buildEvent(transation)
        try {
            val eventProcessed = kafkaTemplate.send("payment-topic", event).get()
            log.info(
                "Item sent to Kafka - topic: {}, offset: {}, partition: {}",
                eventProcessed.producerRecord.topic(),
                eventProcessed.recordMetadata.offset(),
                eventProcessed.recordMetadata.partition()
            )
        } catch (e: ExecutionException) {
            log.error("Failed to send item to Kafka", e.cause)
        } catch (e: InterruptedException) {
            log.error("Interrupted while sending item to Kafka", e)
        }
    }

    private fun buildEvent(transation: Transaction): TransactionItem =
        TransactionItem(
            transation.value.toString(),
            Origin(
                transation.origin.agency,
                transation.origin.account,
                transation.origin.dac
            ),
            Destination(
                transation.destination.key,
                transation.destination.keyType.toString()
            ),
            transation.createdAt.toString()
        )

    private val log = LoggerFactory.getLogger(PaymentKafkaProducer::class.java)

}