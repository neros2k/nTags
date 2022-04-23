![Java](https://img.shields.io/badge/Java%20version-16-orange)
![Open issues](https://img.shields.io/github/issues-raw/neros2k/nTags)

# Описание
**nTags** - плагин, позволяющий управлять именными тегами игроков на сервере. Имеет большое количество функционала, и активно развивается. Подойдет для любого РП-сервера, мини-игры, и пр. Кроме того, в проектировании плагина использовался паттерн ООП - **PIDOR** (presenter-interactor-decorator-object-repository), что делает его еще круче!

# Установка
Для того, чтобы установить плагин, потребуеться лишь положить **.jar** файл в директорию плагинов вашего сервера (**/plugins**). Плагин автоматически установится при запуске/перезагрузке сервера.
> Доступные версии: 1.17; 1.17.1; 1.18; 1.18.1; 1.18.2. Доступные ядра: Spigot, Paper, и прочие форки упомянутых.

# Особенности
- Удобный полный **JSON** конфиг.
- Сохранение через **SQLite**.
- Поддержка **PlaceholerAPI**.
- Удобные команды.
- Приятный вид сообщений "из коробки".
- **ActionBar** при нажатии ПКМ по игроку.
- Система прав.

# Команды
- **/tag** - Основная команда. Отправляет помощь по командам.
- **/tag help** - Аналогично с **/tag** отправляет помощь.
- **/tag reload** - Перезагрузить конфигурация плагина.
- **/tag hide {player}** - Скрыть тег игрока.
- **/tag show {player}** - Показать тег игрока.
- Кроме того, у имени команды есть альтернативы: **ntags, nt**

# Права
- **ntags.admin** - Все права
- **ntags.command.reload** - Права на команду **/tag reload**.
- **ntags.command.hide** - Права на команду **/tag hide**.
- **ntags.command.show** - Права на команду **/tag show**.
