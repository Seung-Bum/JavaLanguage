자바 콜렉션과 제네릭

콜렉션 - 데이터를 수집하고 관리해주는 객체, 가변길이 배열이다.

왜 사용하는가?
데이터관리를 직접 할 필요가 없다.
공간의 크기를 가변적으로 사용할 수 있다.

모든 데이터를 목록으로 관리할 수 있는 자료형식이 있나요?
데이터 타입이 다 다르기 때문에 문제가 된다.
모든 클래스는 Object이다. 그럼 primitive 형식과의 관계는?
자바에서 모든 클래스는 Object를 상속하게 되어있다.
Object 형식으로 배열을 만들면 모두 참조할 수 있다.

모든 객체를 묶을 수 있는 범용 자료형식

하지만 값을 참조 할 수가 없다.

그래서 등장한
Wrapper 클래스와 Auto Boxing / UnBoxing

Boxing
Object obj = new Integer(3);

=> UnBoxing
int x = obj.intValue();

Boxing에 사용되는 Wrapper 클래스
int x = 3; => 값 형식
Integer x = 3; => Integer x = new Integer(3); => Boxing 참조형
내부적으로 연산이 이루어진다.

Object x = 3; => Object x = new Integer(3);

모든 데이터를 단일하게 일괄 관리하기 위한 방법이 필요하다.
int x = 3;
float y = 3.5f;
char c = 'a';
double d = 4.7;
Record r new Record(1,1,1);

Object[] data = new Object[5];
data[0] = x;
data[1] = y;
data[2] = c;
data[3] = d;
data[4] = r;
