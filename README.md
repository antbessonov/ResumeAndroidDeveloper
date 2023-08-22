# Резюме Android-разработчика

Приложения для демонстрации потенциальному работодателю актуальных технических навыков.

>Стек: Kotlin, Retrofit2, Room, Hilt, Kotlin Coroutines

## Основные функции

- Отображение списка профессиональных навыков
- Демонстрация отзывов и рекомендации
- Получение каталога публичных репозиториев из GitHub
- Представление законченных образовательных программ с возможностью фильтрации
- Просмотр контактных данных

## Описание работы приложения

Приложение состоит из следующих основных экранов:
- "Главная" - приветствие, список навыков и рекомендации,
- "Проекты" - репозитории из GitHub,
- "Образование" - образовательные программы,
- "Контакты" - контакты для связи с автором.

### Главная
При запуске приложения пользователя встречает приветственная анимация в форме диалога.

<p align="center">
<img src="https://github.com/antbessonov/ResumeAndroidDeveloper/blob/master/screenshot/about_me.gif?raw=true" width="400">
</p>

При дальнейшем скроллинге отображается сгруппированный список навыков, который храниться в локальной базе данных.
В конце экрана находиться раздел со всеми профессиональными рекомендациями. После просмотра которого, есть возможность оставить дополнительный отзыв.

<p align="center">
<img src="https://github.com/antbessonov/ResumeAndroidDeveloper/blob/master/screenshot/about_me_1.gif?raw=true" width="400">
</p>

### Проекты
В разделе отображаются актуальные публичные репозитории, опубликованные автором. Для получения списка используется [REST API GitHub](https://docs.github.com/ru/rest/repos/repos?apiVersion=2022-11-28#list-repositories-for-a-user). Полученная информация сохраняется в локальной базе данных.

<p align="center">
<img src="https://github.com/antbessonov/ResumeAndroidDeveloper/blob/master/screenshot/project.gif?raw=true" width="400">
</p>

При первом запуске приложения с отсутствующем интернет соединением, на экране отобразиться соответсвующее сообщение.

<p align="center">
<img src="https://github.com/antbessonov/ResumeAndroidDeveloper/blob/master/screenshot/project_1.gif?raw=true" width="400">
</p>

### Образование
Образовательные программы храниться в локальной базе данных. Список возможно отфильтровать по двум параметрам "основное" и "дополнительное".

<p align="center">
<img src="https://github.com/antbessonov/ResumeAndroidDeveloper/blob/master/screenshot/education.gif?raw=true" width="400">
</p>

### Контакты
Во вкладке "Контакты" изначально отображается предпочтительный вид для связи.
При клике на "Показать все контакты" появиться дополнительная информация, в том числе и место нахождения автора.

<p align="center">
<img src="https://github.com/antbessonov/ResumeAndroidDeveloper/blob/master/screenshot/contact.gif?raw=true" width="400">
</p>

Если интернет соединение отсутствует, вместо карты будет отображаться заглушка с информационным сообщением и кнопка с возможностью обновить информацию.

<p align="center">
<img src="https://github.com/antbessonov/ResumeAndroidDeveloper/blob/master/screenshot/contact_1.gif?raw=true" width="400">
</p>

### Дополнительно
Приложение поддерживает темную тему.

<p align="center">
<img src="https://github.com/antbessonov/ResumeAndroidDeveloper/blob/master/screenshot/dark_theme.gif?raw=true" width="400">
</p>
