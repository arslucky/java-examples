package org.ars.example.clazz;

public class AnonymousClasses1 {

    interface HelloWorld {
        public void greet();

        public void greetSomeone( String someone);
    }

    public void sayHello() {

        class EnglishGreeting implements HelloWorld {
            String name = "world";

            @Override
            public void greet() {
                greetSomeone( "world");
            }

            @Override
            public void greetSomeone( String someone) {
                name = someone;
                System.out.println( "Hello " + name);
            }
        }

        HelloWorld englishGreeting = new EnglishGreeting();

        HelloWorld frenchGreeting = new HelloWorld() {
            String name = "tout le monde";

            @Override
            public void greet() {
                greetSomeone( "tout le monde");
            }

            @Override
            public void greetSomeone( String someone) {
                name = someone;
                System.out.println( "Salut " + name);
            }
        };

        HelloWorld spanishGreeting = new HelloWorld() {
            String name = "mundo";
            int i = 0;
            {
                i = i + 1;
            }

            @Override
            public void greet() {
                greetSomeone( "mundo");
            }

            @Override
            public void greetSomeone( String someone) {
                name = someone;
                System.out.println( "Hola, " + name);
            }
        };
        englishGreeting.greet();
        frenchGreeting.greetSomeone( "Fred");
        spanishGreeting.greet();
    }

    public static void main( String... args) {
        AnonymousClasses1 myApp = new AnonymousClasses1();
        myApp.sayHello();
    }
}
