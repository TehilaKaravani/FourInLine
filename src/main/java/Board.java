import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Board extends JFrame {
    private final JButton[][] squares = new JButton[7][];
    private boolean clicked1;
    private boolean clicked2;
    private boolean clicked3;
    private boolean clicked4;
    private boolean clicked5;
    private boolean clicked6;
    private boolean clicked7;


    public Board() {
        for (int i = 0; i < Constant.BOARD_WIDTH; ++i) {
            this.squares[i] = new JButton[Constant.BOARD_WIDTH];

            for (int j = 0; j < Constant.BOARD_WIDTH; ++j) {
                JButton square = new JButton();
                if (i == 0) {
                    square.setText(String.valueOf(j + 1));
                    square.setBackground(Color.WHITE);
                    square.setFont(new Font(Constant.FONT_NAME, 1, Constant.FONT_SIZE));
                    square.addActionListener((e) -> {
                    });
                } else {
                    square.setEnabled(false);
                }
                this.squares[i][j] = square;
                this.add(square);
            }

            this.squares[0][0].addActionListener((e -> {
                this.clicked1 = true;
            }));

            this.squares[0][1].addActionListener((e -> {
                this.clicked2 = true;
            }));

            this.squares[0][3].addActionListener((e -> {
                this.clicked4 = true;
            }));

            this.squares[0][4].addActionListener((e -> {
                this.clicked5 = true;
            }));

            this.squares[0][5].addActionListener((e -> {
                this.clicked6 = true;
            }));

            this.squares[0][6].addActionListener((e -> {
                this.clicked7 = true;
            }));

        }

        this.setLocationRelativeTo((Component) null);
        GridLayout gridLayout = new GridLayout(Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT);
        this.setLayout(gridLayout);
        this.setSize(Constant.BOARD_HEIGHT * Constant.SQUARE_SIZE, Constant.BOARD_HEIGHT * Constant.SQUARE_SIZE);
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

    public boolean isPlayerIsInside(int player) {
        boolean isInside = false;
        int column = -1;
        if (clicked1) {
            column = 1;
            clicked1 = false;
        } else if (clicked2) {
            column = 2;
            clicked2 = false;
        } else if (clicked3) {
            column = 3;
            clicked3 = false;
        } else if (clicked4) {
            column = 4;
            clicked4 = false;
        } else if (clicked5) {
            column = 5;
            clicked5 = false;
        } else if (clicked6) {
            column = 6;
            clicked6 = false;
        } else if (clicked7) {
            column = 7;
            clicked7 = false;
        }
        if (column != -1) {
            int emptyPlace = 0;
            for (int i = 1; i < 6; i++) {
                if (this.getPlayerInSquare(column, i) == 0) {
                    emptyPlace = i;
                    break;
                }
            }
            placeSquare(column, emptyPlace, player);
            isInside = true;
        }
        return isInside;
    }


}