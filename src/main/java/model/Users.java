package model;

public class Users {
    private static OKUser okBot;

    public static OKUser okBot() {
        if (okBot == null) {
            okBot = new OKUser("Yarcev3", "testQA_1");
        }
        return okBot;
    }
}
