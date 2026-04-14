package company.app;

import company.core.Employee;

/**
 * Задание 1.2 — Модификаторы доступа (тестовый класс)
 *
 * Задача: запустите этот файл. Он НЕ скомпилируется полностью — это нормально.
 *
 * Для каждой строки (A–H) определите:
 *   1. Скомпилируется ли она?
 *   2. Если нет — какой модификатор доступа и почему он не виден из company.app?
 *
 * Заполните таблицу в answers/task1_2_access_table.md.
 * Подсказка: закомментируйте строки, которые не компилируются,
 * чтобы запустить остальные.
 */
public class HRSystem {
    public static void main(String[] args) {
        Employee emp = new Employee("Иван", 30, 80000, "secret");

        System.out.println(emp.name);            // Строка A
        //System.out.println(emp.age);             // Строка B
        //System.out.println(emp.salary);          // Строка C
        // System.out.println(emp.password);        // Строка D
        System.out.println(emp.getRole());       // Строка E
        // emp.promote(5000);                       // Строка F
        // emp.printSummary();                      // Строка G
        // emp.validatePassword("secret");          // Строка H
   /*
Строка | Компилируется? | Почему?
A      | Да             | name — public, доступен отовсюду
B      | Нет            | age — protected, доступен в том же пакете + наследникам; HRSystem в другом пакете и не наследует Employee
C      | Нет            | salary — package-private, доступен только в пакете company.core; HRSystem в company.app
D      | Нет            | password — private, доступен только внутри класса Employee
E      | Да             | getRole() — public, доступен отовсюду
F      | Нет            | promote() — protected, доступен в том же пакете + наследникам; HRSystem в другом пакете и не наследует Employee
G      | Нет            | printSummary() — package-private, доступен только в пакете company.core
H      | Нет            | validatePassword() — private, доступен только внутри класса Employee
    */

    }
}