package atto.controller;

import atto.container.ComponentContainer;
import atto.dto.Card;
import atto.dto.Profile;
import atto.service.CardService;
import atto.service.UserService;
import atto.util.ScannerUtil;

import java.util.Scanner;



public class UserController {
    private UserService userService;
    private CardService cardService;
    private MenuController menuController;

    public  void userCardMenu() {
        System.out.println("-----------  CardMenu  ----------");
        System.out.println("1. Add Card");
        System.out.println("2. Card List ");
        System.out.println("3. Card Change Status");
        System.out.println("4. Delete Card");
        System.out.println("5. ReFill ");
        System.out.println("6. Transaction List");
        System.out.println("7. Make Payment");
        System.out.println("8.Go Back");
        System.out.println("0.Exit");
        userByCardStart();
    }

    public  void userByCardStart() {
        boolean bool = true;
        while (bool) {
            Integer action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    addCard();
                    break;
                case 2:
                    cardList();
                    break;
                case 3:
                    changeCardStatus();
                    break;
                case 4:
                    deleteCard();
                    break;
                case 5:
                    reFillCard();
                    break;
                case 6:
                    transactionList();
                    break;
                case 7:
                    makePayment();
                    break;
                case 8:
                     menuController.singUpMenu();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("wrong option!");
                    break;
            }
        }
    }


    //CRUD SECTION
    public  void addCard() {
        System.out.println("Enter Card Number: ");
        String cardNumber = ComponentContainer.StringScan.nextLine();

        System.out.println("Enter Card exp_date: ");
        String exp_date = ComponentContainer.StringScan.nextLine();

        Card card = new Card();
        card.setNumber(cardNumber);
        card.setExp_date(exp_date);
          userService.addCard(card);
    }

    public void cardList() {
        cardService.allCardList();
    }


    public void changeCardStatus() {
        System.out.println("------ Card change status -------");

        System.out.println("Enter card number: ");
        String cardNumber = ComponentContainer.StringScan.nextLine();
        cardService.cardChangeStatus(cardNumber);
    }

    public void deleteCard() {
        System.out.println("Enter delete card number: ");
        String cardNumber = ComponentContainer.StringScan.nextLine();

        Card card = new Card();
        card.setNumber(cardNumber);
        cardService.deleteCard(card);
    }


    private void reFillCard() {
        System.out.print("Enter card number: ");
        Scanner scanner = new Scanner(System.in);
        String cardNumber = scanner.nextLine();

        System.out.print("Enter amount: ");
        Double amount = scanner.nextDouble();
        Profile profile = ComponentContainer.profile;
        cardService.userRefillCard(profile.getPhone(), cardNumber, amount);
    }

    private static void transactionList() {

    }



    private static void makePayment() {

    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
}
