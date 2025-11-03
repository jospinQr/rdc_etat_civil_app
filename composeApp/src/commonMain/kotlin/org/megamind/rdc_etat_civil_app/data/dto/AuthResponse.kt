package org.megamind.rdc_etat_civil_app.data.service.dto

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(val token: String)