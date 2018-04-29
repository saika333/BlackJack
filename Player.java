package blackjack;

import java.util.ArrayList;
import java.util.List;


public class Player {

    private boolean isHit;
    private int calcResult;
    private List<Card> cardList = new ArrayList<Card>();
    private boolean isBlackJack;
    private int money;
    

    public Player() {
        isHit = false;
        calcResult = 0;
        isBlackJack = false;
        money = 500;
    }

    public boolean isContainA() {
        for (Card card : this.cardList) {
            if (card.getNumber() == 1) {
                return true;
            }
        }
        return false;
    }

    public void calcCard(Card card) {
        int num = card.getNumber();

        if (num == 1) {
            if (21 - this.calcResult > 11) {
                this.calcResult += 11;
            } else {
                this.calcResult += 1;
            }
        } else if (num >= 2 && num <= 10) {
            this.calcResult += num;
        } else {
            this.calcResult += 10;
        }
    }
    
    public void calcCardList() {
        
        this.calcResult = 0;
        int countA = 0;
        
        for (Card card : this.cardList) {
            int num = card.getNumber();
            if (num == 1) {
                countA++;
            } else if (num >= 2 && num <= 10) {
                this.calcResult += num;
            } else {
                this.calcResult += 10;
            }
        }
        
        for (int i=0; i<countA; i++) {
            if (21 - this.calcResult > 11) {
                this.calcResult += 11;
            } else {
                this.calcResult += 1;
            }
        }

    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public void addCardAndCalc(Card card) {
        this.cardList.add(card);
        if (isContainA()) {
            calcCardList();
        } else {
            calcCard(card);
        }
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean isHit) {
        this.isHit = isHit;
    }

    public int getCalcResult() {
        return calcResult;
    }

    public void setCalcResult(int calcResult) {
        this.calcResult = calcResult;
    }

    public boolean isBlackJack() {
        return isBlackJack;
    }

    public void setBlackJack(boolean isBlackJack) {
        this.isBlackJack = isBlackJack;
    }
    
    public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}