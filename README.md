![Java](https://img.shields.io/badge/Java%20version-16-orange)
![Open issues](https://img.shields.io/github/issues-raw/neros2k/nTags)
---
[![Dropbox](https://img.shields.io/badge/nTags-Dropbox-blue)](https://www.dropbox.com/sh/0fkk7gimpp39nl4/AADIhpj9NI6kuJHdB3JIEWK9a?dl=0)
[![SpigotMC](https://img.shields.io/badge/nTags-SpigotMC-yellow)](https://www.spigotmc.org/resources/ntags-%E2%80%93-powerful-tool-for-management-nametags.101579/)
[![Trello](https://img.shields.io/badge/nTags-Trello-blue)](https://trello.com/c/MRT8SzEn/6-ntags)
[![CurseForge](https://img.shields.io/badge/nTags-CurseForge-green)](https://www.curseforge.com/minecraft/bukkit-plugins/ntags)

# Описание
**nTags** - плагин, позволяющий управлять именными тегами игроков на сервере. Имеет большое количество функционала, и активно развивается. Подойдет для любого РП-сервера, мини-игры, и пр. Кроме того, в проектировании плагина использовался паттерн ООП - PIDOR (presenter-interactor-decorator-object-repository), что делает его еще круче!

# Установка
Для того, чтобы установить плагин, потребуется лишь установить **.jar** файл в директорию плагинов вашего сервера (**/plugins**). Необходимые библиотеки/API: [JSONConfigAPI v1.0](https://github.com/neros2k/JSONConfigAPI). Плагин автоматически установится при запуске/перезагрузке сервера.
> Доступные версии: 1.17; 1.17.1; 1.18; 1.18.1; 1.18.2. Доступные ядра: Spigot, Paper, и прочие форки упомянутых.

# Особенности
- Удобный полный JSON конфиг.
- Сохранение через SQLite.
- Поддержка PlaceholerAPI.
- Удобные команды.
- Приятный вид сообщений "из коробки".
- Сообщение в ActionBar при нажатии ПКМ по игроку.
- Система прав.

# Команды
- **/tag** - Основная команда. Отправляет помощь по командам.
- **/tag help** - Аналогично с /tag отправляет помощь.
- **/tag reload** - Перезагрузить конфигурация плагина.
- **/tag hide {player}** - Скрыть тег игрока.
- **/tag show {player}** - Показать тег игрока.
- Кроме того, у основной команды есть альтернативы: **ntags, nt**

# Права
- **ntags.admin** - Все права
- **ntags.command.reload** - Права на команду /tag reload.
- **ntags.command.hide** - Права на команду /tag hide.
- **ntags.command.show** - Права на команду /tag show.

# Конфигурация
```json
{
  "HIDE_TAG_ON_JOIN": true,
  "ENABLE_CMDS": true,
  "ENABLE_AB_MSG": true,
  "SEND_AB_FROM_DSPLD_PLYR": false,
  "MESSAGES": {
    "AB_MSG": "§f%name%",
    "RELOAD_CMD": "§f§l[§bɴᴛᴀɢs§f§l]§f Plugin was successfully reloaded.",
    "HIDE_CMD": "§a▪§f %player% tag was successfully hidden.",
    "SHOW_CMD": "§a▪§f %player% tag was successfully displayed.",
    "PERM_ERR": "§c▪§f Error. Not enough permissions.",
    "UNK_CMD_ERR": "§c▪§f Error. Unknown command. Type: /tag help",
    "HIDE_CMD_ERR": "§c▪§f Error. Usage: /tag hide <player>",
    "SHOW_CMD_ERR": "§c▪§f Error. Usage: /tag show <player>",
    "HELP_MESSAGE": [
      "",
      "§f§l[§b▰▰▰▰▰§f§l]§f nTags v1.0 by neros2k §f§l[§b▰▰▰▰▰§f§l]",
      "§b►§f Command list:",
      "§b▪§f /tag §7(help)§f §b-§f Main command",
      "§b▪§f /tag reload §b-§f Reload nTags",
      "§b▪§f /tag show §7<player> §b-§f Show nametag",
      "§b▪§f /tag hide §7<player> §b-§f Hide nametag",
      "§b►§f Command aliases: §7[ntags, nt]",
      "§f§l[§b▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰▰§f§l]",
      ""
    ]
  }
}
```
