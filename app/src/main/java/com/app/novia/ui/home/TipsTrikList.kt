package com.app.novia.ui.home

object TipsTrikList {
    private var data = arrayOf(
        arrayOf(
            "https://www.masakapahariini.com/wp-content/uploads/2018/04/resep-rawon-daging-780x440.jpg",
            "1",
            "Lorem ipsum dolor sit amet blabla consecstum"
        ),
        arrayOf(
            "https://www.masakapahariini.com/wp-content/uploads/2018/04/resep-rawon-daging-780x440.jpg",
            "2",
            "Lorem ipsum dolor sit amet blabla consecstum"
        ),
        arrayOf(
            "https://www.masakapahariini.com/wp-content/uploads/2018/04/resep-rawon-daging-780x440.jpg",
            "3",
            "Lorem ipsum dolor sit amet blabla consecstum"
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