# 자바 콜렉션과 제네릭

## 콜렉션 - 데이터를 수집하고 관리해주는 객체, 가변길이 배열이다.

<br>
왜 사용하는가? <br>
데이터관리를 직접 할 필요가 없다. <br>
공간의 크기를 가변적으로 사용할 수 있다.

모든 데이터를 목록으로 관리할 수 있는 자료형식이 있나요?<br>
데이터 타입이 다 다르기 때문에 문제가 된다.<br>
모든 클래스는 Object이다. 그럼 primitive 형식과의 관계는?<br>
자바에서 모든 클래스는 Object를 상속하게 되어있다.<br>
Object 형식으로 배열을 만들면 모두 참조할 수 있다.<br>

모든 객체를 묶을 수 있는 범용 자료형식

하지만 값을 참조 할 수가 없다. (원시타입)<br><br>

그래서 등장한
Wrapper 클래스와 Auto Boxing / UnBoxing

```
Boxing
Object obj = new Integer(3);

=> UnBoxing
int x = obj.intValue();
```

Boxing에 사용되는 Wrapper 클래스

```
int x = 3; => 값 형식
Integer x = 3; => Integer x = new Integer(3); => Boxing 참조형
내부적으로 연산이 이루어진다.

Object x = 3; => Object x = new Integer(3);
```

모든 데이터를 단일하게 일괄 관리하기 위한 방법이 필요하다.

```
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
```

<br>범용자료형 형식을 사용할때의 문제점

범용자료형이기 때문에 변수가 확실히 어떤값을 가지고 있는지
알 수 없다.

형변환을 일일히 해줘야 한다.

=> 그래서 등장한게 제네릭이다.

<br>
<br>

# Generic

Object를 계속 사용할 경우에는 어떤값으로 리턴될지 알 수 가 없다.

범용 콜렉션의 장점과 특화된 클래스의 장점을 모두 겸비한 Template

Generic => 무엇이든 될 수 있다. 단, 기본은 Object이다.

<br>
* 가변크기 콜렉션으로 변경하기

```
만약에 공간이 부족하면?
1. amount개 확장한 새로운 배열 temp를 생성한다.
2. list에 있는 데이터를 temp 배열로 옮긴다.
3. temp가 참조하는 객체를 list가 참조하게 한다.
4. 현재 capacity의 값을 amount 만큼 증가시킨다.

```

<br>
* 자바 콜렉션 3대 계보

Collection - Set, List, Queue

Map - HashedMap

List - ArrayList, LinkedList, Stac (List 인터페이스를 구현함)

Map도 콜렉션이지만 데이터구조가 다르다.

<br>

배열에 값을 저장하는 일반적인 방법 - 선형 데이터 구조

다른 방법 비선형 데이터구조 : Tree 모양

선형 데이터 구조 : 링크(참조, 포인터 등)로 연결된 데이터

모든 데이터구조 구현체를 공부하기 보다 우선적으로 HashSet, HashMap, ArrayList를 알아야한다.

<br>
처음에 데이터 구조는 보통 위에 우선적으로 알아야하는 것으로 하고 그 후에 효율이 더 높은 데이터구조로 변경하게 된다.

<br>
* 용도에 따른 데이터구조 선택

가변길이 리스트가 필요할때는 ArrayList

데이터 수집시 중복을 제거한 데이터 수집이 필요하면 HashSet

클래스를 정의하지 않고 속성과 값으로 이루어진 객체가 필요할때 Map

<br>
<br>
<br>
<br>

[🎇뉴렉처 강의 참조](https://www.youtube.com/watch?v=_YmqodzBSC8&list=PLq8wAnVUcTFWKOIbvo18pJZ9zsxtXz_-k&index=6)
