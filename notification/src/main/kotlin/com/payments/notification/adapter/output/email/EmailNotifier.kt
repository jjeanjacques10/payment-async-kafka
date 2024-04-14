package com.payments.notification.adapter.output.email

import com.payments.notification.domain.model.Transaction
import com.payments.notification.domain.port.output.NotificationProcessor
import org.springframework.stereotype.Component

@Component
class EmailNotifier : NotificationProcessor {
    override fun notify(transation: Transaction) {
        TODO("Not yet implemented")
    }
}