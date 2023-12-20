import java.util.Scanner;

public class Main {

    public static char[][] board;

    public static void main(String[] args) {

        char player1 = 'X';
        char player2 = 'O';

        boolean playAgain = true;

        System.out.println("Добро пожаловать в игру крестики-нолики!");
        System.out.println("Первый игрок ходит крестиками, второй игрок ходит ноликами.");
        System.out.println("Для ввода координат хода используйте формат \"строка столбец\" (например, \"1 2\").");

        while (playAgain) {

            initBoard();

            boolean turn = true;

            boolean win = false;

            printBoard();

            while (!win && !isBoardFull()) {

                char currentPlayer = turn ? player1 : player2;

                makeTurn(currentPlayer);

                printBoard();

                win = checkWinner(currentPlayer);

                if (win) {
                    System.out.println("Поздравляем! Игрок " + currentPlayer + " выиграл!");
                    break;
                }

                turn = !turn;
            }

            if (!win) {
                System.out.println("Ничья! Никто не выиграл!");
            }
            playAgain = restart();
        }
    }


    public static void initBoard() {

        board = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }


    public static void printBoard() {

        System.out.println("    1   2   3 ");

        System.out.println("  +---+---+---+");

        for (int i = 0; i < 3; i++) {
            System.out.print(i + 1 + " |");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " |"); //
            }
            System.out.println();
            System.out.println("  +---+---+---+");
        }
    }


    public static boolean isBoardFull() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        //
        return true;
    }


    public static void makeTurn(char playerSymbol) {

        Scanner scanner = new Scanner(System.in);

        int row;
        int col;

        System.out.println("Ходит игрок " + playerSymbol + ".");

        do {

            System.out.print("Введите координаты хода в формате \"строка столбец\": ");

            if (scanner.hasNextInt()) {
                row = scanner.nextInt() - 1;
                if (scanner.hasNextInt()) {
                    col = scanner.nextInt() - 1;
                } else {
                    scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Неверный формат ввода. Пожалуйста, введите два целых числа от 1 до 3.");
                    continue;
                }
            } else {
                scanner.nextLine();
                System.out.println("Неверный формат ввода. Пожалуйста, введите два целых числа от 1 до 3.");
                continue;
            }

            if (row < 0 || row > 2 || col < 0 || col > 2) {

                System.out.println("Неверные координаты. Пожалуйста, введите два целых числа от 1 до 3.");
                continue;
            }

            if (board[row][col] != ' ') {

                System.out.println("Эта клетка уже занята. Пожалуйста, выберите другую клетку.");
                continue;
            }

            break;
        } while (true);

        board[row][col] = playerSymbol;
    }


    public static boolean checkWinner(char symbol) {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0][j] == symbol && board[1][j] == symbol && board[2][j] == symbol) {
                return true;
            }
        }

        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }

        return false;
    }


    public static boolean restart() {

        System.out.print("Хотите сыграть еще раз? (да/y или нет/n): ");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine().toLowerCase();

        if (input.equals("да") || input.equals("y")) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            return true;
        } else if (input.equals("нет") || input.equals("n")) {
            // Если ответ отрицательный, то возвращаем false и выводим прощальное сообщение
            System.out.println("Спасибо за игру! До свидания!");
            System.out.println("Нажмите \"Enter\" для завершения работы программы ...");
            scanner.nextLine();
            return false;
        } else {
            System.out.println("Неверный формат ввода. Игра закончена.");
            System.out.println("Нажмите \"Enter\" для завершения работы программы ...");
            scanner.nextLine();
            return false;
        }
    }
}
