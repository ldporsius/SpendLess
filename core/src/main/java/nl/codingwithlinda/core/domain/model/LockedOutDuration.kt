package nl.codingwithlinda.core.domain.model

enum class LockedOutDuration(val duration: Int) {
    FIFTEEN_SECONDS(15),
    THIRTY_SECONDS(30),
    ONE_MINUTE(60),
    FIVE_MINUTES(60*5)
}