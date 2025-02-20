package nl.codingwithlinda.core.data.session_manager

class Session(
    val sessionDuration: Long = 15_000L,
) {
    private val startTime = System.currentTimeMillis()
    fun isSessionValid(currentTime: Long): Boolean {
        return currentTime < startTime + sessionDuration
    }

}