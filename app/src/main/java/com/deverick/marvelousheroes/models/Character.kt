package com.deverick.marvelousheroes.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val urls: List<CharacterURL>,
    val thumbnail: CharacterThumbnail,
    val comics: CharacterComics,
    val stories: CharacterStories,
    val events: CharacterEvents,
    val series: CharacterSeries,
): Parcelable

@Parcelize
data class CharacterURL(
    val type: String,
    val url: String
): Parcelable

@Parcelize
data class CharacterThumbnail(
    val path: String,
    val extension: String
): Parcelable

@Parcelize
data class CharacterComics(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterComicsItem>
): Parcelable

@Parcelize
data class CharacterStories(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterStoriesItem>
): Parcelable

@Parcelize
data class CharacterEvents(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterEventsItem>
): Parcelable

@Parcelize
data class CharacterSeries(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterSeriesItem>
): Parcelable

@Parcelize
data class CharacterComicsItem(
    val resourceURI: String,
    val name: String
): Parcelable

@Parcelize
data class CharacterStoriesItem(
    val resourceURI: String,
    val name: String,
    val type: String
): Parcelable

@Parcelize
data class CharacterEventsItem(
    val resourceURI: String,
    val name: String
): Parcelable

@Parcelize
data class CharacterSeriesItem(
    val resourceURI: String,
    val name: String,
    val type: String
): Parcelable

