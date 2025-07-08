package alexa.dev.gyros_android_mapbox.domain.model

enum class RatingColor(val rating: Int, val color: Long) {
    RED(1, 0xffd32f2f),
    ORANGE(2, 0xffffa000),
    YELLOW(3, 0xffffeb3b),
    GREEN(4, 0xff8bc34a),
    DARK_GREEN(5, 0xff4caf50),
    UNKNOWN(0, 0xff89cff0);

    companion object {
        fun getRating(rating: Int): RatingColor {
            return entries.find { it.rating == rating } ?: UNKNOWN
        }
    }
}