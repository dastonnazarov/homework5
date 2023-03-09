package atto.service;

import atto.container.ComponentContainer;

import atto.controller.*;
import atto.dto.Card;
import atto.dto.Terminal;
import atto.enums.CardStatus;
import atto.enums.TerminalStatus;
import atto.repository.ProfileRepository;
import atto.repository.TerminalRepository;

import java.util.List;

public class TerminalService {
    private TerminalRepository terminalRepository;
    private TerminalController terminalController;




    public void checkTerminal(Terminal terminal) {

        Terminal  exist = terminalRepository.getTerminalByCode(terminal.getCode());
        if (exist != null) {
            System.out.println("Sorry Terminal already is exist!!!");
            terminalController.adminTerminalMenu();
            terminalController.createTerminal();
        } else{
            terminalRepository.saveTerminal(terminal);
            System.out.println("Terminal is  create  successfully");
            terminalController.adminTerminalMenu();
        }
    }

    public void allTerminalList() {
        List<Terminal> terminalList = terminalRepository.getAll();
        System.out.println("------- Terminal List -------");
        terminalList.forEach(terminal -> System.out.println(terminal));
        terminalController.adminTerminalMenu();
    }

    public void updateTerminalService(Terminal terminal) {
        Terminal exist = terminalRepository.getTerminalByCode(terminal.getCode());
        if (exist != null) {
            terminalRepository.updateTerminal(terminal);
            System.out.println("Terminal is  update  successfully");
            terminalController.adminTerminalMenu();
        } else {
            System.out.println("Sorry terminal isn't  create  yet!!!");
            terminalController.adminTerminalMenu();
        }
    }

    public void deleteTerminal(Terminal terminal) {
        Terminal exist = terminalRepository.getTerminalByCode(terminal.getCode());
        if (exist == null) {
            System.out.println("Terminal doesn't find !!!");
            terminalController.adminTerminalMenu();
            return;
        } else {
            terminalRepository.deleteTerminal(terminal.getCode());
            System.out.println("Terminal is successfully delete");
            terminalController.adminTerminalMenu();
        }

    }

    public void terminalChangeStatus(String code) {
        Terminal exist = terminalRepository.getTerminalByCode(code);
        if (exist == null) {
            System.out.println("Sorry status isn't exist or not found card number");
            return;
        } else if (exist.getStatus().equals(TerminalStatus.ACTIVE)) {
            terminalRepository.updateCardByStatus(TerminalStatus.BLOCK, code);
            terminalController.adminTerminalMenu();
        } else if ( exist.getStatus().equals(TerminalStatus.BLOCK)) {
            terminalRepository.updateCardByStatus(TerminalStatus.ACTIVE, code);
            terminalController.adminTerminalMenu();

        }
    }

    public void setTerminalRepository(TerminalRepository terminalRepository) {
        this.terminalRepository = terminalRepository;
    }

    public void setTerminalController(TerminalController terminalController) {
        this.terminalController = terminalController;
    }
}
