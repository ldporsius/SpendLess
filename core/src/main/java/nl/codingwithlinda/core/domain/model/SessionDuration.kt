package nl.codingwithlinda.core.domain.model

enum class SessionDuration(val duration: Int) {
    FIVE_MINUTES(60_000*5),
    FIFTEEN_MINUTES(60_000*15),
    THIRTY_MINUTES(60_000*30),
    ONE_HOUR(60_000*60),
}