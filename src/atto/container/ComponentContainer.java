package atto.container;

import atto.controller.*;
import atto.dto.Profile;
import atto.enums.TransactionType;
import atto.repository.*;
import atto.service.*;

import java.util.Scanner;

public class ComponentContainer {
    public static Scanner numberScan = new Scanner(System.in);
    public static Scanner StringScan = new Scanner(System.in);
    public static String generalBalance = "98606004015730810327";


    public static MenuController menuController;
    public static ProfileController profileController;
    public static ProfileRepository profileRepository;
    public static ProfileService profileService;
    public static AdminController adminController;
    public static CardController cardController;
    public static CardService cardService;
    public static CardRepository cardRepository;
    public static TerminalController terminalController;
    public static TransactionController transactionController;
    public static TerminalService terminalService;
    public static TerminalRepository terminalRepository;
    public static UserService userService;
    public static UserRepository userRepository;
    public static UserController userController;
    public static Profile profile;
    public static TransactionService transactionService;
    public static TransactionRepository transactionRepository;


    static {
        profile = new Profile();
        profileController = new ProfileController();
        profileRepository = new ProfileRepository();
        profileService = new ProfileService();
        adminController = new AdminController();
        cardController = new CardController();
        cardService = new CardService();
        cardRepository = new CardRepository();
        menuController = new MenuController();
        terminalController = new TerminalController();
        transactionController = new TransactionController();
        terminalService = new TerminalService();
        terminalRepository = new TerminalRepository();
        userService = new UserService();
        userRepository = new UserRepository();
        userController = new UserController();
        transactionService = new TransactionService();
        transactionRepository = new TransactionRepository();

    }


}
