package nl.codingwithlinda.persistence.data.mapping

import nl.codingwithlinda.core.test_data.test_data_generators.fakeTransactions
import nl.codingwithlinda.persistence.room.data.mapping.toDomain
import nl.codingwithlinda.persistence.room.data.mapping.toEntity
import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

class TransactionMappingKtTest{

    @Test
    fun `test transaction mapping`() {
        val amount = BigDecimal("-10.01")
        val transaction = fakeTransactions("1").first().copy(
            amount = amount
        )

        val transactionEntity = transaction.toEntity()

        //assertEquals(transactionEntity.amount, "10.01")
        assertEquals(transaction.amount, transactionEntity.toDomain().amount)

    }
}