package atto.controller;


import atto.container.ComponentContainer;
import atto.repository.ProfileRepository;
import atto.service.ProfileService;
import atto.util.ScannerUtil;

public class AdminController {
    private ProfileService profileService;
    private CardController cardController;
    private TerminalController terminalController;
    private ProfileController profileController;
    private TransactionController transactionController;
    private MenuController menuController;

    public void adminSectionMenu() {
        System.out.println("-----------  Admin Menu  ----------");
        System.out.println("1. Card Section");
        System.out.println("2. Terminal Section");
        System.out.println("3. Profile Section");
        System.out.println("4. Transaction Section");
        System.out.println("5. Statistic Section");
        System.out.println("6.Go Back");
        System.out.println("0. Exit");
        adminSectionStart();

    }

    public void adminSectionStart() {

        while (true) {
            Integer action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    cardController.adminCardMenu();
                    break;
                case 2:
                    terminalController.adminTerminalMenu();
                    break;
                case 3:
                    profileController.adminProfileMenu();
                    break;
                case 4:
           //     transactionController.adminTransactionMenu();
                    break;
                case 5:
                    break;
                case 6:
                    menuController.singUpMenu();
                case 0:
                    return;
                default:
                    System.out.println("wrong option!");
                    break;
            }
        }
    }


    public void profileList() {
        profileService.allProfileList();
    }


    public void changeProfileByPhone() {
        profileController.profileChangeStatusByPhone();
    }


    public void setCardController(CardController cardController) {
        this.cardController = cardController;
    }

    public void setTerminalController(TerminalController terminalController) {
        this.terminalController = terminalController;
    }

    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }

    public void setTransactionController(TransactionController transactionController) {
        this.transactionController = transactionController;
    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }


}
