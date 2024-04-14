package com.payments.pixprocessor.adapter.input.rest

import com.payments.pixprocessor.domain.model.Transaction
import com.payments.pixprocessor.domain.service.PixService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payment")
class Controller(
    val pixService: PixService
) {

    @PostMapping("/pix")
    fun processPix(@RequestBody transaction: Transaction) {
        pixService.process(transaction)
    }
}