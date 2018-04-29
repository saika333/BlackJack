package blackjack;

public class Card {

    private String mark;
    private Integer number;
    private String name;

    public Card() {
    }

    public Card(String mark, Integer number) {
        this.mark = mark;
        this.number = number;

        if (number == 1) {
            this.name = "A";
        } else if (number >= 2 && number <= 10) {
            this.name = number.toString();
        } else {
            switch (number) {
            case 11:
                this.name = "J";
                break;
            case 12:
                this.name = "Q";
                break;
            case 13:
                this.name = "K";
                break;
            default:
                break;
            }
        }
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

