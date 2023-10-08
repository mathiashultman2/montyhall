package main;

import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;

public class MontyHall {
    public static void main(String[] args){
        new MontyHall().go();
    }

    private final Random random = new Random();

    private int getRandomNumberMax3(){
        return random.nextInt(3);
    }

    private int getRandomNumberMax2(){
        return random.nextInt(2);
    }

    private int getRandomNumberMax3Exclude(final int exclude,final int alsoExlude){
        int generatedNumber = 0;
        do{
            generatedNumber = random.nextInt(3);
        } while (generatedNumber == exclude || generatedNumber == alsoExlude);
        return generatedNumber;
    }

    private boolean isWillSwitchChoice(){
        return getRandomNumberMax2()==0;
    }

    private boolean isNotSame(final int one, final int other){
        return one != other;
    }

    private int getOneFaultyBox(final int boxWithPrice, final int choice){
        OptionalInt openedFaultyBox = IntStream.range(0,3)
                .filter(number -> isNotSame(number, boxWithPrice))
                .filter(number -> isNotSame(number, choice))
                .findFirst();
        return openedFaultyBox.getAsInt();
    }
    private void go(){
        int notSwitchWin=0;
        int switchWin=0;
        for(int i=0;i<50;i++) {
            final int boxWithPrice = getRandomNumberMax3();
            int choice = getRandomNumberMax3();
            final int openedFaultyBox = getOneFaultyBox(boxWithPrice, choice);
            final boolean isWillSwitchChoice = isWillSwitchChoice();
            if (isWillSwitchChoice) {
                choice = getRandomNumberMax3Exclude(choice, openedFaultyBox);
            }
            if (choice == boxWithPrice){
                if(isWillSwitchChoice){
                    switchWin++;
                }
                else{
                    notSwitchWin++;
                }
            }
        }
        System.out.println("SwitchWin: "+switchWin+", notSwitchWin: "+notSwitchWin);
    }
}