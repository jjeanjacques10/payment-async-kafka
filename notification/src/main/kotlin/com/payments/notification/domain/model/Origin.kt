package com.payments.notification.domain.model

data class Origin(
    var agency: String,
    var account: String,
    var dac: String
) {
    constructor() : this("", "", "")
}