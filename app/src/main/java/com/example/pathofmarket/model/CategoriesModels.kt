package com.example.pathofmarket.model

import com.example.pathofmarket.R

data class CategoryItem(
    val name: String,
    val iconRes: Int
)

object CategoriesDataSet {
    private const val CURRENCY_CAT_NAME = "Currency"
    private val CURRENCY_CAT_ICON = R.drawable.divine_icon
    private const val FRAGMENTS_CAT_NAME = "Fragments"
    private val Fragments_CAT_ICON = R.drawable.breachstone_icon
    private const val ABYSSAL_CAT_NAME = "Abyss"
    private val ABYSSAL_CAT_ICON = R.drawable.abyssal_bones_icon
    private const val UNCUT_GEMS_CAT_NAME = "UncutGems"
    private val UNCUT_GEMS_CAT_ICON = R.drawable.uncut_gems_icon
    private const val LINEAGE_CAT_NAME = "LineageSupportGems"
    private val LINEAGE_CAT_ICON = R.drawable.lineage_gems_icon
    private const val ESSENCES_CAT_NAME = "Essences"
    private val ESSENCES_CAT_ICON = R.drawable.essencess_icon
    private const val SOUL_CORES_NAME = "SoulCores"
    private val SOUL_CORES_ICON = R.drawable.soul_cores_icon
    private const val IDOLS_CAT_NAME = "Idols"
    private val IDOLS_CAT_ICON = R.drawable.idols_icon
    private const val RUNES_CAT_NAME = "Runes"
    private val RUNES_CAT_ICON = R.drawable.runes_icon
    private const val OMENS_CAT_NAME = "Ritual"
    private val OMENS_CAT_ICON = R.drawable.omens_icon
    private const val EXPEDITION_CAT_NAME = "Expedition"
    private val EXPEDITION_CAT_ICON = R.drawable.expedition_icon
    private const val LIQUID_CAT_NAME = "Delirium"
    private val LIQUID_CAT_ICON = R.drawable.liquid_emotions_icon
    private const val CATALYST_CAT_NAME = "Breach"
    private val CATALYST_CAT_ICON = R.drawable.catalysts_icon
    private const val VERISIUM_CAT_NAME = "Verisium"
    private val VERISIUM_CAT_ICON = R.drawable.verisium_icon



    val categories = listOf(
        CategoryItem(CURRENCY_CAT_NAME, CURRENCY_CAT_ICON),
        CategoryItem(FRAGMENTS_CAT_NAME, Fragments_CAT_ICON),
        CategoryItem(ABYSSAL_CAT_NAME, ABYSSAL_CAT_ICON),
        CategoryItem(UNCUT_GEMS_CAT_NAME, UNCUT_GEMS_CAT_ICON),
        CategoryItem(LINEAGE_CAT_NAME, LINEAGE_CAT_ICON),
        CategoryItem(ESSENCES_CAT_NAME, ESSENCES_CAT_ICON),
        CategoryItem(SOUL_CORES_NAME, SOUL_CORES_ICON),
        CategoryItem(IDOLS_CAT_NAME, IDOLS_CAT_ICON),
        CategoryItem(RUNES_CAT_NAME, RUNES_CAT_ICON),
        CategoryItem(OMENS_CAT_NAME, OMENS_CAT_ICON),
        CategoryItem(EXPEDITION_CAT_NAME, EXPEDITION_CAT_ICON),
        CategoryItem(LIQUID_CAT_NAME, LIQUID_CAT_ICON),
        CategoryItem(CATALYST_CAT_NAME, CATALYST_CAT_ICON),
        CategoryItem(VERISIUM_CAT_NAME, VERISIUM_CAT_ICON)

    )
}