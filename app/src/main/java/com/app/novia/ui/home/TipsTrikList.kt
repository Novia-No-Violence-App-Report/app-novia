package com.app.novia.ui.home

object TipsTrikList {
    private var data = arrayOf(
        arrayOf(
            "https://www.masakapahariini.com/wp-content/uploads/2018/04/resep-rawon-daging-780x440.jpg",
            "Konfrontasi",
            "Bersikap tegas pada pelaku dan lakukan sesuatu"
        ),
        arrayOf(
            "https://www.masakapahariini.com/wp-content/uploads/2018/04/resep-rawon-daging-780x440.jpg",
            "Lindungi diri",
            "Selalu waspada dimana pun Anda berada"
        ),
        arrayOf(
            "https://www.masakapahariini.com/wp-content/uploads/2018/04/resep-rawon-daging-780x440.jpg",
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