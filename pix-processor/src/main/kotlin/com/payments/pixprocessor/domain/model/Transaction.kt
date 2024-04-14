package com.payments.pixprocessor.domain.model

import com.payments.pixprocessor.domain.enums.KeyType
import java.math.BigDecimal
import java.time.LocalDateTime

data class Transaction(
    var value: BigDecimal,
    var origin: Origin,
    var destination: Destination,
    var createdAt: LocalDateTime
) {
    constructor() : this(
        BigDecimal.ZERO,
        Origin("", "", ""),
        Destination("", KeyType.EVP),
        LocalDateTime.now()
    )
}