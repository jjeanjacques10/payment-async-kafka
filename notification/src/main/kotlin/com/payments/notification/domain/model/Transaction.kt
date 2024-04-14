package com.payments.notification.domain.model

import com.payments.notification.domain.enums.KeyType
import java.math.BigDecimal
import java.time.LocalDateTime

data class Transaction(
    val value: BigDecimal,
    val key: String,
    val keyType: KeyType,
    val createdAt: LocalDateTime
) {
    constructor() : this(BigDecimal.ZERO, "", KeyType.EVP, LocalDateTime.now())
}