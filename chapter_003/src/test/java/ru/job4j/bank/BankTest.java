package ru.job4j.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class BankTest {
    Bank bank;
    User u1;
    User u2;
    User u3;
    User u4;
    User u5;

    @Before
    public void loadTest() {
        u1 = new User("John", "532725");
        u2 = new User("Smith", "6789827254");
        u3 = new User("Brown", "95454252");
        u4 = new User("Victor", "154527252");
        u5 = new User("Valdys", "945532754");
        bank = new Bank();
    }

    @Test
    public void whenAddUserThenAdded() {
       bank.addUser(u1);
       List<Account> result = bank.getUserAccounts(u1.getPassport());
       assertNotNull(result);
    }
    @Test
    public void whenGetNonAddedThenNull() {
        bank.addUser(u1);
        List<Account> result = bank.getUserAccounts(u2.getPassport());
        assertNull(result);
    }

    @Test
    public void whenDeleteUserThanDelete() {
        bank.addUser(u1);
        bank.addUser(u2);
        bank.addUser(u3);
        bank.deleteUser(u2);
        List<Account> result = bank.getUserAccounts(u2.getPassport());
        assertNull(result);
    }
    @Test
    public void whenDeleteNonexistentThanNothing() {
        bank.addUser(u1);
        bank.deleteUser(u2);
        List<Account> result = bank.getUserAccounts(u2.getPassport());
        assertNull(result);
    }
    @Test
    public void whenAddAccountThenAdded() {
        bank.addUser(u1);
        bank.addUser(u2);
        Account exist = new Account(500, "5452147");
        bank.addAccountToUser(u2.getPassport(), exist);
        List<Account> accounts = bank.getUserAccounts(u2.getPassport());
        Account result = bank.getAccountFromRequisite(exist.getRequisites(), accounts);
        assertThat(result, is(exist));
    }

    @Test
    public void whenDeleteAccountThanDeleted() {
        bank.addUser(u2);
        Account account = new Account(500, "5452147");
        bank.addAccountToUser(u2.getPassport(), account);
        bank.deleteAccountFromUser(u2.getPassport(), account);
        List<Account> result = bank.getUserAccounts(u2.getPassport());
        assertTrue(result.isEmpty());
    }

    @Test
    public void whenTransferMoneyThenTrue() {
        bank.addUser(u1);
        bank.addUser(u2);
        bank.addAccountToUser(u1.getPassport(), new Account(500, "6555"));
        bank.addAccountToUser(u2.getPassport(), new Account(600, "5666"));
        boolean result = bank.transferMoney(u1.getPassport(), "6555",
                u2.getPassport(), "5666", 300);
        assertTrue(result);
    }
    @Test
    public void whenTransferMoneyThenChangeMoneySecondAccount() {
        bank.addUser(u1);
        bank.addUser(u2);
        bank.addAccountToUser(u1.getPassport(), new Account(500, "6555"));
        bank.addAccountToUser(u2.getPassport(), new Account(600, "5666"));
        bank.transferMoney(u1.getPassport(), "6555",
                u2.getPassport(), "5666", 300);
        List<Account> accounts = bank.getUserAccounts(u2.getPassport());
        Account checkAccount = bank.getAccountFromRequisite("5666", accounts);
        assertThat(checkAccount.getValue(), is((double) 600 + 300));
    }
    @Test
    public void whenTransferMoneyThenChangeMoneyFirstAccount() {
        bank.addUser(u1);
        bank.addUser(u2);
        bank.addAccountToUser(u1.getPassport(), new Account(500, "6555"));
        bank.addAccountToUser(u2.getPassport(), new Account(600, "5666"));
        bank.transferMoney(u1.getPassport(), "6555",
                u2.getPassport(), "5666", 300);
        List<Account> accounts = bank.getUserAccounts(u1.getPassport());
        Account checkAccount = bank.getAccountFromRequisite("6555", accounts);
        assertThat(checkAccount.getValue(), is((double) 500 - 300));
    }
    @Test
    public void whenTransferMoneyMoreAccountsMoneyThenFalse() {
        bank.addUser(u1);
        bank.addUser(u2);
        bank.addAccountToUser(u1.getPassport(), new Account(500, "6555"));
        bank.addAccountToUser(u2.getPassport(), new Account(600, "5666"));
        boolean result = bank.transferMoney(u1.getPassport(), "6555",
                u2.getPassport(), "5666", 700);
        assertFalse(result);

        List<Account> accountsFirst = bank.getUserAccounts(u1.getPassport());
        Account checkAccountFirst = bank.getAccountFromRequisite("6555", accountsFirst);
        assertThat(checkAccountFirst.getValue(), is((double) 500));

        List<Account> accountsSecond = bank.getUserAccounts(u2.getPassport());
        Account checkAccountSecond = bank.getAccountFromRequisite("5666", accountsSecond);
        assertThat(checkAccountSecond.getValue(), is((double) 600));
    }

    @Test
    public void whenTransferMoneyFromOneUserThanTransfer() {
        bank.addUser(u1);
        bank.addUser(u2);
        bank.addAccountToUser(u1.getPassport(), new Account(800, "1111"));
        bank.addAccountToUser(u1.getPassport(), new Account(900, "2222"));
        boolean result = bank.transferMoney(u1.getPassport(), "1111", "2222", 800);
        assertTrue(result);

        List<Account> accounts = bank.getUserAccounts(u1.getPassport());
        Account checkAccountFirst = bank.getAccountFromRequisite("1111", accounts);
        assertThat(checkAccountFirst.getValue(), is((double) 0));

        Account checkAccountSecond = bank.getAccountFromRequisite("2222", accounts);
        assertThat(checkAccountSecond.getValue(), is((double) 900 + 800));
    }
}
