import di.Container;
import di.ContainerFactory;

public class Main {

    public static void main(String[] args) {

        ContainerFactory containerFactory = new ContainerFactory();
        Container container = containerFactory.createContainer();

        App app = new App();
        app.run(container);

    }
}
