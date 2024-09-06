package com.example.JJDTask;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public class InsuranceSubmission {
    private static boolean isWeekend(Calendar calendar) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
    }

    public static Date getVacCheck(Date modDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(modDate);

        while (isWeekend(calendar)) {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }

        return new Date(calendar.getTimeInMillis());
    }

    public static Date getNextSubmissionDate(Date currentDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int[] submissionDays = {1, 10, 20};
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        for (int day : submissionDays) {
            if (currentDay <= day) {
                calendar.set(Calendar.DAY_OF_MONTH, day);
                break;
            }
        }

        if (calendar.get(Calendar.DAY_OF_MONTH) < currentDay) {
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, submissionDays[0]);
        }

        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date submissionDate = new Date(calendar.getTimeInMillis());
        return getVacCheck(submissionDate);
    }

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2024, 9, 7);
        Date currentDate = Date.valueOf(localDate);
        Date nextSubmissionDate = getNextSubmissionDate(currentDate);

        System.out.println("Ближайшая дата отправки: " + nextSubmissionDate);
    }
}