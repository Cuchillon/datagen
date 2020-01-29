## Генератор данных Datagen

#### Описание
Генератор данных предназначен для замещения шаблонов в передаваемой строке сгенерированными случайными значениями, датой/временем в настоящем, прошлом и будущем, а также переданными значениями.

#### Сборка
Для использования необходимо клонировать репозиторий и выполнить следующие команды в корне проекта:
```
./gradlew shadowJar
cd build/libs
```
затем из build/libs скопировать datagen-0.1-lib.jar и использовать как библиотеку в своих тестах.

#### Пример использования

Передаваемая строка:
```
{
  id: ${-random num 7-},
  description: "${-random alph 8-}",
  password: "${-random alphnum 10-}",
  price: ${-random double 4.2-},
  other: "hardcore value",
  current_date: "${-date now millisec-}",  
  begin_date: "${-date past 40 min iso8601-}",
  end_date: "${-date future 30 day iso8601-}",
  name: "${-set name-}",
  person: [
    {
      first_name: "${-random name-}"
    },
    {
      last_name: "${-random name-}"
    },
    {
      profession: "hardcore job"
    },
    {
      email: "${-set person.email-}"
    }
  ]
}
```

Возвращаемая строка:
```
{
  id: 1544012,
  description: "BuIQCMkd",
  password: "0KO3ENI6mX",
  price: 2071.24,
  other: "hardcore value",
  current_date: "1574607664244",  
  begin_date: "2019-11-24T17:21:04",
  end_date: "2019-12-24T18:01:04",
  name: "loafer",
  person: [
    {
      first_name: "Kixono"
    },
    {
      last_name: "Rocunoq"
    },
    {
      profession: "hardcore job"
    },
    {
      email: "loafer@example.com"
    }
  ]
}
```

#### Доступные для использования методы в шаблонах

###### Случайное значение:
```
${-random alph 8-}
${-random alphnum 10-}
${-random num 7-}
${-random double 4.2-}
${-random name-}
```
* **random** - метод для генерации случайного значения
* **alph** - тип значения alphabetic
* **alphnum** - тип значения alphanumeric
* **num** - тип значения numeric
* **double** - тип значения 'число с плавающей точкой'
* **name** - генерация имени длиной от 4-х до 8-ми символов из случайных сочетаний слогов
* **8** - количество символов в генерируемом значении
* **4.2** - количество символов в числе double (нули впереди числа обрезаются, например сгенерированное число 0346.18 передается как 346.18)

###### Дата:
```
${-date now millisec-}
${-date past 900 sec iso8601-}
${-date future 30 day iso8601-}
```
* **date** - метод для генерации даты/времени
* **now** - вернуть текущее время
* **past 900 sec** - вернуть время 900 секунд назад от текущего 
* **future 30 day** - вернуть время 30 дней вперед от текущего
* **30 day** - количество и ед.измерения времени. Доступные единицы: sec, min, hour, day 
* **millisec**  - вернуть время в формате миллисекунд
* **iso8601**  - вернуть время в формате "2019-11-24T17:21:04"

###### Вставка значения:
```
${-set name-}
${-set person.email-}
```
* **set** - метод для вставки по ключу передаваемого значения
* **name** - ключ, по которому возвращается значение. Если ключ не найден, возвращается пустая строка

###### Комбинирование

Сгенерированное имя можно сохранить указав ключ и позже вставить его как значение (сохраняется toLowerCase):
```
{ "person": "${-random name email-}", "email": "${-set email-}@example.com" }
```
Результат:
```
{ "person": "Bili", "email": "bili@example.com" }
```

#### Примеры
###### Для использования толлько с методами random и date, без set
```
Datagen datagen = new Datagen();
String result = datagen.get(template);
```
###### Для использования с методами random, date и set
```
Datagen datagen = new Datagen();
Map<String, String> substituteValues = new HashMap<>();
substituteValues.put("name", "value");
String result = datagen.get(template, substituteValues);
```
либо
```
Datagen datagen = new Datagen();
String result = datagen.get(template, "name", "value");
```
###### Вернуть список строк в количестве 10 шт.
 ```
 Datagen datagen = new Datagen();
 List<String> resultList = datagen.get(10, template);
 ```
либо
```
Datagen datagen = new Datagen();
Map<String, String> substituteValues = new HashMap<>();
substituteValues.put("name", "value");
List<String> resultList = datagen.get(10, template, substituteValues);
```
либо
```
Datagen datagen = new Datagen();
List<String> resultList = datagen.get(10, template, "name", "value");
```
