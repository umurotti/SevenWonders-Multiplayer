class FluentPattern {

    public static void main(String[] args) {
        Card card = new Card();

        card.name("isim").color("renk");

        System.out.print(card);
    }
}