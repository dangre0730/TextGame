import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {
    // This class handles the User Interface generation of the game. Call it in the main class.
    // Declare UI Objects
    JFrame ui;
    Font titleFont, menuFont;
    Container mainCon;
    JPanel titleNamePanel, startButtonPanel, mainTextPanel, choiceButtonPanel;
    JLabel titleNameLabel;
    JButton startButton;
    JTextArea mainTextArea;
    TitleHandler tsHandler;

    public void createGameScreen(){
        // Clear title screen so there is area for text screens.
        titleNamePanel.setVisible(false);
        startButtonPanel.setVisible(false);

        // Create Text Panel
        mainTextPanel = new JPanel();
        mainTextPanel.setBackground(Color.green);
        mainTextPanel.setBounds(120, 100, 1060, 200);
        mainCon.add(mainTextPanel);

        choiceButtonPanel = new JPanel();
        choiceButtonPanel.setBounds(266, 400, 250, 100);
        choiceButtonPanel.setBackground(Color.blue);
        mainCon.add(choiceButtonPanel);

        // Create Text Area
        mainTextArea = new JTextArea(Dialog.greeting());
        mainTextArea.setBounds(120, 100, 1060, 200);
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(menuFont);
        mainTextArea.setLineWrap(true);
        mainTextPanel.add(mainTextArea);

    }

    public void createUI(){

        // UI learning area
        // Create UI
        ui = new JFrame();
        ui.setSize(1280, 720);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.getContentPane().setBackground(Color.BLACK);
        ui.setLayout(null); // Disable the default layout, so we can set our own format.
//        ui.setVisible(true);

        // Create Font for title label
        titleFont = new Font("Courier", Font.PLAIN, 40);
        // Create Font for menu buttons
        menuFont = new Font("Times New Roman", Font.PLAIN, 28);

        // Create containers for ui
        mainCon = ui.getContentPane();

        // Create title name Panel
        titleNamePanel = new JPanel();
        titleNamePanel.setBounds(400, 100, 480, 70);
        titleNamePanel.setBackground(Color.black);

        // Create title label
        titleNameLabel = new JLabel("Abernathy's Mansion");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(titleFont);

        // Create menu start option label
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(540, 400, 200, 55);
        startButtonPanel.setBackground(Color.black);
//            startButtonPanel.setOpaque(false);

        // Create menu start option button and enable clicking of it
        tsHandler = new TitleHandler();
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(menuFont);
        startButton.addActionListener(tsHandler);

        // Load UI
        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);
        mainCon.add(titleNamePanel);
        mainCon.add(startButtonPanel);

        ui.setVisible(true);

        // UI Learning area close
    }
    public class TitleHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            createGameScreen();
        }
    }
}
