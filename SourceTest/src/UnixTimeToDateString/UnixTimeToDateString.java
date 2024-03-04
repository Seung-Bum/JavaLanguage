package UnixTimeToDateString;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class UnixTimeToDateString {
    public static void main(String[] args) {
        // 현재 Unix 시간 가져오기
        long unixTime = Instant.now().getEpochSecond();
        
        // Duration 클래스를 사용하여 두 시간의 차이 계산
        //Duration duration = Duration.ofSeconds(endTimeUnix - startTimeUnix);

        // Unix 시간을 Instant 객체로 변환
        Instant instant = Instant.ofEpochSecond(unixTime);

        // Instant를 LocalDateTime으로 변환
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        // "yyyyMMdd" 형식으로 포맷팅
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = dateFormatter.format(localDateTime);

        // "HHmmss" 형식으로 포맷팅
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmmss");
        String formattedTime = timeFormatter.format(localDateTime);

        // 결과 출력
        System.out.println("Unix 시간: " + unixTime);
        System.out.println("Formatted Date: " + formattedDate);
        System.out.println("Formatted Time: " + formattedTime);
    }
}
