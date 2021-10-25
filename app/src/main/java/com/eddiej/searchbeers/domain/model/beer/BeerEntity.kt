package com.eddiej.searchbeers.domain.model.beer

/**
 * Beer item entity
 * 필수 데이터 요소
 * @property id
 * @property name
 * @property tagline
 * @property imageUrl
 * @property description
 * @constructor Create empty Beer item entity
 */
data class BeerItemEntity(
    val id: Int,
    val name: String,
    val tagline: String,
    val imageUrl: String,
    val description: String
)