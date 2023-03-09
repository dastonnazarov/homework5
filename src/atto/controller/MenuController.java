package atto.controller;
import atto.container.ComponentContainer;
import atto.dto.Profile;
import atto.service.ProfileService;
import atto.util.ScannerUtil;




public class MenuController {
private static ProfileService profileService;


    public static void singUpMenu() {
        System.out.println("----------- Atto Menu  ----------");
        System.out.println("1.Login");
        System.out.println("2.Registration");
        System.out.println("0.Exit");

    }
    public static void startAttoSection() {

        while (true){
            singUpMenu();
            Integer action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                  loginProfile();
                    break;
                case 2:
                   registrationSection();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("wrong option!!!");
                    break;
            }
        }
    }



    public static void loginProfile() {
        System.out.println("Enter phone: ");
        String phone = ComponentContainer.StringScan.nextLine();
        System.out.println("Enter password: ");
        String password = ComponentContainer.StringScan.nextLine();

        Profile profile = new Profile();
        profile.setPhone(phone);
        profile.setPassword(password);

        profileService.loginProfile(profile);
    }



    public static void registrationSection() {
        System.out.println("Enter name: ");
        String name = ComponentContainer.StringScan.nextLine();
        System.out.println("Enter surname: ");
        String surname = ComponentContainer.StringScan.nextLine();
        System.out.println("Enter phone: ");
        String phone = ComponentContainer.StringScan.nextLine();
        System.out.println("Enter password: ");
        String password = ComponentContainer.StringScan.nextLine();

        Profile profile = new Profile();
        profile.setName(name);
        profile.setSurname(surname);
        profile.setPhone(phone);
        profile.setPassword(password);
        profileService.registrationProfile(profile);

    }

    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

}
