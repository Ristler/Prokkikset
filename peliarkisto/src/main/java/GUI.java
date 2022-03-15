
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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
import java.util.Scanner;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GUI extends JFrame implements ActionListener {
    private ArrayList<Peli> pelit;///iffy
    private JList<Peli> arkisto;// iff
    private final JButton lisaaPeli;
    private final JButton muokkaaPeli;
    private final DefaultListModel peliLista;
    private final JMenuItem uusi;
    private final JMenuItem uusiPeli;
    private final JMenuItem avaa;
    private final JMenuItem tallenna;
    private final JMenuItem sulje;
    private final JMenuItem poista;
    private FileWriter tiedostonLukija;
    private JTextField nimiKentta;
    private JTextField jarjestelmaKentta;
    private JTextArea kommenttiKentta;
    JRadioButton aloitettuNappi;
    JRadioButton keskenNappi;
    JRadioButton pelattuNappi;
    JCheckBox pelaan;
    ButtonGroup ryhmaTila;
    
    public GUI() {
        pelit = new ArrayList<Peli>();
        arkisto = new JList<>();
        peliLista = new DefaultListModel();
        arkisto.setModel(peliLista);
        
        //JLISTA LISTENER
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
        poista = new JMenuItem("Poista peli");
        //Nyt metodi actionperformed tietää menusta
        uusi.addActionListener(this);
        uusiPeli.addActionListener(this);
        avaa.addActionListener(this);
        tallenna.addActionListener(this);
        sulje.addActionListener(this);
        poista.addActionListener(this);
        tiedostoMenu.add(poista);
        tiedostoMenu.add(uusi);
        tiedostoMenu.add(uusiPeli);
        tiedostoMenu.add(avaa);
        tiedostoMenu.add(tallenna);
        tiedostoMenu.add(sulje);
       
        //NAPIT (TILA)
        aloitettuNappi = new JRadioButton("Aloitettu", false);
        keskenNappi = new JRadioButton("Kesken", false);
        pelattuNappi = new JRadioButton("Pelattu läpi", false);
        pelaan = new JCheckBox("Pelaan nyt", false);
        ryhmaTila = new ButtonGroup();
        ryhmaTila.add(aloitettuNappi);
        ryhmaTila.add(keskenNappi);
        ryhmaTila.add(pelattuNappi);
        ryhmaTila.add(pelaan);
        JPanel tila = new JPanel(new FlowLayout());
        tila.add(aloitettuNappi);
        tila.add(keskenNappi);
        tila.add(pelattuNappi);
        tila.add(pelaan);
        tila.setBounds(185, 125, 400, 30);
        
        
        //NAPIT (ARVIONTI
        JRadioButton arviointi1 = new JRadioButton("1");
        JRadioButton arviointi2 = new JRadioButton("2");
        JRadioButton arviointi3 = new JRadioButton("3");
        JRadioButton arviointi4 = new JRadioButton("4");
        JCheckBox eiArviointia = new JCheckBox("0");
        ButtonGroup ryhmaArviointi = new ButtonGroup();
        ryhmaArviointi.add(arviointi1);
        ryhmaArviointi.add(arviointi2);
        ryhmaArviointi.add(arviointi3);
        ryhmaArviointi.add(arviointi4);
        ryhmaArviointi.add(eiArviointia);
        JPanel arvosanat = new JPanel(new FlowLayout());
        arvosanat.add(arviointi1);
        arvosanat.add(arviointi2);
        arvosanat.add(arviointi3);
        arvosanat.add(arviointi4);
        arvosanat.add(eiArviointia);
        arvosanat.setBounds(200, 200, 400, 30);
        
        
        //LISÄÄ JA MUOKKAA PELI NAPIT
        lisaaPeli = new JButton("Lisää peli");
        muokkaaPeli = new JButton("Muokkaa peliä");
        lisaaPeli.addActionListener(this);
        muokkaaPeli.addActionListener(this);
        lisaaPeli.setBounds(420, 310, 200, 21);
        muokkaaPeli.setBounds(190, 310, 200, 21);
        
        //TEKSTIT 
        JLabel nimiTeksti = new JLabel("Nimi:");
        JLabel jarjestelmaTeksti = new JLabel("Järjestelmä:");
        JLabel perusTeksti = new JLabel("Perustiedot");
        JLabel tilaTeksti = new JLabel("Tila:");
        JLabel kommenttiTeksti = new JLabel("Kommentit:");
        JLabel arviointiTeksti = new JLabel("Arviointi:");
        nimiTeksti.setBounds(250, 50, 300, 20);
        jarjestelmaTeksti.setBounds(210, 75, 300, 20);
        perusTeksti.setBounds(185, 15, 300, 20);
        tilaTeksti.setBounds(185, 100, 300, 20);
        kommenttiTeksti.setBounds(185, 220, 300, 20);
        arviointiTeksti.setBounds(185, 180, 300, 20);
        
        //TEKSTIKENTÄT
        nimiKentta = new JTextField();
        jarjestelmaKentta = new JTextField();
        nimiKentta.setBounds(290, 50, 290, 20);
        jarjestelmaKentta.setBounds(290, 75, 290, 20);
        kommenttiKentta = new JTextArea();
        kommenttiKentta.setBounds(185, 240, 440, 70); // FIX.
        
        
        //Arkiston lista
        JScrollPane ark = new JScrollPane();
        ark.setViewportView(arkisto);
        ark.setBounds(5, 10, 170, 300);
        
        
        //KEHYS
        JFrame kehys = new JFrame();
        kehys.setSize(650,400);
        kehys.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        kehys.setJMenuBar(menu);
        kehys.add(nimiTeksti);
        kehys.add(jarjestelmaTeksti);
        kehys.add(perusTeksti);
        kehys.add(tilaTeksti);
        kehys.add(kommenttiTeksti);
        kehys.add(arviointiTeksti);
        kehys.add(nimiKentta);
        kehys.add(jarjestelmaKentta);
        kehys.add(kommenttiKentta);
        kehys.add(tila);
        kehys.add(arvosanat);
        kehys.setResizable(false);
        kehys.add(lisaaPeli);
        kehys.add(muokkaaPeli);
        kehys.add(ark);
        kehys.setLayout(null);
        kehys.setSize(650, 400);
        kehys.setVisible(true);
       }
    @Override 
    public void actionPerformed(ActionEvent x) {
        if(x.getSource()==uusiPeli) {
           tyhjennaKentat();
           nimiKentta.setEnabled(true);
           jarjestelmaKentta.setEnabled(true);
        
        }else if(x.getSource()==uusi) {
            int vastaus = JOptionPane.showConfirmDialog(this,"Kaikki tallentamattomat tiedot poistetaan, oletko varma?", "Varoitus!",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(vastaus==JOptionPane.YES_OPTION) {
               pelit.clear();
               peliLista.removeAllElements();
               nimiKentta.setText("");
               jarjestelmaKentta.setText("");
               kommenttiKentta.setText("");
            }
        
        }else if(x.getSource()==sulje) {
            System.exit(0); 
        
        
        }else if(x.getSource()==muokkaaPeli) {
            int indexi = arkisto.getSelectedIndex();
                if(indexi >= 0) {
                    Peli p = pelit.get(indexi);
                    p.setKommentit(kommenttiKentta.getText());
                    kommenttiKentta.setText(p.getKommentit());
                }
        }else if(x.getSource()==lisaaPeli) {
            if(nimiKentta.getText().isEmpty()) {
                showMessageDialog(null, "Lisää nimi!", "Tallentaminen ei onnistunut", ERROR_MESSAGE);
            } else if(!nimiKentta.getText().isEmpty()) {
            Peli p = new Peli(nimiKentta.getText(), jarjestelmaKentta.getText(), kommenttiKentta.getText());
            if(onkoArkistossa(p)==false) {
            
            pelit.add(p);
            peliLista.addElement(p.getNimi());
            showMessageDialog(null, "Peli " + p.getNimi() + " on lisätty arkistoon!", "Ilmoitus", JOptionPane.INFORMATION_MESSAGE);
            tyhjennaKentat();
            }else if(onkoArkistossa(p)==true) {
                showMessageDialog(null, "Peli on jo arkistossa!", "Tallentaminen ei onnistunut", ERROR_MESSAGE);
            
            }
            }
        }else if(x.getSource()==avaa) {
            JFileChooser Avaa = new JFileChooser();
            int vastaus = Avaa.showOpenDialog(this); //Valitsee tiedoston jota avataan
            if(vastaus == JFileChooser.APPROVE_OPTION) {
                peliLista.removeAllElements();
                pelit.clear();
                tyhjennaKentat();
                
                File file = new File(Avaa.getSelectedFile().getAbsolutePath());
                
                
                try {
                    Scanner scan = new Scanner(file);
                    while(scan.hasNextLine()) {
                        
                        String rivi = scan.nextLine();
                        String[] palat = rivi.split(",");
                        
                        String nimi = palat[0];
                        String jarjestelma = palat[1];
                        String kommentit = palat[2];
                        
                       
                        
                        
                        Peli p = new Peli(nimi, jarjestelma, kommentit);
                        
                        pelit.add(p);
                        peliLista.addElement(p.getNimi());
                        
                    
                        //DEBUG
                        System.out.println(nimi);
                        
                    
                    }
                } catch(IOException e) {
                    
                }
            }
                
        }else if(x.getSource()==poista) {
            
            int indexi = arkisto.getSelectedIndex();
                if(indexi >= 0) {
                    Peli p = pelit.get(indexi);
                    pelit.remove(p);
                    paivitaLista(); 
                    tyhjennaKentat();
                    showMessageDialog(null, "Peli " + p.getNimi() + " on poistettu!", "Ilmoitus", JOptionPane.INFORMATION_MESSAGE);
                
                
        }  
                
                
        }else if(x.getSource()==tallenna) { //TOIMII AIKA HYVIN
            JFileChooser Tallennus = new JFileChooser();
            
            int vastaus = Tallennus.showSaveDialog(this);
            
            if(vastaus == JFileChooser.APPROVE_OPTION) {
                File tiedosto = Tallennus.getSelectedFile();
                
                  try {
            if (!tiedosto.exists()) {
                tiedosto.createNewFile();
            }

            FileWriter fw = new FileWriter(tiedosto);
            Writer output = new BufferedWriter(fw);
            
            for(int i = 0; i < pelit.size(); i++) {
                output.write(pelit.get(i).toString() + "\n");
    
            }
                output.close();

            
        } catch (IOException e) {
            e.printStackTrace();
        
                 
            }
        }
        
        }
    }
    public void paivitaLista() {
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
        
    
        
        
    