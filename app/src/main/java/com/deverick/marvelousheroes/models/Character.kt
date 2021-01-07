package com.deverick.marvelousheroes.models

data class Character(
    val id: String,
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
)

data class CharacterURL(
    val type: String,
    val url: String
)

data class CharacterThumbnail(
    val path: String,
    val extension: String
)

data class CharacterComics(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterComicsItem>
)

data class CharacterStories(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterStoriesItem>
)

data class CharacterEvents(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterEventsItem>
)

data class CharacterSeries(
    val available: String,
    val returned: String,
    val collectionURI: String,
    val items: List<CharacterSeriesItem>
)

data class CharacterComicsItem(
    val resourceURI: String,
    val name: String
)

data class CharacterStoriesItem(
    val resourceURI: String,
    val name: String,
    val type: String
)

data class CharacterEventsItem(
    val resourceURI: String,
    val name: String
)

data class CharacterSeriesItem(
    val resourceURI: String,
    val name: String,
    val type: String
)

