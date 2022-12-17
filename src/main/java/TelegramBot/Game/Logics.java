package TelegramBot.Game;

import TelegramBot.TextOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Logics {
    private String playerTurn;
    private String inputText;

    private String[][] player = new String[10][10];
    public ArrayList<String> availableCommandGame = new ArrayList<>();
    private Integer shipNumber;
    private Boolean vectorShip = false;


    private boolean flagStartGame;
    private boolean flagPlayerPlacesShips;

    HashMap<String,Integer> first;

    TextOutput textOutput = new TextOutput();

    //private ConvertTurn convertTurn;

    //private String[][] botAttackField = new String[10][10];
    //private String[][] bot = new String[10][10];

    //String[] firstCoor = new String[]{"A","B","C","D","E","F","G","H","I","K"};
    //String[] secondCoor = new String[]{"1","2","3","4","5","6","7","8","9","10"};

    public Logics()
    {
        flagStartGame = false;
        flagPlayerPlacesShips = true;
        shipNumber = 0;
        //convertTurn = new ConvertTurn();

        first = new HashMap<>();
        first.put("A", 0);
        first.put("B", 1);
        first.put("C", 2);
        first.put("D", 3);
        first.put("E", 4);
        first.put("F", 5);
        first.put("G", 6);
        first.put("H", 7);
        first.put("I", 8);
        first.put("K", 9);

        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++){
                player[i][j] = "_";
            }
    }
    public void setFlagGame() { flagStartGame = true; }
    public boolean getFlagGame() { return flagStartGame; }
    public void setPlayerTurn(String turn) { playerTurn = turn; }

    public String getInputText() { return inputText; }

    public boolean getFlagPlayerPlacesShips() {return flagPlayerPlacesShips; }
    public String[][] getPlayer() { return player; }
    //public String[][] getBotAttackField() { return botAttackField; }

    /* пока не нужно
    private void playerPlacesShips()
    {
        int first = convertTurn.getFirstCoor();
        int second = convertTurn.getSecondCoor();

        switch (shipNumber)
        {
            case 1 ->{
                for(int i=0;i<10;i++)
                    for(int j=0;j<10;j++)
                        player[i][j] = " ";
                player[first][second] = "*";
                if (first+1 < 10) player[first+1][second] = firstCoor[first+1]+secondCoor[second];
                if (first-1 > -1) player[first-1][second] = firstCoor[first-1]+secondCoor[second];
                if (second+1 < 10) player[first][second+1] = firstCoor[first]+secondCoor[second+1];
                if (second-1 > -1) player[first][second-1] = firstCoor[first]+secondCoor[second+1];
            }
            case 2 ->{
                player[first][second] = "*";
            }
        }

        shipNumber++;
    }*/



    //

    //обьединяем их в общий метод, который, если ключ начала расстановки кораблей активен, то
    // в зависимости от номера корабля, вызывает методы ниже и заполняет текстовую строку
    //которая потом передается в сообщение в телеграм пользователю
    //когда расставлены все корабли (номер кораблей равен 10) дизактивируем ключ начала расстановки кораблей
    //начинается генерация поля бота.
    // она происходит аналогично полю игрока, но ход и направление выбирается рандомно
    // число от 0 до 100. далее берется остаток по модулю 10 (если выбираем корабль) 2(если выбираем направление)
    //далее начинается игра....



    //аналогично прописываем методы для остальных кораблей

    private String ship1()
    {
        String text = textOutput.getText("ship0")+textOutput.getText("inputRule");
        if(Objects.equals(playerTurn, "0")){
        //условия если корабль в право или вниз, ошибка, если не влазит в поле
            // заполняем * корабль, поля вокруг корабля #
            //заполняем avar... всеми номерами полей, которые доступны дальше, то есть всеми (_)
        }
        return text;
    }

    private String ship0()
    {
        updateAvailableCommandGame();
        vectorShip = true;
        return textOutput.getText("ship0")+textOutput.getText("inputRule")+fieldOutput(player);
    }

    private String vector()
    {
        int firstCoor = first.get(playerTurn.charAt(0));
        int secondCoor = first.get(playerTurn.charAt(1));
        player[firstCoor][secondCoor] = "*";
        String text = fieldOutput(player)+textOutput.getText("vector");
        availableCommandGame.clear();
        availableCommandGame.add("0");
        availableCommandGame.add("1");
        shipNumber++;
        return text;
    }

    private void updateAvailableCommandGame(){
        availableCommandGame.clear();
        for(int i=0;i<10;i++)
            for(int j=0;j<10;j++)
                if(Objects.equals(player[i][j], "_"))
                    availableCommandGame.add(i+""+j+"");
    }

    private String fieldOutput(String[][] fieldInput)
    {
        String text = "  0 1 2 3 4 5 6 7 8 9\n";
        for(int i=0;i<10;i++)
        {
            text += i+"";
            for(int j=0;j<10;j++)
                text += fieldInput[i][j]+"";
            text += "\n";
        }
        return text;
    }

}
