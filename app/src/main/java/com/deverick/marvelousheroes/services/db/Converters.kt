package com.deverick.marvelousheroes.services.db

import androidx.room.TypeConverter
import com.deverick.marvelousheroes.models.*

class Converters {

    @TypeConverter
    fun characterThumbnailToString(value: CharacterThumbnail): String = value.path

    @TypeConverter
    fun stringToCharacterThumbnail(value: String): CharacterThumbnail =
        CharacterThumbnail(value, "jpg")

    @TypeConverter
    fun characterComicsToString(value: CharacterComics): String = value.available

    @TypeConverter
    fun stringToCharacterComics(value: String): CharacterComics =
        CharacterComics(value, "", "")

    @TypeConverter
    fun characterStoriesToString(value: CharacterStories): String = value.available

    @TypeConverter
    fun stringToCharacterStories(value: String): CharacterStories =
        CharacterStories(value, "", "")

    @TypeConverter
    fun characterEventsToString(value: CharacterEvents): String = value.available

    @TypeConverter
    fun stringToCharacterEvents(value: String): CharacterEvents =
        CharacterEvents(value, "", "")

    @TypeConverter
    fun characterSeriesToString(value: CharacterSeries): String = value.available

    @TypeConverter
    fun stringToCharacterSeries(value: String): CharacterSeries =
        CharacterSeries(value, "", "")
}