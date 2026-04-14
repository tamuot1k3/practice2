# Часть 9 — Эксперименты в jshell

## Как запустить jshell

Откройте терминал IntelliJ (View → Tool Windows → Terminal) и введите:
```
jshell
```
Для выхода: `/exit`

---

## Задание 9.1: Sealed-классы

### Команды (скопируйте и вставьте в jshell)

```
sealed interface Shape permits Circle, Square {}
record Circle(double r) implements Shape {}
record Square(double side) implements Shape {}
Shape s = new Circle(5)
s instanceof Circle c ? "Круг r=" + c.r() : "Не круг"
```

### Фактический вывод:

```
jshell> sealed interface Shape permits Circle, Square {}
|  created interface Shape

jshell> record Circle(double r) implements Shape {}
|  created record Circle

jshell> record Square(double side) implements Shape {}
|  created record Square

jshell> Shape s = new Circle(5)
s ==> Circle[r=5.0]

jshell> s instanceof Circle c ? "Круг r=" + c.r() : "Не круг"
$5 ==> "Круг r=5.0"
```

### Вопрос: Что произойдёт при попытке создать `record Triangle(double a) implements Shape {}`?

**Ваш ответ:**
Ошибка компиляции. Интерфейс Shape объявлен как sealed с permits Circle, Square — это значит, что только Circle и Square могут его реализовывать. Triangle не указан в списке permits, поэтому компилятор запретит его создание.


---

## Задание 9.2: Цепочка лямбд

### Команды

```
import java.util.function.*
Function<String, String> trim = String::trim
Function<String, String> upper = String::toUpperCase
Function<String, String> exclaim = s -> s + "!"
var pipeline1 = trim.andThen(upper).andThen(exclaim)
var pipeline2 = exclaim.compose(upper).compose(trim)
pipeline1.apply("  hello world  ")
pipeline2.apply("  hello world  ")
```

### Фактический вывод:

```
jshell> pipeline1.apply("  hello world  ")
$7 ==> "HELLO WORLD!"

jshell> pipeline2.apply("  hello world  ")
$8 ==> "HELLO WORLD!"
```

### Вопрос: Дают ли `andThen()` и `compose()` одинаковый результат? В каком случае результаты будут различаться?

**Ваш ответ:**
В данном случае результаты одинаковы, потому что порядок применения функций совпадает: trim → upper → exclaim. andThen() выполняет функции слева направо (f.andThen(g) = сначала f, потом g), а compose() — справа налево (f.compose(g) = сначала g, потом f). Результаты будут различаться, если изменить порядок вызовов так, что итоговая последовательность функций окажется другой. Например, если бы exclaim выполнялся до upper, то восклицательный знак не был бы в верхнем регистре: "HELLO WORLD!" vs "HELLO WORLD!".


---

## Задание 9.3: Сравнение EnumSet и HashSet

### Команды

```
enum Color { RED, GREEN, BLUE, YELLOW, CYAN, MAGENTA, WHITE, BLACK }
var enumSet = java.util.EnumSet.of(Color.RED, Color.GREEN, Color.BLUE)
var hashSet = new java.util.HashSet<>(java.util.Set.of(Color.RED, Color.GREEN, Color.BLUE))
enumSet.contains(Color.RED)
hashSet.contains(Color.RED)
enumSet.getClass().getSimpleName()
hashSet.getClass().getSimpleName()
```

### Фактический вывод:

```
jshell> enumSet ==> [RED, GREEN, BLUE]
jshell> hashSet ==> [BLUE, GREEN, RED]
jshell> enumSet.contains(Color.RED) ==> true
jshell> hashSet.contains(Color.RED) ==> true
jshell> enumSet.getClass().getSimpleName() ==> "RegularEnumSet"
jshell> hashSet.getClass().getSimpleName() ==> "HashSet"
```

### Вопрос: Почему внутренний класс EnumSet называется `RegularEnumSet`? Что произойдёт, если enum будет иметь больше 64 констант?

**Ваш ответ:**
RegularEnumSet использует одно значение long (64 бита) как битовую маску, где каждый бит соответствует одной константе enum по её ordinal(). Это обеспечивает O(1) операции contains/add/remove через побитовые операции. Если enum имеет более 64 констант, JVM автоматически использует JumboEnumSet, который хранит массив long[] для поддержки большего количества бит. 
