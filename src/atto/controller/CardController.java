package atto.controller;

import atto.container.ComponentContainer;
import atto.dto.Card;
import atto.service.CardService;
import atto.util.ScannerUtil;

import java.util.Scanner;


public class CardController {
    private CardService cardService;
    private AdminController adminController;



    public void adminCardMenu() {
        System.out.println("-----------  CardMenu  ----------");
        System.out.println("1.Create Card");
        System.out.println("2.Card List");
        System.out.println("3.Update Card (number,exp_date)");
        System.out.println("4.Change Card status");
        System.out.println("5.Delete Card");
        System.out.println("6.Go Back");
        System.out.println("0.Exit");
        adminByCardStart();
    }

    public void adminByCardStart() {
        boolean bool = true;
        while (bool) {
            Integer action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    createCard();
                    break;
                case 2:
                    cardList();
                    break;
                case 3:
                    updateCard();
                    break;
                case 4:
                    changeCardStatus();
                    break;
                case 5:
                    deleteCard();
                    break;
                case 6:
                   adminController.adminSectionMenu();
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
    public void createCard() {
        System.out.println("Enter Card Number: ");
        String cardNumber = ComponentContainer.StringScan.nextLine();

        System.out.println("Enter Card exp_date: ");
        String exp_date = ComponentContainer.StringScan.nextLine();

        Card card = new Card();
        card.setNumber(cardNumber);
        card.setExp_date(exp_date);
        cardService.checkCard(card);
    }

    public void cardList() {
        cardService.allCardList();
    }

    public void updateCard() {
        System.out.println("Enter card number: ");
        String number = ComponentContainer.StringScan.nextLine();
        System.out.println("Enter card new update exp_date: ");
        String exp_date = ComponentContainer.StringScan.nextLine();

        Card card = new Card();
        card.setNumber(number);
        card.setExp_date(exp_date);
        cardService.updateCard(card);
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

    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }

}
