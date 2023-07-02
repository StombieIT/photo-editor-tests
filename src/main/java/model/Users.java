package model;

public class Users {
    private static OKUser okBot;

    public static OKUser okBot() {
        if (okBot == null) {
            okBot = new OKUser("arturbesstrashnii", "testQA_1", "Артур", "Бесстрашный");
        }
        return okBot;
    }
}
