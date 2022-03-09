
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JList;
import java.util.ArrayList;
import static java.util.Collections.list;
import javax.swing.DefaultListModel;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//RIGHT CLICK POISTA PELI

public class GUI extends JFrame implements ActionListener {
    
    private ArrayList<Peli> pelit;///iffy
    private JList<Peli> arkisto;// iff
    private JButton lisaaPeli;
    private JButton muokkaaPeli;
    private DefaultListModel peliLista;
    private JMenuItem uusi;
    private JMenuItem uusiPeli;
    private JMenuItem avaa;
    private JMenuItem tallenna;
    private JMenuItem sulje;
    private FileWriter tiedostonLukija;
    private JTextField nimiKentta;
    private JTextField jarjestelmaKentta;
    private JTextField kommenttiKentta;
   
  
    public GUI() {
        
        
        pelit = new ArrayList<Peli>();
        arkisto = new JList<>();
        peliLista = new DefaultListModel();
        arkisto.setModel(peliLista);
        
        arkisto.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                nimiKentta.setEnabled(true);
                jarjestelmaKentta.setEnabled(true);
                
                int indexi = arkisto.getSelectedIndex();
                //Jos listassa on pelejä sitten vasta runnataan metodi
                if(indexi >= 0) {
                    Peli p = pelit.get(indexi);
                    nimiKentta.setText(p.getNimi());
                    jarjestelmaKentta.setText(p.getJarjestelma());
                    kommenttiKentta.setText(p.getKommentit());
                    nimiKentta.setEnabled(false);
                    jarjestelmaKentta.setEnabled(false); 
                }
            }
        });
        //Menu
        JMenuBar menu = new JMenuBar();
        JMenu tiedostoMenu = new JMenu("Tiedosto");
        menu.add(tiedostoMenu);
        //Menun sisällä olevat optiot
        uusi = new JMenuItem("Uusi arkisto");
        uusiPeli = new JMenuItem("Uusi peli");
        
        avaa = new JMenuItem("Avaa");
        tallenna = new JMenuItem("Tallenna");
        sulje = new JMenuItem("Sulje");
        //Nyt metodi actionperformed tietää menusta
        uusi.addActionListener(this);
        uusiPeli.addActionListener(this);
        avaa.addActionListener(this);
        tallenna.addActionListener(this);
        sulje.addActionListener(this);
        
        //TILA NAPIT!
        JRadioButton aloittamattaNappi = new JRadioButton("Aloitettu");
        JRadioButton keskenNappi = new JRadioButton("Kesken");
        JRadioButton pelattuNappi = new JRadioButton("Pelattu läpi");
        JCheckBox pelaan = new JCheckBox("Pelaan nyt");
        
        
        lisaaPeli = new JButton("Lisää peli");
        muokkaaPeli = new JButton("Muokkaa peliä");
        lisaaPeli.addActionListener(this);
        muokkaaPeli.addActionListener(this);
        
        lisaaPeli.setBounds(420, 310, 200, 21);
        muokkaaPeli.setBounds(190, 310, 200, 21);
        
        aloittamattaNappi.setBounds(185, 125, 300, 20);
        keskenNappi.setBounds(300, 125, 300, 20);

        // Tekstit! 
        JLabel nimiTeksti = new JLabel();
        JLabel jarjestelmaTeksti = new JLabel();
        JLabel perusTeksti = new JLabel();
        JLabel tilaTeksti = new JLabel();
        JLabel kommenttiTeksti = new JLabel();
        
        
        nimiTeksti.setText("Nimi:");
        jarjestelmaTeksti.setText("Järjestelmä:");
        perusTeksti.setText("Perustiedot");
        tilaTeksti.setText("Tila");
        kommenttiTeksti.setText("Kommentit:");
        
        nimiTeksti.setBounds(250, 50, 300, 20);
        jarjestelmaTeksti.setBounds(210, 75, 300, 20);
        perusTeksti.setBounds(185, 15, 300, 20);
        tilaTeksti.setBounds(185, 100, 300, 20);
        kommenttiTeksti.setBounds(185, 220, 300, 20);
        
        
        //Tekstiboxit!
        nimiKentta = new JTextField();
        jarjestelmaKentta = new JTextField();
        kommenttiKentta = new JTextField();
        nimiKentta.setBounds(290, 50, 290, 20);
        jarjestelmaKentta.setBounds(290, 75, 290, 20);
        kommenttiKentta.setBounds(185, 240, 440, 70); // FIX.
        
        
        //TYÖSTÄ NYT.
        JLabel label = new JLabel();
        JPanel pelit = new JPanel();
        pelit.setBackground(Color.red);
        pelit.setBounds(150, 240, 440, 70);
        
        arkisto.setLayout(null);
        arkisto.setBounds(5, 10, 160, 300);
        pelit.add(label);
        
        // ÄLÄ KOSKE VIEL.
        JPanel kommentit = new JPanel();
        JPanel nimi = new JPanel();
        JPanel jarjestelma = new JPanel();
        //Paneelejen Värit!
        kommentit.setBackground(Color.white);
        nimi.setBackground(Color.white);
        jarjestelma.setBackground(Color.white);
        //Paneelejen Koko!
         
        //Kehys, Tänne lisätään nappeja, paneeleja, jne!
        JFrame kehys = new JFrame();
        kehys.setSize(650,400);
        kehys.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //ÄLÄ KOSKE.
        tiedostoMenu.add(uusi);
        tiedostoMenu.add(uusiPeli);
        tiedostoMenu.add(avaa);
        tiedostoMenu.add(tallenna);
        tiedostoMenu.add(sulje);
        kehys.setJMenuBar(menu);
        kehys.setResizable(true);
        kehys.add(keskenNappi);
        kehys.add(aloittamattaNappi);
        
        //ÄLÄ KOSKE.
        kehys.add(nimiTeksti);
        kehys.add(jarjestelmaTeksti);
        kehys.add(perusTeksti);
        kehys.add(tilaTeksti);
        kehys.add(kommenttiTeksti);
        
        //EHKÄ FIX
        kehys.add(nimiKentta);
        kehys.add(jarjestelmaKentta);
        kehys.add(kommenttiKentta);
        
        kehys.setResizable(false);
        kehys.add(lisaaPeli);
        kehys.add(muokkaaPeli);
        kehys.add(arkisto);
        
        kehys.setLayout(null);
        kehys.setSize(650, 400);
        kehys.setVisible(true);
    
       }
    @Override 
    public void actionPerformed(ActionEvent x) {
        //VALMIS!
        if(x.getSource()==sulje) {
            System.exit(0); 
        }
        
        if(x.getSource()==lisaaPeli) {
            
            Peli p = new Peli(nimiKentta.getText(), jarjestelmaKentta.getText(), kommenttiKentta.getText());
            pelit.add(p);
            peliLista.addElement(p.getNimi());
            
            tyhjennaKentat();
        
        //KESKEN
        if(x.getSource()==avaa) {
            JFileChooser etsija = new JFileChooser();
            int vastaus = etsija.showOpenDialog(null); // select file to open
            
        if(vastaus == JFileChooser.APPROVE_OPTION) {
               File tiedosto =  new File(etsija.getSelectedFile().getAbsolutePath());
        }
        
        if(x.getSource()==uusi) {
           tyhjennaKentat();
           
        }
        }
        }
    }
    //TOIMII
    public void addPeli(Peli p) {
        pelit.add(p);
        refreshPelit();
        
    }
    
    public void refreshPelit() {
        peliLista.removeAllElements();
        for(Peli p : pelit) {
            peliLista.addElement(p.getNimi());
        
        }
    }
    public void tyhjennaKentat() {
            nimiKentta.setText("");
            jarjestelmaKentta.setText("");
            kommenttiKentta.setText("");
    }
}

        
        
    
        
        
    