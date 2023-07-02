package model;

public enum PhotoLayerAction {
    ROTATE("r"),
    FLIP("f"),
    CROP("c"),
    CREATE_CROP("cc"),
    MOVE_CROP("mc"),
    RESIZE_BY_NW("nw"),
    RESIZE_BY_NE("ne"),
    RESIZE_BY_SW("sw"),
    RESIZE_BY_SE("se"),
    DONE("d");

    private final String hint;

    PhotoLayerAction(String hint) {
        this.hint = hint;
    }

    public String getHint() {
        return hint;
    }
}
