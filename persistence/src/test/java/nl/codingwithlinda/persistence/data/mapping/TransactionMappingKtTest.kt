package nl.codingwithlinda.persistence.data.mapping

import nl.codingwithlinda.core.test_data.fakeTransactions
import org.junit.Assert.*
import org.junit.Test
import java.math.BigDecimal

class TransactionMappingKtTest{

    @Test
    fun `test transaction mapping`() {
        val amount = BigDecimal("-10.01")
        val transaction = fakeTransactions().first().copy(
            amount = amount
        )

        val transactionEntity = transaction.toEntity()

        //assertEquals(transactionEntity.amount, "10.01")
        assertEquals(transaction.amount, transactionEntity.toDomain().amount)

    }
}