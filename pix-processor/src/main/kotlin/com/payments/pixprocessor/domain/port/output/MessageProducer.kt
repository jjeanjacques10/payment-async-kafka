package com.payments.pixprocessor.domain.port.output

import com.payments.pixprocessor.domain.model.Transaction

fun interface MessageProducer {
    fun send(transation: Transaction)
}
