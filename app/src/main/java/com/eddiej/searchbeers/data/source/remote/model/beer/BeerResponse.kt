package com.eddiej.searchbeers.data.source.remote.model.beer

import com.eddiej.searchbeers.data.source.remote.model.IMapper
import com.eddiej.searchbeers.domain.model.beer.BeerItemEntity
import com.google.gson.annotations.SerializedName

data class BeerItem(
    @SerializedName("abv")
    val abv: Float = 0.0f,
    @SerializedName("attenuation_level")
    val attenuationLevel: Float = 0.0f,
    @SerializedName("boil_volume")
    val boilVolume: ValueUnit = ValueUnit(),
    @SerializedName("brewers_tips")
    val brewersTips: String = "",
    @SerializedName("contributed_by")
    val contributedBy: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("ebc")
    val ebc: Float = 0.0f,
    @SerializedName("first_brewed")
    val firstBrewed: String = "",
    @SerializedName("food_pairing")
    val foodPairing: List<String> = listOf(),
    @SerializedName("ibu")
    val ibu: Float = 0.0f,
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image_url")
    val imageUrl: String? = "",
    @SerializedName("ingredients")
    val ingredients: Ingredients = Ingredients(),
    @SerializedName("method")
    val method: Method = Method(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("ph")
    val ph: Float = 0.0f,
    @SerializedName("srm")
    val srm: Float = 0.0f,
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("target_fg")
    val targetFg: Float = 0.0f,
    @SerializedName("target_og")
    val targetOg: Float = 0.0f,
    @SerializedName("volume")
    val volume: ValueUnit = ValueUnit()
) : IMapper<BeerItemEntity> {
    override fun toEntity(): BeerItemEntity {
        return BeerItemEntity(
            id = id,
            name = name,
            tagline = tagline,
            imageUrl = imageUrl ?: "",
            description = description
        )
    }
}

data class Ingredients(
    @SerializedName("hops")
    val hops: List<Hop> = listOf(),
    @SerializedName("malt")
    val malt: List<Malt> = listOf(),
    @SerializedName("yeast")
    val yeast: String = ""
)

data class Method(
    @SerializedName("fermentation")
    val fermentation: Fermentation = Fermentation(),
    @SerializedName("mash_temp")
    val mashTemp: List<MashTemp> = listOf(),
    @SerializedName("twist")
    val twist: Any = Any()
)

data class Hop(
    @SerializedName("add")
    val add: String = "",
    @SerializedName("amount")
    val amount: ValueUnit = ValueUnit(),
    @SerializedName("attribute")
    val attribute: String = "",
    @SerializedName("name")
    val name: String = ""
)

data class Malt(
    @SerializedName("amount")
    val amount: ValueUnit = ValueUnit(),
    @SerializedName("name")
    val name: String = ""
)

data class ValueUnit(
    @SerializedName("unit")
    val unit: String = "",
    @SerializedName("value")
    val value: Float = 0.0f
)

data class Fermentation(
    @SerializedName("temp")
    val temp: ValueUnit = ValueUnit()
)

data class MashTemp(
    @SerializedName("duration")
    val duration: Int = 0,
    @SerializedName("temp")
    val temp: ValueUnit = ValueUnit()
)