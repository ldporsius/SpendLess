package nl.codingwithlinda.core.domain.result

import nl.codingwithlinda.core.domain.error.RootError

sealed interface SpendResult<R, E: RootError> {
    data class Success<R, E: RootError>(val data: R): SpendResult<R, E>
    data class Failure<R, E: RootError>(val error: E): SpendResult<R, E>
}