package com.payments.pixprocessor.infrastructure.config

import com.payments.pixprocessor.domain.service.PixService
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(PixService::class)
class BeanConfigurationConfig {
}