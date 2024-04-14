package com.payments.pixprocessor.domain.model

import com.payments.pixprocessor.domain.enums.KeyType

data class Destination(
    var key: String,
    var keyType: KeyType
) {
    constructor() : this("", KeyType.EVP)
}
