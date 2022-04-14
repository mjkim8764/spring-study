package chap07;

public class ExeTimeCalculator implements Calculator {

    private Calculator delegate;

    public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }

    /**
     * AOP 프로그래밍 : 관심의 분리 (= 응집도를 높여줌)
     * ExeTimeCalculator 클래스는 핵심 기능인 factorial 을 다른 객체에 위임하고,
     * 부가적인 기능(런타임 구하기)을 제공함. 이런 객체를 프록시 라고 한다.
     *
     * 각 클래스마다 런타임을 구하는 코드가 있었지만,
     * 그렇게 되면 코드 중복의 문제와 그에 따른 유지보수의 문제도 있고,
     * 각 클래스의 관심이 분산되어 버린다 (= 응집도가 낮아짐)
     * 그렇기 때문에 실행시간을 구하는 공통의 부가적인 기능은 프록시(ExeTimeCalculator) 에서 맡아준다.
     *
     * 즉, AOP 의 핵심은 공통 기능 구현과 핵심 기능 구현의 분리라고 할 수 있겠다.
     */

    @Override
    public long factorial(long num) {
        long start = System.nanoTime();
        long result = delegate.factorial(num);
        long end = System.nanoTime();

        System.out.printf("%s.factorial(%d) 실행 시간 = %d\n", delegate.getClass().getSimpleName(), num, (end - start) );

        return result;
    }
}
