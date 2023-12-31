package HW_2;

/**
 * Абстрактный класс с шаблонным методом
 */
abstract class AbstractClass {
    /**
     * Шаблонный метод
     */
    public final void templateMethod() {
        // Вызов метода до
        doBefore();

        // Вызов абстрактного метода
        doSomething();

        // Вызов метода после
        doAfter();
    }

    /**
     * Метод до
     * Может быть реализован в абстрактном классе или иметь пустую реализацию
     */
    protected void doBefore() {}

    /**
     * Абстрактный метод
     * Должен быть реализован в каждом наследнике
     */
    protected abstract void doSomething();

    /**
     * Метод после
     * Может быть реализован в абстрактном классе или иметь пустую реализацию
     */
    protected void doAfter() {}
}

/**
 * Пример наследника абстрактного класса с переопределением методов
 */
class ConcreteClass extends AbstractClass {
    @Override
    protected void doBefore() {
        // Действия, выполняемые перед выполнением абстрактного метода
    }

    @Override
    protected void doSomething() {
        // Реализация абстрактного метода
    }

    @Override
    protected void doAfter() {
        // Действия, выполняемые после выполнения абстрактного метода
    }
}

public class Task_9 {
    public static void main(String[] args) {
        AbstractClass object = new ConcreteClass();
        object.templateMethod();
    }
}


/* Паттерн Шаблонный метод определяет основу алгоритма в суперклассе, оставляя наследникам возможность 
переопределить некоторые части алгоритма без изменения его структуры. В данном примере абстрактный класс 
AbstractClass содержит шаблонный метод templateMethod(), который определяет последовательность выполнения операций.
 Метод doBefore() может быть переопределен в наследниках для выполнения дополнительных действий перед вызовом 
 абстрактного метода doSomething(). Абстрактный метод doSomething() должен быть реализован в каждом наследнике. 
 Метод doAfter() может быть переопределен в наследниках для выполнения дополнительных действий после выполнения 
 абстрактного метода. В классе ConcreteClass происходит переопределение всех трех методов, которые выполняют 
 определенные действия в соответствующих местах шаблонного метода. В методе main создается объект класса 
 ConcreteClass и вызывается его шаблонный метод templateMethod() для выполнения алгоритма. */