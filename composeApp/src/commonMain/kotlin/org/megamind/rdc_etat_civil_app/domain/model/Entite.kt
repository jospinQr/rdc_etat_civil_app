package org.megamind.rdc_etat_civil_app.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Entite(
    val id: Long? = null,
    val designation: String,
    val estVille: Boolean,
    val province: Province,
)
