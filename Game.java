package blackjack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {

	private static Player player = new Player();
    private static Player dealer = new Player();
    private static Deck deck = new Deck();
    
    private static final String Even = "引き分けです";
    private static final String PlayerWin = "プレーヤーの勝ち";
    private static final String DealerWin = "ディーラーの勝ち";
    private static String resultStr = "";
    private static boolean game = true;
    private static int bet;
    private static int resultMoney = player.getMoney();
    
    public static void main(String[] args) {
        System.out.println("★BlackJack★");
        
        while(game) {
        	
        	bet();
        	
        	startGame();
            
            judge();
            
            player.setCalcResult(0);
            dealer.setCalcResult(0);
            
            restartGame();
        }
    }
    
    private static void startGame() {
    	
        String startStr1 = "プレーヤーの持ち札 : ";
        String startStr2 = null;
        
        for (int i=1; i<=2; i++) {
            Card playerCard = deck.dealCard();
            player.addCardAndCalc(playerCard);
            deck.deleteCard(playerCard);
            
            Card dealerCard = deck.dealCard();
            dealer.addCardAndCalc(dealerCard);
            deck.deleteCard(dealerCard);
            
            startStr1 += playerCard.getMark() + playerCard.getName() + " ";
            startStr2 = dealerCard.getMark() + dealerCard.getName() + " ";
        }
        
        System.out.println(startStr1);
        System.out.println("合計点: " + player.getCalcResult());
        System.out.println("ディーラーのアップカード : " + startStr2);
        
        playerGame(true);
    }
    
    private static void playerGame(boolean isFirst) {
        
        if (player.isHit()) {
            Card card = deck.dealCard();
            player.addCardAndCalc(card);
            deck.deleteCard(card);
            System.out.println("引いたカード : " + card.getMark() + card.getName());
            System.out.println("合計点: " + player.getCalcResult());
        }
        
        if (player.getCalcResult() > 21) {
            player.setHit(false);
        } else if (player.getCalcResult() == 21) {
            if (player.getCardList().size() == 2) {
                player.setBlackJack(true);
            }
            player.setHit(false);
        } else {
            player.setHit(hitOrStand());
        }
        
        if (isFirst) {
            dealerGame();
        } else if (dealer.isHit()) {
            dealerGame();
        } else if (player.isHit()) {
            playerGame(false);
        }
    }
    
    private static void dealerGame() {
        
        if (dealer.isHit()) {
            Card card = deck.dealCard();
            dealer.addCardAndCalc(card);
            deck.deleteCard(card);
        }
        
        if (dealer.getCalcResult() > 21) {
            dealer.setHit(false);
        } else if (dealer.getCalcResult() == 21) {
            if (dealer.getCardList().size() == 2) {
                dealer.setBlackJack(true);
            }
            dealer.setHit(false);
        } else if (dealer.getCalcResult() >= 17) {
            dealer.setHit(false);
        } else {
            dealer.setHit(true);
        }
       
        if (player.isHit()) {
            playerGame(false);
        } else if (dealer.isHit()) {
            dealerGame();
        } 
    }
    
    public static boolean hitOrStand() {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        boolean isHit = false;

        do {
            System.out.println("[H]または[S]を入力してください ヒット:h / スタンド:s");
            try {
                str = br.readLine();
                if ("h".equals(str) || "H".equals(str)) {
                    isHit = true;
                } else if ("s".equals(str) || "S".equals(str)) {
                    isHit = false;
                } else {
                    str = "";
                }
            } catch(IOException e) {
                System.out.println("入力エラー:" + e.getMessage());
                str = "";
            }
        } while ("".equals(str));

        return isHit;
    }
    
    public static void judge(){
        
        if(player.isBlackJack() && dealer.isBlackJack()){
            System.out.println("両者ともブラックジャックです。");
            System.out.println(Even);
            return;
        } else if (player.isBlackJack()) {
            System.out.println("ブラックジャックです。");
            System.out.println(PlayerWin);
            resultMoney = (int) (resultMoney + bet * 1.5);
            return;
        } else if (dealer.isBlackJack()) {
            System.out.println("ブラックジャックです。");
            System.out.println(DealerWin);
            player.setMoney(player.getMoney() - bet);
            return;
        }
        
        int playerResult = player.getCalcResult();
        int dealerResult = dealer.getCalcResult();
        
        resultStr = "最終合計点　プレイヤー : " + playerResult + " / ディーラー : " + dealerResult;
      
        if (playerResult > 21 && dealerResult > 21) {
            System.out.println("両者バーストで" + Even);
        } else if (playerResult > 21) {
            System.out.println(DealerWin);
            resultMoney -= bet;
        } else if (dealerResult > 21) {
            System.out.println(PlayerWin);
            resultMoney += bet;
        } else if (playerResult == dealerResult) {
            System.out.println(Even);
        } else if (playerResult > dealerResult) {
            System.out.println(PlayerWin);
            resultMoney += bet;
        } else {
            System.out.println(DealerWin);
            resultMoney -= bet;
        }
        System.out.println(resultStr  + "  所持金:" + resultMoney);
        
    }
    
    public static void restartGame() {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        do {
            System.out.println("もう一度ゲームを行いますか？ はい:y / いいえ:n");
            try {
                str = br.readLine();
                if ("y".equals(str) || "Y".equals(str)) {
                	game = true;
                }
                else if ("n".equals(str) || "N".equals(str)) {
                    game = false;
                } else {
                    str = "";
                }
            } catch(IOException e) {
                System.out.println("入力エラー:" + e.getMessage());
                str = "";
            }
        } while ("".equals(str));
    }
    
    public static void bet() {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        
        do {
            System.out.println("何枚賭けますか？ 現在の所持金:" + resultMoney);
            try {
                bet = Integer.parseInt(br.readLine());
                if (player.getMoney() >= bet) {
                	break;
                } else {
                    str = "";
                }
            } catch(IOException e) {
                System.out.println("入力エラー:" + e.getMessage());
                str = "";
            }
        } while ("".equals(str));
    }
    

}
