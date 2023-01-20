package utils;

public class BoardPage {
    public static String pagingStr(int totalCount, int pageSize, int blockPage,
            int pageNum, String reqUrl) {
        String pagingStr = "";

        // 단계3 : 전체 페이지 수 계산
        // 남는 게시물도 페이지 안에 포함될 수 있게
        // pageSize : 한페이지에 출력할 게시물 수
        // totalCount : 전체 게시물 수
        int totalPages = (int) (Math.ceil(((double) totalCount / pageSize)));

        // 단계4 : '이전 페이지 블록 바로가기' 출력
        // pageNum : 현재 페이지
        // blockPage : 블록에 들어갈 페이지 수
        int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
        // 첫 페
        if (pageTemp != 1) {
            pagingStr += "<a href='" + reqUrl + "?pageNum=1'>[첫 페이지]</a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + (pageTemp - 1)
                         + "'>[이전 블록]</a>";
        }

        // 단계5 : 각 페이지 번호 출력
        int blockCount = 1;
        while (blockCount <= blockPage && pageTemp <= totalPages) {
            if (pageTemp == pageNum) {
                // 현재 페이지는 링크를 걸지 않음
                pagingStr += "&nbsp;" + pageTemp + "&nbsp;";
            } else {
                pagingStr += "&nbsp;<a href='" + reqUrl + "?pageNum=" + pageTemp
                             + "'>" + pageTemp + "</a>&nbsp;";
            }
            pageTemp++;
            blockCount++;
        }

        // �떒怨� 6 : '�떎�쓬 �럹�씠吏� 釉붾줉 諛붾줈媛�湲�' 異쒕젰
        if (pageTemp <= totalPages) {
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + pageTemp
                         + "'>[�떎�쓬 釉붾줉]</a>";
            pagingStr += "&nbsp;";
            pagingStr += "<a href='" + reqUrl + "?pageNum=" + totalPages
                         + "'>[留덉�留� �럹�씠吏�]</a>";
        }

        return pagingStr;
    }
}
