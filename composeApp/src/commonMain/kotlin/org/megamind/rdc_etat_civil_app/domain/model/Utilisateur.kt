package org.megamind.rdc_etat_civil_app.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Utilisateur(
    val id: Long,
    val username: String,
    val password: String,
    val role: Role = Role.ADMIN,
    val province: Province? = null,
    val commune: Commune? = null

)

enum class Role {
    ADMIN,
    OEC,
    CB,
    CD,
}