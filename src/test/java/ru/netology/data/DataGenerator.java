package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class Registration {
        public DataGeneratorForm generateByForm(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return new DataGeneratorForm(
                    faker.address().city(),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber()
            );
        }
    }


    public static String city() {
        String[] bigCity = new String[]{"Майкоп", "Горно-Алтайск", "Уфа", "Улан-Удэ", "Махачкала",
                "Магас", "Нальчик", "Элиста", "Черкесск", "Казань", "Кызыл", "Ижевск", "Абакан",
                "Петрозаводск", "Сыктывкар", "Симферополь", "Йошкар-Ола", "Саранск", "Якутск", "Владикавказ",
                "Грозный", "Чебоксары", "Барнаул", "Чита", "Петропавловск-Камчатский", "Краснодар",
                "Красноярск", "Пермь", "Владивосток", "Ставрополь", "Хабаровск", "Благовещенск",
                "Архангельск", "Астрахань", "Белгород", "Брянск", "Вологда", "Воронеж", "Калининград",
                "Киров", "Гатчина", "Москва", "Санкт-Петербург"};
        int randCity = (int) Math.floor(Math.random() * bigCity.length);
        return bigCity[randCity];
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}