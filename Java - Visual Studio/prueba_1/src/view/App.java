package view;

public class App {
    public static void main(String[] args) {
        controller.jugadorDao.listarJugadores().forEach(System.out::println);
    }
}
