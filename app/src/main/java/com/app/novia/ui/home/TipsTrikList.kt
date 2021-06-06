package com.app.novia.ui.home

object TipsTrikList {
    private var data = arrayOf(
        arrayOf(
            "https://storage.googleapis.com/novia-files/icon_konfrontasi.png",
            "Konfrontasi",
            "Bersikap tegas pada pelaku dan lakukan sesuatu"
        ),
        arrayOf(
            "https://storage.googleapis.com/novia-files/icon_lindungi.png",
            "Lindungi diri",
            "Selalu waspada dimana pun Anda berada"
        ),
        arrayOf(
            "https://storage.googleapis.com/novia-files/icon_hubungi.png",
            "Hubungi",
            "Keluarga atau kerabat Anda dengan segera"
        )
    )

    val listData: ArrayList<TipsTrikModel> get() {
        val list = arrayListOf<TipsTrikModel>()
        for (array in data) {
            val tips = TipsTrikModel()
            tips.img = array[0]
            tips.title = array[1]
            tips.description = array[2]

            list.add(tips)
        }
        return list
    }
}