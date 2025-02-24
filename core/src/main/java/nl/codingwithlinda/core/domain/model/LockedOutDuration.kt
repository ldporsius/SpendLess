package nl.codingwithlinda.core.domain.model

enum class LockedOutDuration(val duration: Int) {
    FIFTEEN_SECONDS(15_000),
    THIRTY_SECONDS(30_000),
    ONE_MINUTE(60_000),
    FIVE_MINUTES(60_000*5)
}