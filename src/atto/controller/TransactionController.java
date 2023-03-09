package atto.controller;


import atto.dto.Transaction;
import atto.service.TransactionService;
import atto.util.ScannerUtil;

import java.util.LinkedList;
import java.util.List;


public class TransactionController {
   /* public static List<Transaction> transactionList;

    public TransactionController() {
        transactionList = new LinkedList<>();
    }

    public static void adminTransactionMenu() {
        System.out.println("-----------  Transaction Menu  ----------");
        System.out.println("1. Transaction List");
        System.out.println("2. Company Card Balance");
        System.out.println("3. Bugungi to'lovlar");
        System.out.println("4. Kunlik to'lovlar (bir kunlik to'lovlar)");
        System.out.println("5. Oraliq to'lovlar");
        System.out.println("6. Umumiy balance (company card dagi pulchalar)");
        System.out.println("7. Transaction by Terminal");
        System.out.println("8. Transaction By Card");
        System.out.println("9. Go Back");
        System.out.println("0. Exit");
        adminTransactionStart();

    }


    public static void adminTransactionStart() {

        while (true) {
            Integer action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    TransactionService.transactionList();
                    break;
                case 2:
                    TransactionService.cardCompany();
                    break;
                case 3:
                    TransactionService.todayTransactionList();
                    break;
                case 4:
                    TransactionService.transactionByDay();
                    break;
                case 5:
                    TransactionService.transactionBetweenDays();
                    break;
                case 6:
                    TransactionService.totalBalance(transactionList);
                    break;
                case 7:
                    TransactionService.transactionByTerminal();
                    break;
                case 8:
                   TransactionService.transactionByCard();
                    break;
                case 9:
                    AdminController.adminSectionMenu();
                case 0:
                    return;
                default:
                    System.out.println("wrong option!");
                    break;
            }
        }
    }*/




}
