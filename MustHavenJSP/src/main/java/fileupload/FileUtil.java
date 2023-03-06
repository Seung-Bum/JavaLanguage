package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

public class FileUtil {
    // 파일 업로드 (multipart/form-data 요청) 처리
    public static MultipartRequest uploadFile(HttpServletRequest req,
            String saveDirectory, int maxPostSize) {
        try {
            // 파일 업로드
        	// requeset 내장객체, 파일이 저장될 디렉터리의 물리적 경로, 업로드할 파일의 최대 용량, 인코딩 방식
        	// MutipartRequest에 다음 파라미터를 넣고 생성하면 업로드됨
            return new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
        }
        catch (Exception e) {
            // 업로드 실패
            e.printStackTrace();
            return null;
        }
    }

    // 紐낆떆�븳 �뙆�씪�쓣 李얠븘 �떎�슫濡쒕뱶�빀�땲�떎.
    public static void download(HttpServletRequest req, HttpServletResponse resp,
            String directory, String sfileName, String ofileName) {
        String sDirectory = req.getServletContext().getRealPath(directory);
        try {
            // �뙆�씪�쓣 李얠븘 �엯�젰 �뒪�듃由� �깮�꽦
            File file = new File(sDirectory, sfileName);
            InputStream iStream = new FileInputStream(file);

            // �븳湲� �뙆�씪紐� 源⑥쭚 諛⑹�
            String client = req.getHeader("User-Agent");
            if (client.indexOf("WOW64") == -1) {
                ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            else {
                ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
            }

            // �뙆�씪 �떎�슫濡쒕뱶�슜 �쓳�떟 �뿤�뜑 �꽕�젙
            resp.reset();
            resp.setContentType("application/octet-stream");
            resp.setHeader("Content-Disposition",
                           "attachment; filename=\"" + ofileName + "\"");
            resp.setHeader("Content-Length", "" + file.length() );

            //out.clear();  // 異쒕젰 �뒪�듃由� 珥덇린�솕

            // response �궡�옣 媛앹껜濡쒕��꽣 �깉濡쒖슫 異쒕젰 �뒪�듃由� �깮�꽦
            OutputStream oStream = resp.getOutputStream();

            // 異쒕젰 �뒪�듃由쇱뿉 �뙆�씪 �궡�슜 異쒕젰
            byte b[] = new byte[(int)file.length()];
            int readBuffer = 0;
            while ( (readBuffer = iStream.read(b)) > 0 ) {
                oStream.write(b, 0, readBuffer);
            }

            // �엯/異쒕젰 �뒪�듃由� �떕�쓬
            iStream.close();
            oStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("�뙆�씪�쓣 李얠쓣 �닔 �뾾�뒿�땲�떎.");
            e.printStackTrace();
        }
        catch (Exception e) {
            System.out.println("�삁�쇅媛� 諛쒖깮�븯���뒿�땲�떎.");
            e.printStackTrace();
        }
    }

    // 吏��젙�븳 �쐞移섏쓽 �뙆�씪�쓣 �궘�젣�빀�땲�떎.
    public static void deleteFile(HttpServletRequest req,
            String directory, String filename) {
        String sDirectory = req.getServletContext().getRealPath(directory);
        File file = new File(sDirectory + File.separator + filename);
        if (file.exists()) {
            file.delete();
        }
    }
}
