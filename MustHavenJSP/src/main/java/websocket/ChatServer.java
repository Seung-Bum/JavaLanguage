package websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// 채팅 서버는 접속한 클라이언트의 세션 정보를 관리하면서 메시지를 보낸 클라이언트를 제외한 나머지에게만 에코하느 형태로 구현
// ws://호스트:포트번호/컨텍스트루트/chatingServer

@ServerEndpoint("/ChatingServer") //웹소켓 서버의 요청명을 지정, 해당 요청명으로 접속하는 클라이언트를 이 클래스가 처리하게함
public class ChatServer {
    private static Set<Session> clients
            = Collections.synchronizedSet(new HashSet<Session>()); 
    // 여러 클라이언트가 접속해도 문제없도록 조치
    // synchronizedSet() 메서드는 멀티스레드 환경에서도 안전한 Set 컬렉션을 생성해준다.

    @OnOpen  // 클라이언트 접속시 실행
    public void onOpen(Session session) {
        clients.add(session);  // 세션추가
        System.out.println("웹소켓 연결" + session.getId());
    }

    @OnMessage  // 메시지를 받으면 실행
    public void onMessage(String message, Session session) throws IOException {
        System.out.println("메시지 전송 : " + session.getId() + ":" + message);
        synchronized (clients) {
            for (Session client : clients) {  // 모든 클라이언트에 메시지 전달
                if (!client.equals(session)) {  // 단, 메시지를 보낸 클라이언트는 제외하고 전달 (session이 보낸 클라이언트)
                    client.getBasicRemote().sendText(message);
                }
            }
        }
    }

    @OnClose  // 클라이언트와의 연결이 끊기면 실행
    public void onClose(Session session) {
        clients.remove(session); 
        System.out.println("웹소켓 종료 : " + session.getId());
    }

    @OnError  // 에러 발생 시 실행
    public void onError(Throwable e) {
        System.out.println("에러 발생");
        e.printStackTrace();
    }
}
