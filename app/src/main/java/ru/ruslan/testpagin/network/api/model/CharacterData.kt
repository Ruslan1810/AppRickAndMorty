package ru.ruslan.testpagin.network.api.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CharacterData(
    val id: Int,
    val created: String?,
    val gender: String?,
    val image: String?,
    val name: String?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?,
    val episode: List<String>?,
    val location: Location? = null,
    val origin: Origin? = null
) : Parcelable
