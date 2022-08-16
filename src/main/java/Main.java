public class Main {

    private boolean isIn1;
    private boolean isIn2;
    private boolean finish;

    public Main () {
        finish = true;
        play();
    }

    public static void main(String[] args) {
        Main main = new Main();

    }

    public void play () {
        Board board = new Board();



//        board.placeSquare(1,4,2);
//        board.checkVictoryInDiagonalDown();


//        board.placeSquare(2,5,2);
//        board.placeSquare(3,4,2);
//        board.placeSquare(4,3,2);
//        board.placeSquare(5,2,2);
//        System.out.println(board.checkVictoryInDiagonalDown());









//
//        new Thread(() -> {
//            while (finish) {
//                try {
//                    finish = false;
//                    while (!isIn1) {
//                        System.out.println("false");
//                        if (board.isPlayerIsInside(1)) {
//                            isIn1 = true;
//                        }
//                    }
//                    isIn1 = false;
//                    while (!isIn2) {
//                        if (board.isPlayerIsInside(2)) {
//                            isIn2 = true;
//                        }
//                    }
//                    isIn2 = false;
//                    finish = true;
//
//
//                    Thread.sleep(500);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }


    //Method 1: Board board = new Board();
    //This method shows the initial empty board


    //Method 2: placeSquare (int x, int y, int player)
    //This method gets 3 arguments
    //x is the x position of the square to be placed
    //y is the y position of the square to be placed
    //player can be either of value 1 or 2. For the value 1, a red square is being placed, For the value 2, a yellow square


    //Method 2: int getPlayerInSquare (int x, int y)
    //This method gets 2 arguments
    //x is the x position of the asked square
    //y is the y position of the asked square
    //The method returns an int value: if the value is 0 - the square is empty, if the value is 1 - the square is occupied by
    //player 1, if the value is 2 - the square is occupied by player 2


}