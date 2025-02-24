package nl.codingwithlinda.core.domain.model

enum class SessionDuration(val duration: Int) {
    FIVE_MINUTES(5),
    FIFTEEN_MINUTES(15),
    THIRTY_MINUTES(30),
    ONE_HOUR(60),
}