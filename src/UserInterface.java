import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {
    // This class handles the User Interface generation of the game. Call it in the main class.
    // Declare UI Objects
    JFrame ui;
    Container mainCon;
    JPanel
            titleNamePanel,
            startButtonPanel,
            playerPanel,
            nameClassLevelPanel,
            healthManaStaminaPanel,
            acInitiativeHitDiePanel,
            strDexConPanel,
            intWisChaPanel,
            weaponPanel,
            offhandPanel,
            armorPanel,
            statsPanel,
            mainTextPanel,
            playerInputPanel,
            choiceButtonPanel;
    JLabel
            titleNameLabel,
            mainTextLabel,
            choiceLabel;
    JButton startButton;
    JTextArea mainTextArea;
    JTextField playerInputField;
    TitleHandler tsHandler;
    PlayerInputHandler pHandler;
    Font
            titleFont,
            menuFont;
    Color
            uiBackgroundColor,
            statsBackgroundColor;
    GridLayout statsLayout;
    BoxLayout nameClassLayout; //, choiceLayout;

    Boolean gameStarted = false;
    Integer gameWidth = 1280;
    Integer gameHeight = 720;
    String playerInput = "";

    enum CurrentScreen {
        TITLE,
        EXPLORATION,
        COMBAT,
        PAUSE,
        INVENTORY
    }

    CurrentScreen currentScreen;

    public void createUI(){

        currentScreen = CurrentScreen.TITLE;

        uiBackgroundColor = new Color(19, 99, 83);
        statsBackgroundColor = new Color(168, 161, 103);

        // UI learning area
        // Create UI. Currently static size of 1280 x 720. Will figure out how to make this dynamic later along with all relevant elements.
        ui = new JFrame();
//        ui.setSize(gameWidth, gameHeight);
        ui.setPreferredSize(new Dimension(gameWidth, gameHeight));
        ui.pack();
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.getContentPane().setBackground(uiBackgroundColor);
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
        titleNamePanel.setOpaque(false);

        // Create title label
        titleNameLabel = new JLabel("Abernathy's Mansion");
        titleNameLabel.setForeground(Color.WHITE);
        titleNameLabel.setFont(titleFont);

        // Create menu start option label
        startButtonPanel = new JPanel();
        startButtonPanel.setBounds(540, 400, 200, 55);
        startButtonPanel.setBackground(Color.black);
        startButtonPanel.setOpaque(false);

        // Create menu start option button and enable clicking of it
        tsHandler = new TitleHandler();
        startButton = new JButton("START");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.WHITE);
        startButton.setBorderPainted(false);
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

    public void createGameScreen(){
        // Clear title screen so there is area for text screens.
//        titleNamePanel.setVisible(false);
        mainCon.remove(titleNamePanel);
//        startButtonPanel.setVisible(false);
        mainCon.remove(startButtonPanel);
        mainCon.revalidate();
        mainCon.repaint();

        // Set the grid layout for the stats panel to use.
//        statsLayout = new GridLayout(3, 5, 8, 8);
//        nameClassLayout = new BoxLayout();

        playerPanel = new JPanel();
        playerPanel.setBackground(statsBackgroundColor);
//        playerPanel.setBounds(16, 16, gameWidth - 16, 160);
        playerPanel.setLocation(16, 16);
        playerPanel.setSize(mainCon.getWidth() - 32, (mainCon.getHeight() / 4) - 16);
        playerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        playerPanel.setLayout(new GridLayout(1, 0, 8, 8));
        mainCon.add(playerPanel);

        mainCon.revalidate();
        mainCon.repaint();

        int playerPanelElementsWidth = (playerPanel.getWidth() / 8);
        int playerPanelElementsHeight = (playerPanel.getHeight() / 3);

        // Testing out min, pref, and max sizing for these elements within the playerPanel to better learn how BoxLayout works.
        //  Setting the values used for this first BoxLayout's items as variables so that I only need to update in one spot.
        int minLabelSizeWidth = 20;
        int minLabelSizeHeight = 16;
//        int prefLabelSizeWidth = 122;
//        int prefLabelSizeHeight = playerPanelElementsHeight - 2;
        int prefLabelSizeWidth = 60;
        int prefLabelSizeHeight = playerPanelElementsHeight + 60;
        int maxLabelSizeWidth = 245;
        int maxLabelSizeHeight = playerPanelElementsHeight - 8;

        nameClassLevelPanel = new JPanel();
        nameClassLevelPanel.setBackground(statsBackgroundColor);
        nameClassLevelPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        nameClassLevelPanel.setLayout(new BoxLayout(nameClassLevelPanel, BoxLayout.PAGE_AXIS));

        JLabel playerNameLabel = new JLabel("NAME:\nCraig Carter");
            playerNameLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerNameLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerNameLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerNameLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel playerClassLabel = new JLabel("CLASS: --");
            playerClassLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerClassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerClassLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerClassLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerClassLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));
        JLabel playerLevelLabel = new JLabel("LEVEL: 0");
            playerLevelLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerLevelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerLevelLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerLevelLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerLevelLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));

        nameClassLevelPanel.add(playerNameLabel);
        nameClassLevelPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        nameClassLevelPanel.add(playerClassLabel);
        nameClassLevelPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        nameClassLevelPanel.add(playerLevelLabel);

        healthManaStaminaPanel = new JPanel();
        healthManaStaminaPanel.setBackground(statsBackgroundColor);
        healthManaStaminaPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        healthManaStaminaPanel.setLayout(new BoxLayout(healthManaStaminaPanel, BoxLayout.PAGE_AXIS));

        JLabel playerHealthLabel = new JLabel("HEALTH: 90 / 90");
            playerHealthLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerHealthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerHealthLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerHealthLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerHealthLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel playerManaLabel = new JLabel("MANA: --");
            playerManaLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerManaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerManaLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerManaLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerManaLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));
        JLabel playerStaminaLabel = new JLabel("STAMINA: 70 / 70");
            playerStaminaLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerStaminaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerStaminaLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerStaminaLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerStaminaLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));

        healthManaStaminaPanel.add(playerHealthLabel);
        healthManaStaminaPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        healthManaStaminaPanel.add(playerManaLabel);
        healthManaStaminaPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        healthManaStaminaPanel.add(playerStaminaLabel);

        acInitiativeHitDiePanel = new JPanel();
        acInitiativeHitDiePanel.setBackground(statsBackgroundColor);
        acInitiativeHitDiePanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        acInitiativeHitDiePanel.setLayout(new BoxLayout(acInitiativeHitDiePanel, BoxLayout.PAGE_AXIS));

        JLabel playerACLabel = new JLabel("ARMOR CLASS: 12");
            playerACLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerACLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerACLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerACLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerACLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel playerInitiativeLabel = new JLabel("INITIATIVE: 0");
            playerInitiativeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerInitiativeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerInitiativeLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerInitiativeLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerInitiativeLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));
        JLabel playerHitDieLabel = new JLabel("HIT DIE [d10]: 5 / 5");
            playerHitDieLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerHitDieLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerHitDieLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerHitDieLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerHitDieLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));

        acInitiativeHitDiePanel.add(playerACLabel);
        acInitiativeHitDiePanel.add(Box.createRigidArea(new Dimension(0, 8)));
        acInitiativeHitDiePanel.add(playerInitiativeLabel);
        acInitiativeHitDiePanel.add(Box.createRigidArea(new Dimension(0, 8)));
        acInitiativeHitDiePanel.add(playerHitDieLabel);

        strDexConPanel = new JPanel();
        strDexConPanel.setBackground(statsBackgroundColor);
        strDexConPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        strDexConPanel.setLayout(new BoxLayout(strDexConPanel, BoxLayout.PAGE_AXIS));

        JLabel playerStrengthLabel = new JLabel("STRENGTH: +8 (26)");
            playerStrengthLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerStrengthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerStrengthLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerStrengthLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerStrengthLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel playerDexterityLabel = new JLabel("DEXTERITY: +2 (14)");
            playerDexterityLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerDexterityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerDexterityLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerDexterityLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerDexterityLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));
        JLabel playerConstitutionLabel = new JLabel("CONSTITUTION: +5 (21)");
            playerConstitutionLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerConstitutionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerConstitutionLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerConstitutionLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerConstitutionLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));

        strDexConPanel.add(playerStrengthLabel);
        strDexConPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        strDexConPanel.add(playerDexterityLabel);
        strDexConPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        strDexConPanel.add(playerConstitutionLabel);

        intWisChaPanel = new JPanel();
        intWisChaPanel.setBackground(statsBackgroundColor);
        intWisChaPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        intWisChaPanel.setLayout(new BoxLayout(intWisChaPanel, BoxLayout.PAGE_AXIS));

        JLabel playerIntelligenceLabel = new JLabel("INTELLIGENCE: -- (10)");
            playerIntelligenceLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerIntelligenceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerIntelligenceLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerIntelligenceLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerIntelligenceLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel playerWisdomLabel = new JLabel("WISDOM: +1 (12)");
            playerWisdomLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerWisdomLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerWisdomLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerWisdomLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerWisdomLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel playerCharismaLabel = new JLabel("CHARISMA: -2 (7)");
            playerCharismaLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerCharismaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerCharismaLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerCharismaLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerCharismaLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));

        intWisChaPanel.add(playerIntelligenceLabel);
        intWisChaPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        intWisChaPanel.add(playerWisdomLabel);
        intWisChaPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        intWisChaPanel.add(playerCharismaLabel);

        weaponPanel = new JPanel();
        weaponPanel.setBackground(statsBackgroundColor);
        weaponPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        weaponPanel.setLayout(new BoxLayout(weaponPanel, BoxLayout.PAGE_AXIS));


//        JLabel playerWeaponNameLabel = new JLabel("WEAPON: Brass Knuckles");
//            playerWeaponNameLabel.setBorder(BorderFactory.createLineBorder(Color.black));
//            playerWeaponNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//            playerWeaponNameLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
//            playerWeaponNameLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
//            playerWeaponNameLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel playerWeaponDamageLabel = new JLabel("DAMAGE: 1");
            playerWeaponDamageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerWeaponDamageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerWeaponDamageLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerWeaponDamageLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerWeaponDamageLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel playerWeaponSpeedLabel = new JLabel("SPEED: 2");
            playerWeaponSpeedLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerWeaponSpeedLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerWeaponSpeedLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerWeaponSpeedLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerWeaponSpeedLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel playerWeaponCritLabel = new JLabel("CRIT %: 15");
            playerWeaponCritLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerWeaponCritLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerWeaponCritLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerWeaponCritLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerWeaponCritLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));

        weaponPanel.add(playerWeaponDamageLabel);
        weaponPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        weaponPanel.add(playerWeaponSpeedLabel);
        weaponPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        weaponPanel.add(playerWeaponCritLabel);

        offhandPanel = new JPanel();
        offhandPanel.setBackground(statsBackgroundColor);
        offhandPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        offhandPanel.setLayout(new BoxLayout(offhandPanel, BoxLayout.PAGE_AXIS));

        JLabel offhandDamageLabel = new JLabel("OFF-HAND DAMAGE: --");
            offhandDamageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            offhandDamageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            offhandDamageLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            offhandDamageLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            offhandDamageLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel offhandSpeedLabel = new JLabel("OFF-HAND SPEED: --");
            offhandSpeedLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            offhandSpeedLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            offhandSpeedLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            offhandSpeedLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            offhandSpeedLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel offhandCritLabel = new JLabel("OFF-HAND CRIT: --");
            offhandCritLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            offhandCritLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            offhandCritLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            offhandCritLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            offhandCritLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));

        offhandPanel.add(offhandDamageLabel);
        offhandPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        offhandPanel.add(offhandSpeedLabel);
        offhandPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        offhandPanel.add(offhandCritLabel);

        armorPanel = new JPanel();
        armorPanel.setBackground(statsBackgroundColor);
        armorPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        armorPanel.setLayout(new BoxLayout(armorPanel, BoxLayout.PAGE_AXIS));

        JLabel armorBonusLabel = new JLabel("PROT BONUS: --");
            armorBonusLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            armorBonusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            armorBonusLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            armorBonusLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            armorBonusLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel armorDamageReductionLabel = new JLabel("DAMAGE RED: --");
            armorDamageReductionLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            armorDamageReductionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            armorDamageReductionLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            armorDamageReductionLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            armorDamageReductionLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        JLabel armorSpeedReductionLabel = new JLabel("SPEED RED: --");
            armorSpeedReductionLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            armorSpeedReductionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            armorSpeedReductionLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            armorSpeedReductionLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            armorSpeedReductionLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));

        armorPanel.add(armorBonusLabel);
        armorPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        armorPanel.add(armorSpeedReductionLabel);
        armorPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        armorPanel.add(armorDamageReductionLabel);



        playerPanel.add(nameClassLevelPanel);
        playerPanel.add(healthManaStaminaPanel);
        playerPanel.add(acInitiativeHitDiePanel);
        playerPanel.add(strDexConPanel);
        playerPanel.add(intWisChaPanel);
        playerPanel.add(weaponPanel);
        playerPanel.add(offhandPanel);
        playerPanel.add(armorPanel);

        mainCon.revalidate();
        mainCon.repaint();

        // Create Text Panel
        mainTextPanel = new JPanel();
        mainTextPanel.setBackground(Color.black);
        mainTextPanel.setBounds(playerPanel.getX(), playerPanel.getY() + playerPanel.getHeight(), playerPanel.getWidth(), ((mainCon.getHeight() / 2) + (mainCon.getHeight() / 8) + (mainCon.getHeight() / 16)) - 16);
        mainCon.add(mainTextPanel);

        // Create Text Panel Label and display text so you know what element it is
        mainTextLabel = new JLabel("GAME CONSOLE");
        mainTextPanel.add(mainTextLabel);

        playerInputPanel = new JPanel();
        playerInputPanel.setBounds(playerPanel.getX(), mainTextPanel.getY() + mainTextPanel.getHeight(), mainTextPanel.getWidth(), (mainCon.getHeight() / 16));
        playerInputPanel.setBackground(Color.darkGray);

        JLabel playerInputLabel = new JLabel(">");        ////////Trying to add a > right before the text field like the HTML game. Not displaying currently.
        playerInputLabel.setLocation(playerInputPanel.getX() + 8, playerInputPanel.getY());
        playerInputLabel.setFont(new Font("Arial", Font.BOLD, playerInputPanel.getWidth() / 50));
        playerInputLabel.setForeground(Color.black);
        playerInputPanel.add(playerInputLabel);

        pHandler = new PlayerInputHandler();
        playerInputField = new JTextField();
            playerInputField.setPreferredSize(new Dimension(playerInputPanel.getWidth() - 32, playerInputPanel.getHeight() - 8));
            playerInputField.setLocation(playerInputLabel.getX() + 24, playerInputLabel.getY());
            playerInputField.setFont(new Font("Arial", Font.PLAIN, playerInputPanel.getWidth() / 50));
            playerInputField.setBackground(Color.darkGray);
            playerInputField.setForeground(Color.black);
            playerInputField.setBorder(null);
            // Two ways to handle this action monitor. Did it this commented out way first, but realized I could 'reuse' the TitleHandler class.
            // Commented out so I have it as a reference.
//            playerInputField.addActionListener(e -> {
//                if (playerInputField.isFocusOwner()){
//                    System.out.println(playerInputField.getText());
//                    playerInputField.setText("");
//                }
//            });
            playerInputField.addActionListener(pHandler);
        playerInputPanel.add(playerInputField);

        mainCon.add(playerInputPanel);

        // Create Choice Panel Label and display text so you know what element it is
//        choiceLabel = new JLabel("Make your choice");
//        choiceButtonPanel.add(choiceLabel);

        // Create Text Area. Set the width of this area to be 16 pixels smaller than the text panel so that it has a buffer around it
        mainTextArea = new JTextArea(); //JTextArea(Dialog.greeting());
        mainTextArea.setBounds(mainTextPanel.getX() + 8, mainTextPanel.getY() + 8, mainTextPanel.getWidth() - 8, mainTextPanel.getHeight() - 8);
//        mainTextArea.setBorder(BorderFactory.createLineBorder(Color.gray));
        mainTextArea.setBackground(Color.black);
        mainTextArea.setForeground(Color.white);
        mainTextArea.setFont(new Font("monospaced", Font.BOLD, 22));
        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        mainTextArea.setEditable(false);
        mainTextArea.append("Main Text Area\n\n\n");
        mainTextPanel.add(mainTextArea);

        mainCon.revalidate();
        mainCon.repaint();

        gameStarted = true;

        playerInputField.requestFocusInWindow();

        Functions.slowPrintText(Dialog.chatTracker[Game.dialog.chatLevel], Game.ui.mainTextArea);

    }

    public void displayText(){
//        mainTextArea.append(text);
        Functions.slowPrintText(Dialog.chatTracker[Game.dialog.chatLevel], Game.ui.mainTextArea);
    }

    public void clearText(){
        mainTextArea.setText("");
    }

    public void getPlayerInput(){
        if (playerInputField.isFocusOwner()) {

            boolean validInput = true;

            System.out.println(Game.gameState);

            Game.playerInput = playerInputField.getText().toUpperCase();

            switch (Game.gameState) {
                case WAITING_ON_GAME_START -> {
                }
                case WAITING_ON_PLAYER_NAME -> {
                    Game.setPlayerName(playerInputField.getText());
                    Game.gameState = Game.GameState.WAITING_ON_CONTINUE;
                    System.out.println("Game.player.name: " + Game.player.name);
                    clearText();
                    Game.dialog.chatLevel = 1;
                    displayText();
                }
                case WAITING_ON_CONTINUE -> {
                    if (Game.playerInput.equals("CONTINUE")) {
                        Game.gameState = Game.GameState.WAITING_ON_PLAYER_CLASS;
                        clearText();
                        Game.dialog.chatLevel++;
                        displayText();
                    } else {
                        Game.ui.playerInputField.setText("");
                    }
                }
                case WAITING_ON_PLAYER_CLASS -> {

                    switch (Game.playerInput) {
                        case "SOLDIER" -> Game.player.pcClass = Player.classChoice.SOLDIER;
                        case "RANGER" -> Game.player.pcClass = Player.classChoice.RANGER;
                        case "BRAWLER" -> Game.player.pcClass = Player.classChoice.BRAWLER;
                        case "MAGE" -> Game.player.pcClass = Player.classChoice.MAGE;
                        default -> validInput = false;
                    }
                    if (validInput) {
                        Game.player.classBuilder();
                        Game.player.statPrinter();
                        Game.dialog.chatLevel++;
                        clearText();
//                        Game.dialog.chatLevel = 2;
                        Game.gameState = Game.GameState.WAITING_ON_PLAYER_WEAPON;
                        displayText();
                    } else {
                        Game.ui.playerInputField.setText("");
                    }
                }
                case WAITING_ON_PLAYER_WEAPON -> {
                    System.out.println(Game.gameState);

                    switch (Game.playerInput) {
                        case "SWORD" -> Game.player.playerWeapon = Player.weaponChoice.SWORD;
                        case "CROSSBOW" -> Game.player.playerWeapon = Player.weaponChoice.CROSSBOW;
                        case "BAT" -> Game.player.playerWeapon = Player.weaponChoice.BAT;
                        case "WAND" -> Game.player.playerWeapon = Player.weaponChoice.WAND;
                        default -> validInput = false;
                    }

                    if (validInput) {
                        Game.player.setWeaponChoice();
                        Game.dialog.chatLevel++;
                        clearText();
                        displayText();
                    }
                }
                case EXPLORING -> {
                }
                case IN_COMBAT -> {
                }
                case IN_INVENTORY -> {
                }
                default -> System.out.println("gameState default switch");
            }

            playerInputField.setText("");
        }
    }

    public class TitleHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            createGameScreen();
//            currentScreen = CurrentScreen.EXPLORATION;
            Game.gameState = Game.GameState.WAITING_ON_PLAYER_NAME;
        }
    }

    public class PlayerInputHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            getPlayerInput();
//            if (playerInputField.isFocusOwner()){
//                System.out.println(playerInputField.getText());
//                playerInput = playerInputField.getText();
//                playerInputField.setText("");
//            }
        }
    }
}
