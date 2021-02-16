package ru.geekbrains.level2.lesson1.balyanova;

public class MainApp {
    public static void main(String[] args) {
        Robot r2D2 = new Robot("R2D2", 50, 2);
        Robot bostonDynamics = new Robot("Boston Dynamics", 2000, 5);
        Human steave = new Human("Steave", 1000, 1);
        Human tom = new Human("Tom", 2000, 1);
        Cat barsik = new Cat("Barsik", 500, 3);
        Cat pushok = new Cat("Pushok", 100, 1);

        Treadmill treadmill1 = new Treadmill(100);
        Treadmill treadmill2 = new Treadmill(1000);

        Wall wall1 = new Wall(2);
        Wall wall2 = new Wall(1);

//        robot.run(); //проверка
//        human.jump(); //код больше не работает
//        cat.jump();
//
//        treadmill1.check(cat);
//        treadmill1.check(robot);
//        treadmill1.check(human);
//
//        wall1.check(human);
//        wall1.check(cat);
//        wall1.check(robot);

        Entity[] entities = {r2D2, bostonDynamics, steave, tom, barsik, pushok}; // массив из участников

        Barrier[] barriers = {wall1, wall2, treadmill1, treadmill2}; //массив из препятствий
        for (int i = 0; i < barriers.length; i++) {
            for (Entity entity : entities) {
                if (entity.getSuccess()) { //отсекаем всех, кто не прошел препятствие
                    barriers[i].check(entity);
                    if (!entity.getSuccess()) { //печатаем выбывших
                        System.out.println(entity.getName() + "  dropped out!");
                    }
                }
            }
            for (Entity entity : entities) {
                if (entity.getSuccess()) {
                    System.out.println(entity.getName() + " is on distance");
                }
            }
        }
    }
}
/*

1. Создайте три класса Человек, Кот, Робот,которые не наследуются от одного класса.
Эти классы должны уметь бегать и прыгать (методы просто выводят информацию о действии в консоль).

2. Создайте два класса: беговая дорожка и стена,
при прохождении через которые, участники должны выполнять
соответствующие действия (бежать или прыгать),
результат выполнения печатаем в консоль
(успешно пробежал, не смог пробежать и т.д.).

3. Создайте два массива: с участниками и препятствиями,
 и заставьте всех участников пройти этот набор препятствий.

4. * У препятствий есть длина (для дорожки) или высота (для стены),
 а участников ограничения на бег и прыжки.
  Если участник не смог пройти одно из препятствий,
  то дальше по списку он препятствий не идет.
 */