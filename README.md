# Fitness_Trainer_Bot
учебный проект, написанный довольно криво

>> телеграмм бот фитнес-тренер! Подбирает тренировку в зависимости от цели
>> <br> + <br>
>> игра морской бой, если надоест тренироваться

## Задачи
* консольная версия чат-бота 
* оформление чат-бота в виде бота для Телеграмм
  * клавиатура в телеграмм
* использовать API You-Tube для изъятия ссылок на тренеровки
* игра морской бой с сохранением функций тренировок
* деплой телеграмм-бота в интернет


## Ход разработки: 
### Консоль_
* консольный процедурный бот + тесты ввода
* разбиение на объекты + интерфейс
### Телеграм_
* #### Бот тренеровки
   * пробная версия телеграм-бота
   * добавлена клавиатура
   * добавлен таймер
   * исправлены неполадки, разбиение на классы
   * изменена структура проекта, добавлены тесты на корректность ввода и вывода
   * расширен функционал, добавлены тренировки по видео (дома и в зале) на разные части тела
   * выделен класс Parsing: возвращает ссылку на первое видео с YouTube по введенному запросу
   * скорректированы тесты
* #### Игра морской бой
   * начало разработки игры морской бой
   * ввод кораблей игроком с клавиатуры с учетом правил
   * рандомная генерация поля бота с учетом правил
   * логика игры
   * готовая игра
   * тесты для игры
   * исправление неполадок


## Пример тренировок по видео и с ботом
<br>
<div>
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4671.PNG" width="250">
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4672.PNG" width="250">
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4673.PNG" width="250">
</div>
<br>

## Пример фрагментов игры
<br>
<div>
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4674.PNG" width="248">
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4675.PNG" width="248">
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4676.PNG" width="248">
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4677.PNG" width="248">
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4678.PNG" width="248">
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4679.PNG" width="248">
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4680.PNG" width="248">
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4681.PNG" width="248">
<img src="https://github.com/TanyashaVoron/WorkoutForYouBot/blob/master/imag/IMG_4682.PNG" width="248">
</div>

