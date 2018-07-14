# Java 8 

## 함수형 프로그래밍
람다 표현식으로 구현이 가능한 인터페이스는 오직 추상 메서드가 1개뿐인 인터페이스만 가능하며 그렇기때문에 추상 메서드가 1개인 인터페이스를 부르는 명칭이 추가됐다. 그것이 함수형 인터페이스다.  

#### 그게뭐라구??

 인터페이스 선언 시
`Alog` 라는 이름의 인터페이스를 `Main` 에서 구현한다고 하자. 

```Java
//example1 Class
Algo al = () -> "hi";

//Algo interface
interface Algo {
	String myfunc();
}
```

아주 직관적인 코드다. 저런식으로 표현하고, 저 동작을 이해하고 다음으로 넘어가자.  
바로 다음에 이어지는 `행위 파라미터화 (Behavior Parameterize)` 에 자세한 내용이 있다.  

#### 행위 파라미터화 (Behavior Parameterize)
parameter로 `행위를 전달` 하는 것.  
```Java
class Fruit{
    private String name;
    private String color;

    Fruit(String name, String color){
        this.name = name;
        this.color = color;
    }

    String getName(){
        return this.name;
    }

    String getColor(){
        return this.color;
    }
}

List<Fruit> extractApple(List<Fruit> fruits){
    List<Fruit> resultList = new ArrayList<>();
    for(Fruit fruit : fruits){
        if("apple".equals(fruit.getName())){
            resultList.add(fruit);
        }
    }

    return resultList;
}

List<Fruit> extractRed(List<Fruit> fruits){
    List<Fruit> resultList = new ArrayList<>();
    for(Fruit fruit : fruits){
        if("red".equals(fruit.getColor())){
            resultList.add(fruit);
        }
    }

    return resultList;
}
``` 
`Fruit` 클래스가 있고, 해당 객체들의 리스트를 받아 특정 조건으로 추출해내는 method 2개이다.  
이를 행위 파라미터를 이용한다면 1개의 method로 합치는 것이 가능하다.  

```Java
static List<Fruit> extractFruitList(List<Fruit> fruits, Predicate<Fruit> predicate){
    List<Fruit> resultList = new ArrayList<>();
    for(Fruit fruit : fruits){
        if(predicate.test(fruit)){
            resultList.add(fruit);
        }
    }

    return resultList;
}

```

> **비교하는 행위**를 파라미터로 받아, 비교하는 부분을 조금 깔끔하게 처리하자.  
호출하는 부분은 이렇다.  

```Java
List<Fruit> fruits = Arrays.asList(new Fruit("apple", "red"), new Fruit("melon", "green"), new Fruit("banana", "yellow"));
List<Fruit> appleList = extractFruitList(fruits, new Predicate<Fruit>() {
    @Override
    public boolean test(Fruit fruit) {
        return "apple".equals(fruit.getName());
    }
});

List<Fruit> redList = extractFruitList(fruits, new Predicate<Fruit>() {
    @Override
    public boolean test(Fruit fruit) {
        return "red".equals(fruit.getColor());
    }
});
``` 
이걸 한줄씩으로도 표현이 가능하다. 

```Java
List<Fruit> appleList = extractFruitList(fruits, fruit -> "apple".equals(fruit.getName()));
List<Fruit> redList = extractFruitList(fruits, fruit -> "red".equals(fruit.getColor()));
```


### what is Stream?
Java8에 추가된 API, 자료구조들을 **선언적**으로 다루는 역할을 한다.  

#### Stream VS Collection
`Collection은 자료구조들의 구현체` 이고, `Stream은 자료구조들을 다루는 역할`  

**Collection**  

```Java
List<Integer> numbers = new ArrayList<>();

numbers.add(1);
numbers.get(0);
numbers.remove(1);

List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List<Integer> evenList = new ArrayList<>();

for(int number : numbers){
    if(number % 2 == 0){
        evenList.add(number);
    }
}

System.out.println(evenList);

//(numbers 리스트에서 짝수를 추출하는 코드. 리스트는 요소를 추가, 삭제, 순회하는 API만 제공하기때문에 내가 어떻게 짝수를 걸러내야하는지를 짜야된다.)
```

**Stream**  

```Java
List<Integer> evenList = Stream.iterate(1, n -> n+1)
        .limit(6)
        .filter(number -> number % 2 == 0)
        .collect(toList());

System.out.println(evenList);
```


stream은 내부 반복을 통해 동작한다.   
> 반복문에는 2가지가 있다. 외부 반복 / 내부반복.
> 원래도 외부반복을 컴파일 하면 `Binary` 변환 과정에서 어느정도 불필요한 코드가 정리되는데, 
> steam은 내부반복이기 때문에 더 효율적. 


 
### map
`Function<T,R>` : 형변환 해주는 것.  
> T Type 을 받아서 R Type으로 변환.  

`map(use -> user.length())` 라고 하면 user라는 스트링을 user.length() 로바꾼다. 

`List<String> users = new ArrayList<>();` 에서 user의 이름글자수에 따라 새로운 자료구조에 넣어주는 상황을 가정해보자.

```Java
//users.stream().filter(조건식) 부분 생략.
map(use -> user.length())
map(Stirng::length)
```

`users.stream()` 이라고 표현하면, `String 타입순회` 라는 점이 명확히 전달되므로, map의 파라미터의 변수 타입은 명확히 String이다.  
`클래스 :: method` 형태의 표현이다. 본 예제에서는 String 내부에 있는 length method 를 사용하는 상황을 표현한 것이다.   
> map 에는 해당 클래스가 가지고 있는 method 만 호출 가능.  

### filter
filter는 T 타입을 받아서 boolean으로 넘긴다.  
**Predicate<T>**
하나의 인자와 리턴타입을 가진다. Function과 비슷해보이지만 리턴타입을 지정하는 타입파라미터가 안보인다. 반환타입은 boolean 타입으로 고정되어있다.  Function<T, Boolean>형태라고 보면된다.  

```Java
//자료구조 내 타입이 String이라고 가정. 
private boolean filter(final String u) {
	//이런 형태. 
}
```

### Optional(null 방지) 
```Java
User u = null
출력(u.length()); // nullpointerException.
```

이런 실수를 방지하기 위해,  

```Java
Optional<User> opUser = Optional.ofNullable();  
int length = opUser
             .map(User::length)
             .orElse(1) // null이어서 값이 없으면 1 넣어주는 코드
//만약 진짜 에러를 던져야 하는 상황이라면
.orElseThrow(()->new Exception(""))
```

--> return (null) ? (empty()) : value; 

### 기타 정리
만약 리스트에서 string 길이가 4이상인 것만 추출한다고 할때, 절대 원본 데이터를
해치지 말것.  
> 멀티스레드 환경에서 critical issue 발생.
새로운 것을 하고싶으면, 새로운 `List` 생성해서 거기 넣어서 작업하기.  
**원본 데이터에 대한 side Effect** 가 없게 할것. (final을 많이 붙임.) 

+ 이해를 하고 쓰는것과 아닌 것은 정말 엄청난 차이...  
+ 에러를 만날 때 해결 방법에 있어서 차이가 있다.   

### 참고 링크
출처: http://multifrontgarden.tistory.com/128 [우리집앞마당]  
