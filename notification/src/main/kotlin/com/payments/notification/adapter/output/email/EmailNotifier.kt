package com.payments.notification.adapter.output.email

import com.payments.notification.domain.model.Transaction
import com.payments.notification.domain.port.output.NotificationProcessor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EmailNotifier : NotificationProcessor {
    override fun notify(transation: Transaction) {
        log.info("Email sent to ${transation.origin.agency} ${transation.origin.account}-${transation.origin.dac}")
    }

    private val log = LoggerFactory.getLogger(EmailNotifier::class.java)
}