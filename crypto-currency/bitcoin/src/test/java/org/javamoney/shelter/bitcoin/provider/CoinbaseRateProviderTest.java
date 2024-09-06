package org.javamoney.shelter.bitcoin.provider;

import org.javamoney.moneta.CurrencyUnitBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.money.UnknownCurrencyException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class CoinbaseRateProviderTest {
    private static CoinbaseRateProvider coinbaseRateProvider;

    @BeforeClass
    public static void setUpBeforeClass() {
        coinbaseRateProvider = new CoinbaseRateProvider();
        CurrencyUnitBuilder.of("BTC", "BitcoinProvider")
                .setDefaultFractionDigits(8)
                .build(true);
    }

    @AfterClass
    public static void tearDownAfterClass() {
        coinbaseRateProvider = null;
    }

    @Test
    public void testGetExchangeRate() {
        assertNotNull(coinbaseRateProvider.getExchangeRate("BTC", "USD"));
    }

    @Test
    public void testGetExchangeRateWithInvalidCurrency() {
        assertThrows(UnknownCurrencyException.class, () -> coinbaseRateProvider.getExchangeRate("BTC", "INVALID"));
    }
}