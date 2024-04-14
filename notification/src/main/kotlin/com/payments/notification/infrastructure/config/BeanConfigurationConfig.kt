package com.payments.notification.infrastructure.config

import com.payments.notification.domain.service.NotificationService
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(NotificationService::class)
class BeanConfigurationConfig {
}