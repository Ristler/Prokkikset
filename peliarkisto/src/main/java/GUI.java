
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
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
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
        
        //PELIN TILA NAPIT! TÄSSÄ ON MAJOR BUGI, VOI JOHTUA LAYOUTISTA. KOKEILE WINDOWS
        JRadioButton aloitettuNappi = new JRadioButton("Aloitettu");
        JRadioButton keskenNappi = new JRadioButton("Kesken");
        JRadioButton pelattuNappi = new JRadioButton("Pelattu läpi");
        JCheckBox pelaan = new JCheckBox("Pelaan nyt");
        ButtonGroup ryhmaTila = new ButtonGroup();
        ryhmaTila.add(aloitettuNappi);
        ryhmaTila.add(keskenNappi);
        ryhmaTila.add(pelattuNappi);
        ryhmaTila.add(pelaan);
        
        
        
        
        
        
        
        lisaaPeli = new JButton("Lisää peli");
        muokkaaPeli = new JButton("Muokkaa peliä");
        lisaaPeli.addActionListener(this);
        muokkaaPeli.addActionListener(this);
        
        lisaaPeli.setBounds(420, 310, 200, 21);
        muokkaaPeli.setBounds(190, 310, 200, 21);
        
        
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
        
        
        //TICKBOXIT PANEL
        JPanel tick = new JPanel(new FlowLayout());
        tick.add(aloitettuNappi);
        tick.add(keskenNappi);
        tick.add(pelattuNappi);
        tick.add(pelaan);
        
        
        tick.setBounds(185, 125, 400, 30);
        
        
        
        //KÄY LÄPI.
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
        kehys.add(tick);
        
        
        
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
            if(onkoArkistossa(p)==false) {
            pelit.add(p);
            peliLista.addElement(p.getNimi());
            tyhjennaKentat();
            }else if(onkoArkistossa(p)==true) {
                showMessageDialog(null, "Peli on jo arkistossa!", "Tallentaminen ei onnistu", ERROR_MESSAGE);
            
        
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
    public boolean onkoArkistossa(Peli p) {
             for(Peli onko : pelit) {
             if(onko.getNimi().equals(p.getNimi())) {
                 return true;
        }
   }           return false;
}
}        
        
    
        
        
    