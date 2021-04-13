package com.comp3350.webudget.business;

import com.comp3350.webudget.Exceptions.WalletException;
import com.comp3350.webudget.objects.Wallet;
import com.comp3350.webudget.persistence.IWalletDatabase;
import com.comp3350.webudget.persistence.testDatabases.TestWalletDatabase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class WalletLogicTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private IWalletLogic walletLogic;
    private IWalletDatabase mockWalletPersistence;
    private Wallet mockWallet;

    @Before
    public void setUp() {
        mockWalletPersistence = mock(TestWalletDatabase.class);
        mockWallet = mock(Wallet.class);
        walletLogic = new WalletLogic(mockWalletPersistence);
    }

    @Test
    public void testZeroBalance() throws WalletException {
        when(mockWallet.getBalance()).thenReturn(0);
        when(mockWalletPersistence.getWallet(anyInt())).thenReturn(mockWallet);
        assertEquals(0, walletLogic.getAmount(0));
        verify(mockWallet).getBalance();
        verify(mockWalletPersistence).getWallet(anyInt());
    }

    @Test(expected = WalletException.class)
    public void testNegativeDeposit() throws WalletException {
        when(mockWalletPersistence.getWallet(anyInt())).thenReturn(mockWallet);

    }
}
