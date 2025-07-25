Тестовые классы размещены в директории **app/src/androidTest/kotlin/ru/tinkoff/favouritepersons/tests**. Она включает в себя следующие классы:

- **AddDuplicateTest**: Добавление студента-дубликата (https://kursqamobile20252.testrail.io/index.php?/cases/view/42)
- **AddFromCloudTest**: Добавление студента из Сети (https://kursqamobile20252.testrail.io/index.php?/cases/view/16)
- **AddFromCloudWithoutNetworkTest**: Добавление студента из Сети при отсутствии Интернет-соединения (https://kursqamobile20252.testrail.io/index.php?/cases/view/44)
- **AddScreenAfterMinimizeTest**: Сохранение введенных данных на экране "Добавление пользователя" после сворачивания приложения (https://kursqamobile20252.testrail.io/index.php?/cases/view/31)
- **AddTest**: Добавление студента вручную (https://kursqamobile20252.testrail.io/index.php?/cases/view/36)
- **AddWithoutBirthdateTest**: Добавление студента без даты рождения (https://kursqamobile20252.testrail.io/index.php?/cases/view/39)
- **AddWithoutNameTest**: Добавление студента без имени (https://kursqamobile20252.testrail.io/index.php?/cases/view/37)
- **AddWithoutScoreTest**: Добавление студента без итогового балла (https://kursqamobile20252.testrail.io/index.php?/cases/view/40)
- **AddWithoutSurnameTest**: Добавление студента без фамилии (https://kursqamobile20252.testrail.io/index.php?/cases/view/38)
- **EditDuplicateTest**: 	Создание дубликата при редактировании студента (https://kursqamobile20252.testrail.io/index.php?/cases/view/53)
- **EditScreenAfterMinimizeTest**: Сохранение введенных данных на экране "Редактирование пользователя" после сворачивания приложения (https://kursqamobile20252.testrail.io/index.php?/cases/view/50)
- **EditTest**: Редактирование студента (https://kursqamobile20252.testrail.io/index.php?/cases/view/51)
- **EditWithoutBirthdateTest**: Сохранение студента без даты рождения (https://kursqamobile20252.testrail.io/index.php?/cases/view/56)
- **EditWithoutNameTest**: Сохранение студента без имени (https://kursqamobile20252.testrail.io/index.php?/cases/view/54)
- **EditWithoutScoreTest**: Сохранение студента без итогового балла (https://kursqamobile20252.testrail.io/index.php?/cases/view/57)
- **EditWithoutSurnameTest**: Сохранение студента без фамилии (https://kursqamobile20252.testrail.io/index.php?/cases/view/55)
- **OpenStudentCardTest**: 	Открытие карточки студента (https://kursqamobile20252.testrail.io/index.php?/cases/view/25)
- **ScrollListTest**: Проскролливание списка студентов (https://kursqamobile20252.testrail.io/index.php?/cases/view/32)
- **DeleteTest**: Удаление студента из списка (https://kursqamobile20252.testrail.io/index.php?/cases/view/19)

При проектировании тестов был использован паттерн PageObject. В объектах были описаны элементы экранов, которые использовались в тестах. Объекты с описанием экранов расположены в директории **app/src/androidTest/kotlin/ru/tinkoff/favouritepersons/screens**. Были использованы следующие объекты:

- StudentsListScreen: экран "Список студентов"
- StudentDetailsScreen: экран "Карточка студента"

Для взаимодействия с SharedPreferences приложения был использован объект **Prefs**, который находится в директории **app/src/androidTest/kotlin/ru/tinkoff/favouritepersons/**.

Для мокирования данных был использован **WireMock**. Заглушки в формате Json для ответов сервера расположены в директории **app/src/main/assets/**. Для преобразования файлов json в String была реализована функция **readJsonFileFromAssets**, являющаяся частью объекта **CommonFunc**, расположенного в директории **app/src/androidTest/kotlin/ru/tinkoff/favouritepersons**.
