import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();

        //비밀번호
        String password = "hi12345678";
        //SHA256으로 암호화된 비밀번호
        String cryptogram = sha256.encrypt(password);

        System.out.println(cryptogram);

        //비밀번호 일치 여부
        System.out.println(cryptogram.equals(sha256.encrypt(password)));

    }

}

/*
  결과 값
  3d939b8a32d9e0138935522f8c524b3fbe2d5cc39bf6a2c04805f890b11f3bdb
  true

  출처
  https://bamdule.tistory.com/233
*/

