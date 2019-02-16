package web.command.enums;

import web.command.Controller;
import web.command.Paths;
import web.command.impl.*;

public enum ControllerType {
    ADMIN(Paths.ADMIN_PATH, "admin", "admin.title", new AdminController()),
    ALLTRANSACTIONS(Paths.ADMIN_TRANS_PATH, "alltransactions", "admin.title", new AdminTransactionController()),
    ALlCARDS(Paths.ADMIN_CARDS_PATH, "allcards", "admin.title", new AdminCardController()),
    BANK(Paths.ADMIN_BANK_PATH, "bank", "admin.title", new BankController()),
    CLIENT(Paths.CLIENT_PATH, "client", "client.titile", new ClientController()),
    CLIENTBANK(Paths.CLIENT_BANK_PATH, "clientbank", "client.titile", new ClientBankController()),
    CLIENTCARD(Paths.CLIENT_CARD_PATH, "clientcard", "client.titile", new ClientCardController()),
    CLIENTTRANS(Paths.CLIENT_TRANS_PATH, "clienttrans", "client.titile", new ClinetTransactionController()),
    DELETE(Paths.ADMIN_PATH, "delete", "admin.title", new DeleteClientController()),
    EXPIREDCARDS(Paths.ADMIN_CARDS_PATH, "expiredcards", "admin.title", new ExpiredCardsController()),
    LOGIN(Paths.LOGIN_PATH, "login", "signin.title", new LoginController()),
    LOGOUT(Paths.LOGIN_PATH, "logout", "logout.title", new LogoutController()),
    NEWCLIENT(Paths.REGISTRATION_PATH, "newclient", "registration.title", new AddClientController()),
    SENDMONEY(Paths.SEND_MONEY_PATH, "sendmoney", "client.titile", new SendMoneyController());

    private String pagePath;
    private String pageName;
    private String i18nKey;
    private Controller controller;

    ControllerType(String pagePath, String pageName, String i18nKey, Controller controller) {
        this.pagePath = pagePath;
        this.pageName = pageName;
        this.i18nKey = i18nKey;
        this.controller = controller;
    }

    public String getPagePath() {
        return pagePath;
    }

    public String getPageName() {
        return pageName;
    }

    public String getI18nKey() {
        return i18nKey;
    }

    public Controller getController() {
        return controller;
    }

    public static ControllerType getByPageName(String page) {
        for (ControllerType type : ControllerType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }

        return LOGIN;
    }
}