package BotTraining;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String ans=Hello();
        do{
            ans = switch (ans) {
                case ("info") -> Info();
                case ("go") -> Go();
                case ("up") -> Up();
                case ("down") -> Down();
                case ("done") -> Done();
                default -> ans;
            };
        }while(!ans.equals("exit"));
        Exit();
    }

    static String Input(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    static String InputFilter(String ... FilterCriterion){
        String input=Input();
        switch (FilterCriterion.length){
            case (1):{
                while (!Objects.equals(input, FilterCriterion[0])){
                    ErrorInput();
                    input=Input();
                    break;
                }
            }
            case (2): {
                while (!Objects.equals(input, FilterCriterion[0]) && !Objects.equals(input, FilterCriterion[1])) {
                    ErrorInput();
                    input = Input();
                    break;
                }
            }
            case (3): {
                while (!Objects.equals(input, FilterCriterion[0]) && !Objects.equals(input, FilterCriterion[1])&& !Objects.equals(input, FilterCriterion[2])) {
                    ErrorInput();
                    input = Input();
                    break;
                }
            }
        }
        return input;
    }

    static void ErrorInput(){
        System.out.println("Не понимаю( Введи команду правильно (без пробелов, с маленькой буквы, без скобок или кавычек)");
    }

    // Command-----------------------------------------------------------

    static String Hello() {
        System.out.println("""
                Привет! Давай тренироваться!
                Введи:
                (go) чтобы начать,
                (info) если хочешь узнать побольше обо мне.""");
        return InputFilter("go","into");
    }

    static String Info(){
        System.out.println("""
                Давай знакомиться!
                Я твой личный фитнес-тренер и подберу тренировку, подходящую именно тебе.
                Введи:
                (go) чтобы начать,
                (exit) если не хочешь тренироваться""");
        return InputFilter("go","exit");
    }

    static String Go(){
        System.out.println("""
                Что ты хочешь потренировать сегодня?
                Введи:
                (up) если ты хочешь тренировать руки, спину и пресс,
                (down) - ноги и ягодицы.""");
        return InputFilter("up","down");
    }

    static String Up(){
        System.out.println("""
                1. Жим штанги лёжа - 3-4 подхода по 6-8 повторений.
                2. Тяга штанги к поясу - 3-4 подхода по 6-8 повторений.
                3. Жим штанги с груди стоя - 3-4 подхода по 8-12 повторений.
                4. Отжимания от брусьев - 3-4 подхода по 8-15 повторений.
                5. Подтягивания обратным хватом - 3-4 подхода по 8-15 повторений.
                6. Махи гантелями в стороны - 3-4 подхода по 10-15 повторений.
                У тебя всё получится!
                Пиши (done), когда сделаешь.""");
        return InputFilter("done");
    }

    static String Down(){
        System.out.println("""
                1. Приседания со штангой: 4 подхода по 6-8, 6-8, 8-10, 8-10 повторений
                2. Жим ногами: 3 подхода по 10-12 повторений без отдыха.
                3. Шагающие выпады с гантелями: 3 подхода по 10, 12, 14 шагов на каждую ногу.
                4. Подъемы на носки стоя: 4 подхода по 12, 12, 20, 20 повторений.
                5. Приседания с гирей (гоблет): 4 подхода по 10-12 повторений, отдых 90 секунд.
                6. Обратные выпады в тренажере Смита: 3 подхода по 10-12 повторений на каждую ногу без отдыха.
                У тебя всё получится!
                Напиши (done), когда сделаешь.""");
        return InputFilter("done");
    }

    static String Done(){
        System.out.println("""
                Ты молодец!
                Продолжим тренировку?
                (go) - хочу ещё,
                (exit) - заканчиваю
                (info) - хочу узнать о тебе больше""");
        return InputFilter("go","exit","info");
    }

    static void Exit(){
        System.out.println("Пока! Возвращайся:)");
    }
}