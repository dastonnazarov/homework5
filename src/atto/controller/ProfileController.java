package atto.controller;

import atto.container.ComponentContainer;
import atto.dto.Profile;
import atto.service.ProfileService;
import atto.util.ScannerUtil;

public class ProfileController {
    private ProfileService profileService;
    private AdminController adminController;


    public void adminProfileMenu() {

        System.out.println("-----------  Profile Menu  ----------");
        System.out.println("1. Profile List");
        System.out.println("2. Change Profile Status (by phone)");
        System.out.println("3. Go Back");
        System.out.println("0. Exit");
        adminProfileStart();

    }

    public void adminProfileStart() {

        while (true) {
            Integer action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    adminController.profileList();
                    break;
                case 2:
                    adminController.changeProfileByPhone();
                    break;
                case 3:
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

    public void profileChangeStatusByPhone() {
        System.out.println("------ Change Profile Status (by phone) -------");

        System.out.println("Enter profile phone: ");
        String phone = ComponentContainer.StringScan.nextLine();
        profileService.profileChangeStatusByPhone(phone);

    }


    public void setProfileService(ProfileService profileService) {
        this.profileService = profileService;
    }

    public void setAdminController(AdminController adminController) {
        this.adminController = adminController;
    }
}

