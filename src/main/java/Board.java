import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Board extends JFrame {
    private final JButton[][] squares = new JButton[7][];
    private int column;
    private int player;
    private boolean isPlayerEnters;
    private boolean isGameOver;

    public Board() {

        new Thread(() -> {
            while (true) {
                try {
                    isPlayerEnters = false;
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        this.player = 1;
        for (int i = 0; i < Constant.BOARD_WIDTH; ++i) {
            this.squares[i] = new JButton[Constant.BOARD_WIDTH];

            for (int j = 0; j < Constant.BOARD_WIDTH; ++j) {
                JButton square = new JButton();
                if (i == 0) {
                    square.setText(String.valueOf(j + 1));
                    square.setBackground(Color.WHITE);
                    square.setFont(new Font(Constant.FONT_NAME, 1, Constant.FONT_SIZE));
                    square.addActionListener((e) -> {
                        if (!this.isGameOver) {
                            insertPlayer(Integer.parseInt(square.getText()));
                            int winner = checkVictory();
                            if (winner != 0) {
                                System.out.println("The Winner is: " + winner);
                                this.isGameOver = true;
                                Label label = new Label("game over");
                                label.setBounds(Constant.LABEL_X - (Constant.LABEL_WIDTH / 2),Constant.LABEL_Y,Constant.LABEL_WIDTH,Constant.LABEL_HEIGHT);
                                label.setFont(new Font(Constant.FONT_NAME, 1, Constant.FONT_SIZE2));
                                this.add(label);
                            }
                        }
                    });
                } else {
                    square.setEnabled(false);
                }
                this.squares[i][j] = square;
                this.add(square);
            }
        }

        this.setLocationRelativeTo((Component) null);
        GridLayout gridLayout = new GridLayout(Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT);
        this.setLayout(gridLayout);
        this.setBounds(600, 0, Constant.BOARD_HEIGHT * Constant.SQUARE_SIZE, Constant.BOARD_HEIGHT * Constant.SQUARE_SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void placeSquare(int x, int y, int player) {
        this.squares[Constant.BOARD_WIDTH - y][x - 1].setBackground(player == 1 ? Color.RED : Color.YELLOW);
    }

    public int getPlayerInSquare(int x, int y) {
        byte player = 0;
        try {
            Color backgroundColor = this.squares[Constant.BOARD_WIDTH - y][x - 1].getBackground();
            if (backgroundColor.equals(Color.RED)) {
                player = 1;
            } else if (backgroundColor.equals(Color.YELLOW)) {
                player = 2;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return player;
    }

    public boolean isPlayerIsInside() {
        boolean isInside = false;
        int emptyPlace;
        for (int i = 1; i < 7; i++) {
            if (this.getPlayerInSquare(column, i) == 0) {
                emptyPlace = i;
                placeSquare(column, emptyPlace, player);
                isInside = true;
                break;
            }
        }
        return isInside;
    }

    public void insertPlayer(int column) {
        if (!isPlayerEnters) {
            this.column = column;
            if (isPlayerIsInside()) {
                if (this.player == 1) {
                    this.player = 2;
                } else {
                    this.player = 1;
                }
                isPlayerEnters = true;
            }
        }
    }

    public int checkVictory() {
        int winner;
        int columnWinner =  checkVictoryInColumn();
        int lineWinner =  checkVictoryInLine();
        int diagonalDownWinner = checkVictoryInDiagonalDown();
        int diagonalUpWinner = checkVictoryInDiagonalUp();
        if (columnWinner != 0) {
            winner = columnWinner;
        }else if (lineWinner != 0) {
            winner = lineWinner;
        }else if (diagonalDownWinner != 0) {
            winner = diagonalDownWinner;
        }else {
            winner = diagonalUpWinner;
        }
        System.out.println(winner + " is the final winner");
        return winner;
    }

    public int checkVictoryInLine () {
        int winner = 0;
        int currentPlayer = -1;
        int counter = 0;
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                currentPlayer = this.getPlayerInSquare(j, i);
                if (currentPlayer == this.getPlayerInSquare(j + 1, i) && currentPlayer != 0) {
                    counter++;
                } else {
                    counter = 0;
                }

                if (counter == 3) {
                    return currentPlayer;
                }
            }
        }
        return winner;
    }

    public int checkVictoryInColumn () {
        int winner = 0;
        int currentPlayer = -1;
        int counter = 0;
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 6; j++) {
                currentPlayer = this.getPlayerInSquare(i, j);
                if (currentPlayer == this.getPlayerInSquare(i, j + 1) && currentPlayer != 0) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == 3) {
                    return currentPlayer;
                }
            }
        }
        return winner;
    }

    public int checkVictoryInDiagonalDown() {
        int winner = 0;
        for (int x = 1; x < 5; x++) {
            for (int y = 4; y < 7; y++) {
                int currentPlayer = getPlayerInSquare(x,y);
                if (currentPlayer != 0) {
                    if (checkOneDown(x,y)) {
                        winner = currentPlayer;
                        break;
                    }
                }else {
                    break;
                }
            }
            if (winner != 0) {
                break;
            }
        }
        return winner;
    }

    public boolean checkOneDown (int x, int y) {
        boolean isWin = true;
        int counter = 0;
        int currentPlayer = this.getPlayerInSquare(x, y);
        while (isWin) {
            if (x == 1 || y == 6) {
                isWin = false;
                break;
            }
            if (currentPlayer == this.getPlayerInSquare(x - 1, y + 1) && currentPlayer != 0) {
                counter++;
                x--;
                y++;
            }else {
                isWin = false;
            }
        }

        if (counter == 3) {
            isWin = true;
        }
        return isWin;
    }

    public int checkVictoryInDiagonalUp() {
        int winner = 0;
        for (int x = 1; x < 8; x++) {
            for (int y = 1; y < 7; y++) {
                int currentPlayer = getPlayerInSquare(x,y);
                if (currentPlayer != 0 && checkOneUp(x,y)) {
                    winner = currentPlayer;
                    break;
                }
            }
        }
        return winner;
    }

    public boolean checkOneUp(int x, int y) {
        boolean isWin = true;
        int counter = 0;
        int currentPlayer = this.getPlayerInSquare(x, y);
        while (isWin) {
            if (x == 7 || y == 6) {
                isWin = false;
                break;
            }
            if (currentPlayer == this.getPlayerInSquare(x + 1, y + 1)) {
                counter++;
                x++;
                y++;
            }else {
                isWin = false;
            }
        }

        if (counter == 3) {
            isWin = true;
        }else {
            isWin = false;
        }
        return isWin;
    }



}