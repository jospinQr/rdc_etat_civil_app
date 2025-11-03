package org.megamind.rdc_etat_civil_app.data.service.dto

import kotlinx.serialization.Serializable

@Serializable
class LoginRequest (
    val username:String,
    val password:String
)