package org.megamind.rdc_etat_civil_app.data.service.dto

import kotlinx.serialization.Serializable
import org.megamind.rdc_etat_civil_app.domain.model.Commune
import org.megamind.rdc_etat_civil_app.domain.model.Entite
import org.megamind.rdc_etat_civil_app.domain.model.Province
import org.megamind.rdc_etat_civil_app.domain.model.Role

@Serializable
data class RegisterRequest(
    val username: String,
    val password: String,
    val role: Role,
    val province: Province? = null,
    val entite: Entite? = null,
    val commune: Commune? = null
)