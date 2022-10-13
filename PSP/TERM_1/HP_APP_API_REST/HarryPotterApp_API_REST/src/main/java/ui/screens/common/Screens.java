package ui.screens.common;

public enum Screens {

    START(ScreenConstants.START),

    HOUSES(ScreenConstants.HOUSES),

    STAFF(ScreenConstants.STAFF),

    SPELLS(ScreenConstants.SPELLS),
    STUDENTS(ScreenConstants.STUDENTS),
    CHOICE(ScreenConstants.CHOICE);

    private String route;

    Screens(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }


}
