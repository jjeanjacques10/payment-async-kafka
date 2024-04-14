package com.payments.notification.domain.port.output

import com.payments.notification.domain.model.Transaction

fun interface NotificationProcessor {
    fun notify(transation: Transaction)
}
