# Задание 1.2 — Таблица модификаторов доступа

## Теоретическое напоминание

В Java существуют четыре уровня доступа:

| Модификатор | Свой класс | Тот же пакет | Наследник (другой пакет) | Весь мир |
|-------------|:----------:|:------------:|:------------------------:|:--------:|
| `private`   |     ✅     |      ❌      |           ❌             |    ❌    |
| *(без модификатора)* |  ✅  |   ✅   |           ❌             |    ❌    |
| `protected` |     ✅     |      ✅      |           ✅             |    ❌    |
| `public`    |     ✅     |      ✅      |           ✅             |    ✅    |

**Важно для этого задания:** класс `Employee` находится в пакете `company.core`, а класс `HRSystem` — в пакете `company.app`. Это **разные** пакеты. `HRSystem` **не** наследует `Employee`.

## Ваша таблица

Для каждой строки (A–H) из `HRSystem.java` укажите:

| Строка | Обращаемый член | Модификатор | Компилируется? (да/нет) | Причина |
|--------|----------------|-------------|:-----------------------:|---------|
| A | `emp.name` | public      |           да            | public доступен отовсюду|
| B | `emp.age` |      protected |           нет           |protected доступен только в том же пакете или наследникам; HRSystem в другом пакете и не наследует Employee |
| C | `emp.salary` |      (package-private) |           нет           | package-private доступен только в пакете company.core; HRSystem находится в company.app|
| D | `emp.password` |       private |           нет           | private доступен только внутри класса Employee|
| E | `emp.getRole()` |       public |           да            | public метод доступен отовсюду|
| F | `emp.promote(5000)` |      protected |           нет           | protected доступен только в том же пакете или наследникам; HRSystem в другом пакете и не наследует Employee|
| G | `emp.printSummary()` |      (package-private)       |           нет           | package-private доступен только в пакете company.core; HRSystem находится в company.app|
| H | `emp.validatePassword("secret")` |   private          |           нет           | 	private доступен только внутри класса Employee|
