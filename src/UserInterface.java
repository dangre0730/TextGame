import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface {
    // This class handles the User Interface generation of the game. Call it in the main class.
////////////////////////////
///                     ///
///  Declare UI Objects ///
///                     ///
///////////////////////////

    // This is the actual game window
    JFrame ui;

    // This container is anchored to, and the same size as, the ui object and enables us to draw the game
    Container mainCon;

    // Panels represent each major element of the UI
    JPanel
            titleNamePanel,
            startButtonPanel,
            playerPanel,
            nameClassACPanel,
            healthInitiativeWeaponPanel,
            acInitiativeHitDiePanel,
            strDexConPanel,
            intWisChaPanel,
            weaponStatsPanel,
            offhandPanel,
            armorPanel,
            statsPanel,
            mainTextPanel,
            playerInputPanel,
            choiceButtonPanel;

    // Labels represent the text element that populates information within Panels
    JLabel
            titleNameLabel,
            mainTextLabel,
            playerNameLabel,
            playerClassLabel,
            playerLevelLabel,
            playerHealthLabel,
            playerWeaponLabel,
            playerStaminaLabel,
            playerACLabel,
            playerInitiativeLabel,
            playerHitDieLabel,
            playerStrengthLabel,
            playerDexterityLabel,
            playerConstitutionLabel,
            playerIntelligenceLabel,
            playerWisdomLabel,
            playerCharismaLabel,
            playerWeaponDamageLabel,
            playerWeaponSpeedLabel,
            playerWeaponCritLabel,
            offhandDamageLabel,
            offhandSpeedLabel,
            offhandCritLabel,
            armorBonusLabel,
            armorDamageReductionLabel,
            armorSpeedReductionLabel,
            choiceLabel;

    // Buttons facilitate some player actions
    JButton startButton;

    // Text Areas are used to facilitate printing out the dialog, prompts, explanations, responses, etc.
    JTextArea mainTextArea;

    // Text Fields provide players with a free form method of game interaction
    JTextField playerInputField;

    // Handlers listen for various triggers and kickoff when appropriate
    TitleHandler tsHandler;
    PlayerInputHandler pHandler;

    // Fonts for different situations / resolutions
    Font
            titleFont,
            menuFont;

    // Colors as variables makes changes easier later
    Color
            uiBackgroundColor,
            statsBackgroundColor;

    // Old?
    GridLayout statsLayout;
    BoxLayout nameClassLayout; //, choiceLayout;


    // Important variables that are common across game
    Boolean gameStarted = false;
    Integer gameWidth = 1280;
    Integer gameHeight = 720;
    String playerInput = "";
    String playerClassDesc = "";

    // State tracking for what screen is being managed & displayed to the player
    enum CurrentScreen {
        TITLE,
        EXPLORATION,
        COMBAT,
        PAUSE,
        INVENTORY
    }

    // Generate screen state object
    CurrentScreen currentScreen;

    /**
     *  Draws UI for the first time and displays title menu for player
     */
    public void createUI(){

        currentScreen = CurrentScreen.TITLE;

        // UI learning area
        createUIBox();

        if (Game.gameState == Game.GameState.WAITING_ON_GAME_START) {
            createTitleScreen();
        }

        ui.setVisible(true);
    }

    /**
     *
     */
    public void createGameScreen(){
        //<editor-fold defaultstate="collapsed" desc="temporary minimize">

        // Clear title screen so there is area for text screens
        mainCon.remove(titleNamePanel);
        mainCon.remove(startButtonPanel);

        // Call method to update UI elements
        refreshGameScreen();

        // Store background color for the stats block element as an RGB value
        statsBackgroundColor = new Color(168, 161, 103);

        // Set the grid layout for the stats panel to use.
//        statsLayout = new GridLayout(3, 5, 8, 8);
//        nameClassLayout = new BoxLayout();

        /*
         * Create UI element that will contain player details:
         *  - Anchored in top left of ui, with offset down and to the right by 16 pixels, respectively
         *  - 1/4 of UI height minus 16 pixels to accommodate padding size
         *  - Make element a grid with a single row and padding of 8 pixels on all sides
        */
        playerPanel = new JPanel();
        playerPanel.setBackground(statsBackgroundColor);
//        playerPanel.setBounds(16, 16, gameWidth - 16, 160);
        playerPanel.setLocation(16, 16);
        playerPanel.setSize(mainCon.getWidth() - 32, (mainCon.getHeight() / 4) - 16);
        playerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        playerPanel.setLayout(new GridLayout(1, 0, 8, 8));
        mainCon.add(playerPanel);

        // Update UI
        refreshGameScreen();

        // Store 1/8th the width of player details element and 1/3 the width of height for later use
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

        // Create player stat box that will display Player name, class, and armor class
        nameClassACPanel = new JPanel();
        nameClassACPanel.setBackground(statsBackgroundColor);
        nameClassACPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        nameClassACPanel.setLayout(new BoxLayout(nameClassACPanel, BoxLayout.PAGE_AXIS));

        // Create labels to display player info
        playerNameLabel = new JLabel("NAME:\n--");
            playerNameLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerNameLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerNameLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerNameLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        playerClassLabel = new JLabel("CLASS: -- [--]");
            playerClassLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerClassLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerClassLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerClassLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerClassLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));
        playerACLabel = new JLabel("ARMOR CLASS: --");
            playerACLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerACLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerACLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerACLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerACLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));

        // Add each stat label to this element
        nameClassACPanel.add(playerNameLabel);
        nameClassACPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        nameClassACPanel.add(playerClassLabel);
        nameClassACPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        nameClassACPanel.add(playerACLabel);

        // Create player stat box that will display Player hp, initiative, and weapon name
        healthInitiativeWeaponPanel = new JPanel();
        healthInitiativeWeaponPanel.setBackground(statsBackgroundColor);
        healthInitiativeWeaponPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        healthInitiativeWeaponPanel.setLayout(new BoxLayout(healthInitiativeWeaponPanel, BoxLayout.PAGE_AXIS));

        // Create labels to display player info
        playerHealthLabel = new JLabel("HEALTH: -- / -- [d-]");
            playerHealthLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerHealthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerHealthLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerHealthLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerHealthLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        playerInitiativeLabel = new JLabel("INITIATIVE: --");
            playerInitiativeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerInitiativeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerInitiativeLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerInitiativeLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerInitiativeLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));
        playerWeaponLabel = new JLabel("WEAPON: --");
            playerWeaponLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerWeaponLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerWeaponLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerWeaponLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerWeaponLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));

        // Add each stat label to this element
        healthInitiativeWeaponPanel.add(playerHealthLabel);
        healthInitiativeWeaponPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        healthInitiativeWeaponPanel.add(playerInitiativeLabel);
        healthInitiativeWeaponPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        healthInitiativeWeaponPanel.add(playerWeaponLabel);

        // Create player stat box that will display Player weapon stats
        weaponStatsPanel = new JPanel();
        weaponStatsPanel.setBackground(statsBackgroundColor);
        weaponStatsPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        weaponStatsPanel.setLayout(new BoxLayout(weaponStatsPanel, BoxLayout.PAGE_AXIS));

        // Create labels to display player info
        playerWeaponDamageLabel = new JLabel("DAMAGE: --");
            playerWeaponDamageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerWeaponDamageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerWeaponDamageLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerWeaponDamageLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerWeaponDamageLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        playerWeaponSpeedLabel = new JLabel("SPEED: --");
            playerWeaponSpeedLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerWeaponSpeedLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerWeaponSpeedLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerWeaponSpeedLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerWeaponSpeedLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        playerWeaponCritLabel = new JLabel("CRIT %: --");
            playerWeaponCritLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerWeaponCritLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerWeaponCritLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerWeaponCritLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerWeaponCritLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));

        // Add each stat label to this element
        weaponStatsPanel.add(playerWeaponDamageLabel);
        weaponStatsPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        weaponStatsPanel.add(playerWeaponSpeedLabel);
        weaponStatsPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        weaponStatsPanel.add(playerWeaponCritLabel);

        // Create player stat box that will display Player strength, dexterity, and constitution
        strDexConPanel = new JPanel();
        strDexConPanel.setBackground(statsBackgroundColor);
        strDexConPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        strDexConPanel.setLayout(new BoxLayout(strDexConPanel, BoxLayout.PAGE_AXIS));

        // Create labels to display player info
        playerStrengthLabel = new JLabel("STRENGTH: -- (--)");
            playerStrengthLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerStrengthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerStrengthLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerStrengthLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerStrengthLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        playerDexterityLabel = new JLabel("DEXTERITY: -- (--)");
            playerDexterityLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerDexterityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerDexterityLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerDexterityLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerDexterityLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));
        playerConstitutionLabel = new JLabel("CONSTITUTION: -- (--)");
            playerConstitutionLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerConstitutionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerConstitutionLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerConstitutionLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerConstitutionLabel.setMaximumSize(new Dimension(maxLabelSizeWidth,maxLabelSizeHeight));

        // Add each stat label to this element
        strDexConPanel.add(playerStrengthLabel);
        strDexConPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        strDexConPanel.add(playerDexterityLabel);
        strDexConPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        strDexConPanel.add(playerConstitutionLabel);

        // Create player stat box that will display Player intelligence, wisdom, and charisma
        intWisChaPanel = new JPanel();
        intWisChaPanel.setBackground(statsBackgroundColor);
        intWisChaPanel.setBounds(16, 16, playerPanelElementsWidth, 140);
        intWisChaPanel.setLayout(new BoxLayout(intWisChaPanel, BoxLayout.PAGE_AXIS));

        // Create labels to display player info
        playerIntelligenceLabel = new JLabel("INTELLIGENCE: -- (--)");
            playerIntelligenceLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerIntelligenceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerIntelligenceLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerIntelligenceLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerIntelligenceLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        playerWisdomLabel = new JLabel("WISDOM: -- (--)");
            playerWisdomLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerWisdomLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerWisdomLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerWisdomLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerWisdomLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));
        playerCharismaLabel = new JLabel("CHARISMA: -- (--)");
            playerCharismaLabel.setBorder(BorderFactory.createLineBorder(Color.black));
            playerCharismaLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            playerCharismaLabel.setMinimumSize(new Dimension(minLabelSizeWidth, minLabelSizeHeight));
            playerCharismaLabel.setPreferredSize(new Dimension(prefLabelSizeWidth, prefLabelSizeHeight));
            playerCharismaLabel.setMaximumSize(new Dimension(maxLabelSizeWidth, maxLabelSizeHeight));

        // Add each stat label to this element
        intWisChaPanel.add(playerIntelligenceLabel);
        intWisChaPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        intWisChaPanel.add(playerWisdomLabel);
        intWisChaPanel.add(Box.createRigidArea(new Dimension(0, 8)));
        intWisChaPanel.add(playerCharismaLabel);

//        // </editor-fold>

        // Add each stat element box to the player panel
        playerPanel.add(nameClassACPanel);
        playerPanel.add(healthInitiativeWeaponPanel);
        playerPanel.add(weaponStatsPanel);
        playerPanel.add(strDexConPanel);
        playerPanel.add(intWisChaPanel);

        // Update UI
        refreshGameScreen();

        // Create Text Panel then add it to the UI
        mainTextPanel = new JPanel();
        mainTextPanel.setBackground(Color.black);
        mainTextPanel.setBounds(playerPanel.getX(), playerPanel.getY() + playerPanel.getHeight(), playerPanel.getWidth(),
                            ((mainCon.getHeight() / 2) + (mainCon.getHeight() / 8) + (mainCon.getHeight() / 16)) - 16);
        mainCon.add(mainTextPanel);

        // Create Text Panel Label and display text so you know what element it is
        mainTextLabel = new JLabel("GAME CONSOLE");
        mainTextPanel.add(mainTextLabel);

        // Create ui panel at bottom of UI that will behave as the interaction console
        playerInputPanel = new JPanel();
        playerInputPanel.setBounds(playerPanel.getX(), mainTextPanel.getY() + mainTextPanel.getHeight(), mainTextPanel.getWidth(), (mainCon.getHeight() / 16));
        playerInputPanel.setBackground(Color.darkGray);

        // Add a '>' character to the ui that looks like the row pointer for a terminal
        JLabel playerInputLabel = new JLabel(">");        ////////Trying to add a > right before the text field like the HTML game. Not displaying currently.
        playerInputLabel.setLocation(playerInputPanel.getX() + 8, playerInputPanel.getY());
        playerInputLabel.setFont(new Font("Arial", Font.BOLD, playerInputPanel.getWidth() / 50));
        playerInputLabel.setForeground(Color.black);
        playerInputPanel.add(playerInputLabel);

        // Create player input handler that will listen for player input
        pHandler = new PlayerInputHandler();

        // Create player input field and add the input handler to it so that user input can be processed
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

        // Add the player input elements to the game UI
        mainCon.add(playerInputPanel);

        // Create Choice Panel Label and display text so you know what element it is
//        choiceLabel = new JLabel("Make your choice");
//        choiceButtonPanel.add(choiceLabel);

        // Run method to create game console
        createMainTextArea();

        // Update game UI
        refreshGameScreen();

        gameStarted = true;

        // Set the focused element of the game to the player input text field now that the UI has been drawn fully
        playerInputField.requestFocusInWindow();

        // Print the first dialog array to the console
        Functions.slowPrintText(Dialog.chatTracker[Game.dialog.chatLevel], Game.ui.mainTextArea);

    }

    /**
     * Builds the UI object:
     *  - sets background color
     *  - sets preferred width and height based on class variables
     *  - sets primary container to the bounds of the game window
     */
    private void createUIBox() {
        uiBackgroundColor = new Color(19, 99, 83);

        // Create UI. Currently static size of 1280 x 720, or gameWidth x gameHeight. Will figure out how to make this dynamic later along with all relevant elements.
        ui = new JFrame();
//        ui.setSize(gameWidth, gameHeight);
        ui.setPreferredSize(new Dimension(gameWidth, gameHeight));
        ui.pack();
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.getContentPane().setBackground(uiBackgroundColor);
        ui.setLayout(null); // Disable the default layout, so we can set our own format.
//        ui.setVisible(true);

        // Create primary container for managing the UI
        mainCon = ui.getContentPane();
    }

    /**
     * Creates and draws elements of the UI to display title screen to player
     *  - Sets title screen fonts
     *  - Draws game title and start button
     */
    private void createTitleScreen() {

        // Create Font for title label
        titleFont = new Font("Courier", Font.PLAIN, 40);
        // Create Font for menu buttons
        menuFont = new Font("Times New Roman", Font.PLAIN, 28);


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
    }

    /**
     * Updates game UI with current states of all objects / variables
     */
    private void refreshGameScreen() {
        mainCon.revalidate();
        mainCon.repaint();
    }

    /**
     * Creates the text area where game text / dialog will be drawn for the player
     *  - Using monospaced font for a classic text game feeling
     *  - Wrap text if it exceeds length of the box
     *  - Disable editing of the text
     */
    private void createMainTextArea() {
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
    }

    /** Calls slowPrint method from Functions class to print each character in a text array with a short delay
     *  on each character index. This creates the illusion of text being 'written' in real time for the player
     */
    public void displayText(){
        Functions.slowPrintText(Dialog.chatTracker[Game.dialog.chatLevel], Game.ui.mainTextArea);
    }

    /**
     * Update text box to be empty by overwriting existing element value
     */
    public void clearText(){
        mainTextArea.setText("");
    }

    /**
     * Gets string from text field and passes it over to the Game class to be parsed and worked
     *  - Take input and convert it to upper case characters
     *      -- Creates consistent parsing of text
     *  - If input ever matches "QUIT" exit game
     *  - Pass value to Game class
     *  - Clear out any text in the input field
     */
    public void getPlayerInput(){
        System.out.println(Game.dialog.chatLevel);

            String playerInput = playerInputField.getText().toUpperCase();
            if (playerInput.equals("QUIT")) {
                System.exit(0);
            }

            System.out.println(Game.gameState);

            Game.handleInput(playerInput);

            playerInputField.setText("");
    }

    /**
     * Old class, deprecated
     */
    public void progressDialog(){
        String playerInput = playerInputField.getText().toUpperCase();
        System.out.println(Game.gameState);

    }

    /** Update text for player name
     *
     * @param playerName
     */
    public void updatePlayerNameLabel(String playerName) {
        playerNameLabel.setText("NAME: " + playerName);
    }

    /** Update text for player class
     *
     * @param className
     * @param classLevel
     */
    public void updatePlayerClassLabel(String className, int classLevel) {
        playerClassLabel.setText("CLASS: " + className + " [" + classLevel + "]");
    }

    /** deprecated
     *
     * @param attackModifier
     */
    public void updatePlayerClassModLabel(String attackModifier) {
        playerLevelLabel.setText("CLASS MOD: " + attackModifier.substring(0, 3));
    }

    /** Update text for player health
     *
     * @param health
     * @param maxHealth
     * @param hitDie
     */
    public void updatePlayerHealthLabel(int health, int maxHealth, int hitDie) {
        playerHealthLabel.setText("HEALTH: " + health + " / " + maxHealth + " [d" + hitDie + "]");
    }

    /** Update text for player
     *
     * @param strength
     * @param strMod
     */
    public void updatePlayerStrengthLabel(int strength, int strMod) {
        playerStrengthLabel.setText("STRENGTH: " + strength + " (" + strMod + ")");
    }

    /** Update text for player dexterity
     *
     * @param dexterity
     * @param dexMod
     */
    public void updatePlayerDexterityLabel(int dexterity, int dexMod) {
        playerDexterityLabel.setText("DEXTERITY: " + dexterity + " (" + dexMod + ")");
    }

    /** Update text for player constitution
     *
     * @param constitution
     * @param conMod
     */
    public void updatePlayerConstitutionLabel(int constitution, int conMod) {
        playerConstitutionLabel.setText("CONSTITUTION: " + constitution + " (" + conMod + ")");
    }

    /** Update text for player intelligence
     *
     * @param intelligence
     * @param intMod
     */
    public void updatePlayerIntelligenceLabel(int intelligence, int intMod) {
        playerIntelligenceLabel.setText("INTELLIGENCE: " + intelligence + " (" + intMod + ")");
    }

    /** Update text for player wisdom
     *
     * @param wisdom
     * @param wisMod
     */
    public void updatePlayerWisdomLabel(int wisdom, int wisMod) {
        playerWisdomLabel.setText("WISDOM: " + wisdom + " (" + wisMod + ")");
    }

    /** Update text for player charisma
     *
     * @param charisma
     * @param charMod
     */
    public void updatePlayerCharismaLabel(int charisma, int charMod) {
        playerCharismaLabel.setText("CHARISMA: " + charisma + " (" + charMod + ")");
    }

    /** Update text for player weapon name
     *
     * @param weaponName
     */
    public void updatePlayerWeaponLabel(String weaponName) {
        playerWeaponLabel.setText("WEAPON: " + weaponName);
    }

    /** deprecated?
     *
     * @param protBonus
     */
    public void updatePlayerProtBonusLabel(String protBonus) {
        armorBonusLabel.setText("PROT_BONUS: " + protBonus);
    }

    /** Update text for player armor class
     *
     * @param ac
     */
    public void updatePlayerACLabel(int ac) {
        playerACLabel.setText("ARMOR CLASS: " + ac);
    }

    /** Update text for player initiative
     *
     * @param dexMod
     */
    public void updatePlayerInitiativeLabel(int dexMod) {
        playerInitiativeLabel.setText("INITIATIVE: " + dexMod);
    }

    /** deprecated?
     *
     * @param hitDie
     */
    public void updatePlayerHitDieLabel(int hitDie) {
        playerHitDieLabel.setText("HIT DIE: d" + hitDie);
    }

    /** Update text for player's weapon damage
     *
     * @param damage
     */
    public void updatePlayerWeaponDamageLabel(int damage) {
        playerWeaponDamageLabel.setText("DAMAGE: " + damage);
    }

    /** Update text for player's weapon crit chance
     *
     * @param critChance
     */
    public void updatePlayerWeaponCritLabel(double critChance) {
        playerWeaponCritLabel.setText("CRIT %: " + critChance);
    }

    /** Update text for player's weapon attack speed
     *
     * @param atkSpeed
     */
    public void updatePlayerWeaponAtkSpeedLabel(double atkSpeed) {
        playerWeaponSpeedLabel.setText("ATK SPEED: " + atkSpeed);
    }

    /** Run each updatePlayerNNNLabel, passing relevant variables into each, then update ui to reflect any changes made to the player panel only
     *
     * @param player
     */
    public void updatePlayerPanelLabels(Player player) {
        updatePlayerNameLabel(player.name);
        updatePlayerClassLabel(player.className, player.level);
        updatePlayerHealthLabel(player.playerHealth, player.maxPlayerHealth, player.hitDie);
        updatePlayerInitiativeLabel(player.dexMod);
        updatePlayerStrengthLabel(player.playerStrength, player.strMod);
        updatePlayerDexterityLabel(player.playerDexterity, player.dexMod);
        updatePlayerConstitutionLabel(player.playerConstitution, player.conMod);
        updatePlayerIntelligenceLabel(player.playerIntelligence, player.intMod);
        updatePlayerWisdomLabel(player.playerWisdom, player.wisMod);
        updatePlayerCharismaLabel(player.playerCharisma, player.chaMod);
        updatePlayerACLabel(player.classArmor);
        updatePlayerWeaponLabel(player.weaponName);
        updatePlayerWeaponDamageLabel(player.attackDamage);
        updatePlayerWeaponCritLabel(player.criticalChance);
        updatePlayerWeaponAtkSpeedLabel(player.attackSpeed);
//        updatePlayerProtBonusLabel(player.protBonus);     // Will enable later once we are using the armor type

        playerPanel.repaint();
        playerPanel.revalidate();
    }

    /*
        Helper class that allows us to kick off an action when the player click the 'Start' button on the title screen
            and updates game state via setter method call to expect next input to be the players chosen name
    */
    public class TitleHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            createGameScreen();
//            currentScreen = CurrentScreen.EXPLORATION;
            Game.setGameState(Game.GameState.WAITING_ON_PLAYER_NAME);
        }
    }

    /*
        Helper class that allows us to kick off an action that runs getPlayerInput method based off:
        - Player input field is not empty
        - Enter key was pressed while input field was the current selection
     */
    public class PlayerInputHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            getPlayerInput();
        }
    }

}
