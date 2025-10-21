package org.megamind.rdc_etat_civil_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform