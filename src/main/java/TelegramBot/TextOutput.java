package TelegramBot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class TextOutput
{
    private final Map<String, String> mapOutput = new HashMap<>();
    private final ArrayList<String> workoutVideo  = new ArrayList<>();

    public TextOutput()
    {
        mapOutput.put("start", """
                Привет! Давай тренироваться!
                Введи:
                (game) если хочешь поиграть в игру морской бой
                (with you) если хочешь тренироваться со мной,
                (video) если хочешь тренироваться по видео,
                (info) если хочешь узнать побольше обо мне.""");
        mapOutput.put("info", """
                Давай знакомиться!
                Я твой личный фитнес-тренер и подберу тренировку, подходящую именно тебе.
                Введи:
                (game) если хочешь поиграть в игру морской бой
                (with you) если хочешь тренироваться со мной,
                (video) если хочешь тренироваться по видео,
                (exit) если не хочешь тренироваться""");
        mapOutput.put("with you", """
                Что ты хочешь потренировать сегодня?
                Введи:
                (up) если ты хочешь тренировать руки, спину и пресс,
                (down) - ноги и ягодицы.
                (exit) если хочешь выйти""");
        mapOutput.put("up","""
                1. Бабочка лежа на животе.
                2. Поднятие корпуса лежа с согнутыми в коленях ногами.
                3. Скручивания лежа.
                4. Отжимания с шагом в сторону.
                5. Обратные отжимания.
                6. Планка со сменой рук.""");
        mapOutput.put("down", """
                1. Велосипед.
                2. Ягодичный мост в статике.
                3. Присед, руки касаются пола.
                4. Выпады назад.
                5. Альпинист.
                6. Приведение бедра для внутренней части бедра.""");
        mapOutput.put("done", """
                Ты молодец!
                Продолжим тренировку?
                (game) если хочешь поиграть в игру морской бой
                (with you) если все еще хочешь тренироваться со мной,
                (video) если хочешь тренироваться по видео,
                (exit) - заканчиваю
                (info) - хочу узнать о тебе больше""");
        mapOutput.put("lastEx", """
                Так держать! Осталось последнее упражнение!
                Пиши (done) когда закончишь!""");
        mapOutput.put("next","""
                Время вышло!
                (next) следующее упражнение!
                (exit) если хочешь прервать тренеровку и выйти""");
        mapOutput.put("time","У тебя 60 секунд!");
        mapOutput.put("exit", "Пока! Возвращайся:)");
        mapOutput.put("error", "Ошибка ввода");
        mapOutput.put("video", """
                Где будем тренироваться?
                (home) - дома
                (gym) - в зале
                (exit) если хочешь выйти""");
        mapOutput.put("home","""
                Какую тренировку ты хочешь?
           
                (full body home) - на все тело
             
                (arm home) - руки
                (press) - пресс
                (butt home) - ягодицы
                (leg home) - ноги
                
                (exit) если хочешь выйти""" );
        mapOutput.put("gym","""
                Какую тренировку ты хочешь?
                
                (full body gym) - на все тело
                
                (arm gym) - руки
                (press) - пресс
                (butt gym) - ягодицы
                (leg gym) - ноги
                
                (exit) если хочешь выйти""" );
        mapOutput.put("workoutVideo","""
                Держи тренировку!
                Пиши (done) когда закончишь!""");
        mapOutput.put("full body home", "тренировка на все тело дома");
        mapOutput.put("full body gym", "тренировка на все тело в зале");
        mapOutput.put("arm home", "тренировка на на руки дома");
        mapOutput.put("arm gym", "тренировка на руки в зале");
        mapOutput.put("press", "тренировка на пресс");
        mapOutput.put("butt home", "тренировка на ягодицы дома");
        mapOutput.put("butt gym", "тренировка на ягодицы в зале");
        mapOutput.put("leg home", "тренировка на ноги дома");
        mapOutput.put("leg gym", "тренировка на ноги в зале");

        workoutVideo.add("full body home");
        workoutVideo.add("full body gym");
        workoutVideo.add("arm home");
        workoutVideo.add("arm gym");
        workoutVideo.add("press");
        workoutVideo.add("butt home");
        workoutVideo.add("butt gym");
        workoutVideo.add("leg home");
        workoutVideo.add("leg gym");

        mapOutput.put("ship0", "Бот уже расставил корабли, теперь твоя очередь!\nПервый корабль 4-х палубный\n");
        mapOutput.put("inputRule","Введи две цифры от 0 до 9 без пробелов (сначала координата по оy, потом по оx)\n");
        mapOutput.put("vector","Молодец! выбери направление корабля, введи: \n 0 - по вертикали вниз \n 1 - по горизонтали в право\n");
        mapOutput.put("ship1","Круто! Следующий корабль 3-х палубный \nУчти, что нельзя ставить корабли рядом!\n");
        mapOutput.put("ship2","Выбери место для еще одного 3-х палубного корабля\n");
        mapOutput.put("ship3","Теперь 2-х палубные. Их всего будет три. Давай поставим первый!\n");
        mapOutput.put("ship4","Второй 2-х палубный\n");
        mapOutput.put("ship5","Последний 2-х палубный\n");
        mapOutput.put("ship6","Ууу еще целых 4 одиночных корабля осталось. первый!\n");
        mapOutput.put("ship7","Второй\n");
        mapOutput.put("ship8","Третий\n");
        mapOutput.put("ship9","Ииии Последний корабль!\n");
        mapOutput.put("shipError","ой, здесь нельзя ставить корабль. давай попробуем снова!\n");
        mapOutput.put("goingOutOfBounds","упс, корабль не войдет в границы поля. давай попробуем снова!\n");

        mapOutput.put("firstMessage", "Начинаем игру!\n");
        mapOutput.put("x","Попал! так держать!\nСнова твой ход!\n");
        mapOutput.put("_","Эх, мимо\n");
        mapOutput.put("win","Победа");
    }

    public String getText(String command) { return mapOutput.get(command); }
    public Boolean getWorkoutVideo(String command) { return workoutVideo.contains(command); }
}