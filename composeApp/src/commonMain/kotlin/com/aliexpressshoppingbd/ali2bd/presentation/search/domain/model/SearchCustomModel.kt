package com.aliexpressshoppingbd.ali2bd.presentation.search.domain.model

data class SearchCustomModel(
    val imageRes: String,
    val title: String
) {
    companion object {
        fun getStoreList(): List<SearchCustomModel> {
            return listOf(
                SearchCustomModel("https://ae01.alicdn.com/kf/Sa0202ec8a96a4085962acfc27e9ffd04F/1080x1080.jpg", "AliExpress"),
                SearchCustomModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTW6y46VJ7WAxXS2zfxkDoNVJO1VMdXuyc1fw&s", "Gearbest"),
                SearchCustomModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTDurMW3d5NVev_v63xTORJe3LyQ_2mx8VISaB8es_LwmncXA9bruiE_fM23bLgxRqZTq8&usqp=CAU", "Ali2BD Express"),
                SearchCustomModel("https://babuvi.com/wp-content/uploads/2024/11/logo-taobao-512.png", "Taobao"),
                SearchCustomModel("https://play-lh.googleusercontent.com/FA_rzaEeLlumm0qh68q3z5Pt-PGMVPf2Z28_pbega7SaXSiKjSzh-0MZceB3FpdvQIBq", "Flipkart"),
                SearchCustomModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQz2uZ2WsewVcwq1K_MfaSxoBOWUbXEv5MRyA&s", "Amazon (India)"),
            )
        }
    }
}
