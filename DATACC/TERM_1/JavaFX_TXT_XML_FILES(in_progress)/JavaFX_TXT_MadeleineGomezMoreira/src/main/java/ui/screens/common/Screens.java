package ui.screens.common;

public enum Screens {

    APPSTARTSCREEN(ScreenConstants.APPSTART),
    NEWSPAPERSLISTSCREEN(ScreenConstants.NEWSPAPERSLIST);

    private String route;

    Screens(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }


}
