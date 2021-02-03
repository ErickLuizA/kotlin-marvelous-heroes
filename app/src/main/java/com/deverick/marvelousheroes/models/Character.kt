package com.deverick.marvelousheroes.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "characters"
)
@Parcelize
data class Character(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val resourceURI: String,
    val thumbnail: CharacterThumbnail,
    val comics: CharacterComics,
    val stories: CharacterStories,
    val events: CharacterEvents,
    val series: CharacterSeries,
    val favorite: Boolean = false,
) : Parcelable

@Parcelize
data class CharacterThumbnail(
    val path: String,
    val extension: String
) : Parcelable

@Parcelize
data class CharacterComics(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterComicsItem>? = null
) : Parcelable

@Parcelize
data class CharacterStories(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterStoriesItem>? = null
) : Parcelable

@Parcelize
data class CharacterEvents(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterEventsItem>? = null
) : Parcelable

@Parcelize
data class CharacterSeries(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterSeriesItem>? = null
) : Parcelable

@Parcelize
data class CharacterComicsItem(
    val resourceURI: String,
    val name: String
) : Parcelable

@Parcelize
data class CharacterStoriesItem(
    val resourceURI: String,
    val name: String,
    val type: String
) : Parcelable

@Parcelize
data class CharacterEventsItem(
    val resourceURI: String,
    val name: String
) : Parcelable

@Parcelize
data class CharacterSeriesItem(
    val resourceURI: String,
    val name: String,
    val type: String
) : Parcelable

