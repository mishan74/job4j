package ru.job4j.bank;

import java.util.*;

public class Bank {
    private final Map<User, List<Account>> clients = new HashMap<>();

    /**
     * Метод добавления пользователя.
     *
     * @param user пользователь.
     */
    public void addUser(User user) {
        clients.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод удаления пользователя.
     *
     * @param user пользователь.
     */
    public void deleteUser(User user) {
        if (clients.get(user) != null) {
            clients.remove(user);
        }
    }

    /**
     * Метод добавляет счет пользователю.
     *
     * @param passport пасспорт пользователя.
     * @param account  добавляемый аккаунт.
     */
    public void addAccountToUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> temp : clients.entrySet()) {
            if (temp.getKey().getPassport().equals(passport)) {
                temp.getValue().add(account);
            }
        }
    }

    /**
     * Метод удаления аккаунта из пользователя.
     *
     * @param passport паспорт пользователя.
     * @param account  удаляемый аккаунт.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (Map.Entry<User, List<Account>> temp : clients.entrySet()) {
            if (temp.getKey().getPassport().equals(passport)) {
                int delete = temp.getValue().indexOf(account);
                if (delete != -1) {
                    temp.getValue().remove(delete);
                }
            }
        }
    }

    /**
     * Метд возвращает список аккаунтов пользователя.
     * null в случае, если такого пользователя нет.
     *
     * @param passport Паспорт пользователя.
     * @return Список аккаунтов.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = null;
        for (Map.Entry<User, List<Account>> temp : clients.entrySet()) {
            if (temp.getKey().getPassport().equals(passport)) {
                result = temp.getValue();
            }
        }
        return result;
    }

    /**
     * Метод поиска Аккаунта по реквизитам.
     *
     * @param requisite Реквизиты искомого аккаунта.
     * @param accounts  Список аккаунтов для поиска.
     * @return Account в случае успеха, null если аккаунт не был найден.
     */
    public Account getAccountFromRequisite(String requisite, List<Account> accounts) {
        Account result = null;
        if (accounts != null) {
            for (Account account : accounts) {
                if (account.getRequisites().equals(requisite)) {
                    result = account;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод перечисления с одного счета на другой.
     *
     * @param srcPassport   номер паспорта отправителя.
     * @param srcRequisite  номер реквизитов отправителя.
     * @param destPassport  номер паспорта получателя.
     * @param destRequisite номер реквизитов получателя.
     * @param amount        сумма перевода.
     * @return true в случае успешной транзакции, false, в случае неудачи.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean result = false;
        Account from = getAccountFromRequisite(srcRequisite, getUserAccounts(srcPassport));
        Account destination = getAccountFromRequisite(destRequisite, getUserAccounts(destPassport));
        if (from != null
                && destination != null
                && from.getValue() >= amount) {
            from.setValue((from.getValue() - amount));
            destination.setValue(destination.getValue() + amount);
            result = true;
        }
        return result;
    }

    /**
     * Для перевода пользователю со своего аккаунта на свой другой.
     * @param passport паспорт пользователя.
     * @param srcRequisite реквизиты отправителя.
     * @param destRequisite реквизиты счета получателя.
     * @param amount сумма.
     * @return true в случае успешной транзакции, false, в случае неудачи.
     */
    public boolean transferMoney(String passport, String srcRequisite, String destRequisite, double amount) {
        return transferMoney(passport, srcRequisite, passport, destRequisite, amount);
    }
}