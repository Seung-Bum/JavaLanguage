import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();

        //��й�ȣ
        String password = "hi12345678";
        //SHA256���� ��ȣȭ�� ��й�ȣ
        String cryptogram = sha256.encrypt(password);

        System.out.println(cryptogram);

        //��й�ȣ ��ġ ����
        System.out.println(cryptogram.equals(sha256.encrypt(password)));

    }

}

/*
  ��� ��
  3d939b8a32d9e0138935522f8c524b3fbe2d5cc39bf6a2c04805f890b11f3bdb
  true
  ��ó
  https://bamdule.tistory.com/233
*/
