package UnixTimeToDateString;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class UnixTimeToDateString {
    public static void main(String[] args) {
        // ���� Unix �ð� ��������
        long unixTime = Instant.now().getEpochSecond();
        
        // Duration Ŭ������ ����Ͽ� �� �ð��� ���� ���
        //Duration duration = Duration.ofSeconds(endTimeUnix - startTimeUnix);

        // Unix �ð��� Instant ��ü�� ��ȯ
        Instant instant = Instant.ofEpochSecond(unixTime);

        // Instant�� LocalDateTime���� ��ȯ
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        // "yyyyMMdd" �������� ������
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = dateFormatter.format(localDateTime);

        // "HHmmss" �������� ������
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        String formattedTime = timeFormatter.format(localDateTime);

        // ��� ���
        System.out.println("Unix �ð�: " + unixTime);
        System.out.println("Formatted Date: " + formattedDate);
        System.out.println("Formatted Time: " + formattedTime);
    }
}
