package com.payments.notification.domain.model

import com.payments.notification.domain.enums.KeyType

data class Destination(
    var key: String,
    var keyType: KeyType
) {
    constructor() : this("", KeyType.EVP)
}
