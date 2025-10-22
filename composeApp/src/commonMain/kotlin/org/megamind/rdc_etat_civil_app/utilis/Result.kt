package org.megamind.rdc_etat_civil_app.utilis


sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val e: Throwable?) : Result<Nothing>()
}