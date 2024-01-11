[![Actions Status](https://github.com/vmanannikov/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/vmanannikov/java-project-71/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/646be42acbc7897fa303/maintainability)](https://codeclimate.com/github/vmanannikov/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/ea713e0ba86518f1a299/test_coverage)](https://codeclimate.com/github/vmanannikov/java-project-71/test_coverage)

# Вычислитель отличий

Утилита для сверки различий между файлами:
- JSON
- YAML

Отображение результа в форматах:
- Plain (текст с описанием изменений по ключам)
- Stylish (формат для отображения по аналогии с git)
- JSON

## Как пользоваться
```
  gendiff [-hV] [-f=format] filepath1 filepath2   
- filepath1            Путь к первому файлу   
- filepath2            Путь ко второму файлу  
- f, --format=format   Формат вывода [default: stylish]  
- h, --help            Получения информации об использовании команды.  
- V, --version         Вывести версию
```

## Запуск приложения
```sh
make --directory app/ gendiff format=<format> f1=<filepath1> f2=<filepath2>
```

## Запуск локально

Клонируем проект

```bash
  git clone https://github.com/vmanannikov/java-project-71
```

Переходим в каталог проекта

```bash
  cd java-project-71/app
```

Собираем приложение

```bash
  make install
```

## Примеры
- сравнение YAML файлов (пример вывода Stylish, Plain, JSON)

[![YAML](https://asciinema.org/a/AgHyq76GsCs22yoSnJ20vKTqL.svg)](https://asciinema.org/a/AgHyq76GsCs22yoSnJ20vKTqL)

- сравнение JSON файлов (пример вывода Stylish, Plain, JSON)

[![JSON](https://asciinema.org/a/KzgvFvt45zpjsentwVMBUh9LG.svg)](https://asciinema.org/a/KzgvFvt45zpjsentwVMBUh9LG)








