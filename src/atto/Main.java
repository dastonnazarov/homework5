package atto;


import atto.controller.*;
import atto.database.Database;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        Database.initAdmins();
        Database.createProfileTable();
        Database.createCardTable();
        Database.createTerminalTable();
        Database.createTransactionTable();



        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        MenuController menuController = (MenuController) context.getBean("menuController");
        menuController.startAttoSection();

        AdminController adminController = (AdminController) context.getBean("adminController");
        adminController.adminSectionMenu();

        ProfileController profileController = (ProfileController) context.getBean("profileController");
        profileController.adminProfileMenu();

        CardController cardController = (CardController) context.getBean("cardController");
        cardController.adminCardMenu();

        TerminalController terminalController = (TerminalController) context.getBean("terminalController");
        terminalController.adminTerminalMenu();

        UserController userController = (UserController) context.getBean("userController");
        userController.userCardMenu();

    }

/*    H/W
***** Atto *****
            1. Profile (name,surname,phone unique,pswd,created_date,status,role)
2. Card (number,exp_date,balance,status,phone,created_date)
3. Terminal (code,address,status,created_date)
4. Transaction (card_number,amount,terminal_code,type,created_date)

** Enums
    Transaction Type: ReFill, Payment
    Transaction Role: USER,ADMIN

*** Menu ***
            1. Login
    Enter phone:
    Enter pswd:
            2. Registration:
    Enter name:
    Enter surname:
    Enter phone:
    Enter pswd:

            *** User  Menu **
            (Card)
            1. Add Card (number) - (cartani profile ga ulab qo'yamiz.) (added_date)
    Enter Card Number:
            (kiritilgan number database da bo'lishi kerak.)
            2. Card List (number,exp_date,balance)
    3. Card Change StatusProfile
    4. Delete Card (visible_user = false,deleted_user)

    4. ReFill (pul tashlash) (carta userga tegishli bo'lishi kerak.)
    Enter card number:
    Balance:
            (Transaction with type 'ReFill')

            (Transaction)
            5. Transaction
    CardNumber, Address, Amount,TransactionDate,Type (oxirgi birinchi ko'rinadi)
            6. Make Payment (pul to'lash)
            Enter cardNumber:
            Enter terminal number:
            (Transaction with type 'Payment')


*** Admin Menu ***
            (Card)
            1. Create Card(number,exp_date)
    2. Card List
    3. Update Card (number,exp_date)
    4. Change Card status
    5. Delete Card

            (Terminal)
    6. Create Terminal (code unique,address)
    7. Terminal List
    8. Update Terminal (code,address)
    9. Change Terminal StatusProfile
    10. Delete

            (Profile)
    11. Profile List
    12. Change Profile StatusProfile (by phone)

    (Transaction)
            13. Transaction List
    CardNumber, TerminalNumber, Amount,TransactionDate,Type (oxirgi birinchi ko'rinadi)
            14. Company Card Balance
            (card bo'ladi shu cardga to'lovlar tushadi. bu sql da insert qilinga bo'ladi)

            (Statistic)
    15. Bugungi to'lovlar
    CardNumber, TerminalNumber, Amount,TransactionDate,Type (oxirgi birinchi ko'rinadi)
            16. Kunlik to'lovlar (bir kunlik to'lovlar):
    Enter Date: yyyy-MM-dd
    CardNumber, TerminalNumber, Amount,TransactionDate,Type (oxirgi birinchi ko'rinadi)
            17. Oraliq to'lovlar:
            Enter FromDate: yyyy-MM-dd
            Enter ToDate:   yyyy-MM-dd
            18. Umumiy balance (company card dagi pulchalar)
    19. Transaction by Terminal:
    Enter terminal number:
            20. Transaction By Card:
    Enter Card number:*/
}
