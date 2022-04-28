package ru.ruslan.testpagin.network.api.model

data class CharactersList(
    val info: Info,
    val results: List<CharacterData>
)