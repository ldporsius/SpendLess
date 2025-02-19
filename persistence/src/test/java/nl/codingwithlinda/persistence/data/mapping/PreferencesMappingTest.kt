package nl.codingwithlinda.persistence.data.mapping

import nl.codingwithlinda.core.domain.model.Currency
import nl.codingwithlinda.core.domain.model.ExpensesFormat
import nl.codingwithlinda.core.domain.model.Preferences
import nl.codingwithlinda.core.domain.model.PreferencesAccount
import nl.codingwithlinda.core.domain.model.Separator
import nl.codingwithlinda.persistence.room.data.mapping.toDomain
import nl.codingwithlinda.persistence.room.data.mapping.toEntity
import org.junit.Assert.*
import org.junit.Test

class PreferencesMappingTest{

    val preferences = Preferences(
        expensesFormat = ExpensesFormat.MINUS,
        currency = Currency.EURO,
        thousandsSeparator = Separator.SPACE,
        decimalSeparator = Separator.PERIOD,
        decimalPlaces = 2,
    )

    val preferencesAccount = PreferencesAccount(
        preferences = preferences,
        accountId = "123"
    )

    @Test
    fun `test preferences mapping`(){
        val preferencesEntity = preferencesAccount.toEntity()

        println("preferencesEntity: $preferencesEntity")
        val preferencesDomain = preferencesEntity.toDomain()

        println("preferencesDomain: $preferencesDomain")

        assertEquals(preferencesAccount, preferencesDomain)

    }

}