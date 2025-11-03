package org.megamind.rdc_etat_civil_app.data.service.dto

import kotlinx.serialization.Serializable
import org.megamind.rdc_etat_civil_app.domain.model.Role

@Serializable
data class UserInfoResponse(
    val username: String,
    val role: Role,
    val provinceId: Long?,
    val entiteId: Long?,
    val communeId: Long?,
)