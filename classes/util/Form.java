package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;





public class Form extends JDialog{


	
private static final long serialVersionUID = 1L;



private JTextField tf_id_soft;
private JTextField tf_cod_cliente;
private JTextField tf_cod_soft;
private JTextField tf_data;
private JTextField tf_cont_dias;
private JTextField tf_chave;


private JComboBox<String> tipo;




	public Form(){
		
	super();
		
	this.setTitle("Pague o Aluguel - Gerador");
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setSize(500 , 355);
	this.setLocationRelativeTo(null);
	this.setLayout(new GridBagLayout());
	this.setModal(true);
	this.getContentPane().setBackground(Color.WHITE);  
	this.setResizable(false);
	
	this.adicionarComponentes();	
	}
	
	
	
	
	
	public void adicionarComponentes(){
		
	JPanel p1 = new JPanel(){
			
	private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g){
				
		super.paintComponent(g);
			
		Graphics2D g2 = (Graphics2D) g.create();	

		int largura  = this.getWidth();
		int altura  = this.getHeight();
		
	
		g2.setColor(Color.black);
		g2.fillRect( 0, 0,   largura, altura);
		
			try {  
			
			g2.drawImage( ImageIO.read( getClass().getResource("/icons/fundo.jpg")) , 0, 0, this  );		
			} 
			catch (IOException e) {} 
		}};
	p1.setLayout(new GridBagLayout());
	
	
	GridBagConstraints cons = new GridBagConstraints();  

	cons.fill = GridBagConstraints.BOTH;
	cons.gridwidth = GridBagConstraints.REMAINDER;
	cons.weighty =1;
	cons.weightx = 1;	
	this.add(p1, cons);
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	cons.gridwidth = 1;
	p1.add(new JLabel("Tipo:"), cons);
	p1.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel(""), cons);	
	
	cons.gridwidth = 1;
	cons.insets = new Insets(2, 2, 2, 2);
	p1.add(this.tipo = new JComboBox<String>(new String[]{"0", "1", "2"}), cons);
	
	p1.add(new JLabel(""), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel(""), cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("<html><font color=white>ID Software:</font><font color=red>*</font></html>"), cons);
	p1.add(new JLabel("<html><font color=white>Cód. Cliente:</font><font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel("<html><font color=white>Cód. Software:</font><font color=red>*</font></html>"), cons);
		
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_id_soft = new JTextField();
	this.tf_id_soft.setDocument( new Format_TextField_MaxLengthOnlyNum( 9, this.tf_id_soft )  ); 
	p1.add(this.tf_id_soft, cons);
	
	this.tf_cod_cliente = new JTextField();
	this.tf_cod_cliente.setDocument( new Format_TextField_MaxLengthOnlyNum( 8, this.tf_cod_cliente )  ); 
	p1.add(this.tf_cod_cliente, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	this.tf_cod_soft = new JTextField();
	this.tf_cod_soft.setDocument( new Format_TextField_MaxLengthOnlyNum( 4, this.tf_cod_soft )  ); 
	p1.add(this.tf_cod_soft, cons);
	
	
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.gridwidth  = 1;	
	cons.weighty  = 0;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("<html><font color=white>Data:</font><font color=red>*</font></html>"), cons);
	p1.add(new JLabel("<html><font color=white>Num. de Dias:</font><font color=red>*</font></html>"), cons);
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel(""), cons);
	
	
	cons.gridwidth  = 1;	
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_data = new JTextField();
	this.tf_data.setDocument( new Format_TextField_Date( this.tf_data )  ); 
	p1.add(this.tf_data, cons);
	
	this.tf_cont_dias = new JTextField();
	this.tf_cont_dias.setDocument( new Format_TextField_MaxLengthOnlyNum( 3, this.tf_cont_dias )  ); 
	p1.add(this.tf_cont_dias, cons);
	
	cons.gridwidth  = GridBagConstraints.REMAINDER;
	p1.add(new JLabel(""), cons);
	
	cons.fill = GridBagConstraints.NONE;
	cons.weightx = 0;
	cons.ipadx = 35;
	cons.insets = new Insets(15, 2, 5, 2);
	cons.gridwidth  = GridBagConstraints.REMAINDER;	
	JButton bt_salvar  = new JButton("gerar");
	p1.add(bt_salvar, cons);
	
	cons.fill = GridBagConstraints.HORIZONTAL;
	cons.weightx = 1;
	cons.insets = new Insets(2, 2, 0, 2);
	p1.add(new JLabel("<html><font color=white>Chave:</font><font color=red>*</font></html>"), cons);
	
	cons.ipadx = 0;
	cons.insets = new Insets(2, 2, 2, 2);
	this.tf_chave = new JTextField();
	this.tf_chave.setEditable(false);
	p1.add(this.tf_chave, cons);
	
	
		bt_salvar.addActionListener( new ActionListener(){
		@Override
		public void actionPerformed( ActionEvent event ){
			    	
		salvar();
		}});
	}
	
	
	
	
	
	
	private void salvar(){
	
	if(!this.validacao())	
	return;	
		
	
	int indice = Integer.parseInt(new StringBuilder(""+this.tf_id_soft.getText().charAt(3)+this.tf_id_soft.getText().charAt(6)).reverse().toString());
	
	String keys1[]={
			"527f57a40354545h",
			"905z35p31645hi92",
			"45789a12098s7g32",
			"90523f89210vjl98",
			"36993c70845j41xc",
			"5c84p6a04s42ncl2",
			"p70632f62120vc87",
			"q078v941j4781sb0",
			"786g6a91p1vk03fc",
			"4579823f21qrfb11",
			"k8562812f9m3bizc",
			"5m6921a56106bdtb",
			"89c303a04xxsf430",
			"0fk2352a3g461qaf",
			"498494268076zzb1",
			"24912g19a0q098i4",
			"7f99a0j3414gbczm",
			"k56723564m719798",
			"zmar2g503sklpdf3",
			"203a4f23p1134fvz"};
	
	
	String keys2[]={
			"152679f545p45ph0",
			"193xzq107mn55342",
			"7a0nmtft1ab0s732",
			"8210vj2l960p018b",
			"36c70845j4oo1xbc",
			"5c8a02ncbpgll2am",
			"p700pvv87u132zan",
			"q0781v7vv5854sb0",
			"71pz981vk03fcqxb",
			"45198z7982bm1li1",
			"k81f9zweazvcc910",
			"5mybdytwgbhlp18c",
			"89qac3f4gjn30zdz",
			"0fk261qaqfk760af",
			"4984mjkrvp8x2zb1",
			"24u091q098qmi4ka",
			"7f91gbcznbz1j8xm",
			"k567m71y9798kx57",
			"zmar2klpztkn0df3",
			"343fv1azmyrd8189"};
		
		
	String keys3[]={
			"19p520vnde041za0",
			"13xbbaq30710mn42",
			"7lknfab0s7319bv2",
			"821p1tftkqrr58b4",
			"36c7ovckf1rx47bc",
			"5clbqqtril21am1z",
			"p70r0ow724zkanj8",
			"qv58oqbz19l0adml",
			"71p37fc4mqcawxba",
			"451u8o9c87m14li1",
			"k8ftqwr1fzrvcc10",
			"ybdyq7a7lo1lk8cj",
			"89jnm0wr3o00zdyu",
			"0fafq0k0az9651af",
			"498v20m7x2wzpb19",
			"24re49qc17i4mk1a",
			"7f0zs9opaxcrmjam",
			"k7mrwoq84kxcp5c7",
			"zr2d4rp7fa3nb65z",
			"374cp4orwc1a9mia"};
	
	
		if(indice < 0 || indice >= keys1.length){
		
		JOptionPane.showMessageDialog(null, "Índice inválido.", "ERRO", JOptionPane.ERROR_MESSAGE);	
		return;	
		}
	
	String key = "";	
		
		switch(Integer.parseInt(this.tipo.getSelectedItem().toString())){
	
		case 0:
		key  = keys1[indice]; 
		break;
			case 1:
			key  = keys2[indice];
			break;	
				case 2:
				key  = keys3[indice];
				break;
		}
		
		

		if(key.length()!= 16){
		
		JOptionPane.showMessageDialog(null, "Índice inválido (menor que 16 bytes).", "ERRO", JOptionPane.ERROR_MESSAGE);	
		return;	
		}
		
		
	String pre_chave = StringUtils.leftPad(this.tf_cod_cliente.getText(), 8, "0");
	pre_chave += StringUtils.leftPad(this.tf_id_soft.getText(), 9, "0");
	pre_chave += StringUtils.leftPad(this.tf_cod_soft.getText(), 4, "0");
	pre_chave += StringUtils.leftPad(this.tf_data.getText().replace("/", ""), 8, "0");
	pre_chave += StringUtils.leftPad(this.tf_cont_dias.getText(), 3, "0");
	
	
	this.tf_chave.setText(this.encrypt(pre_chave, key));	
	}
	
	
	
	
	private boolean validacao(){
		
		if(this.tf_id_soft.getText().length() != 9){
			
		JOptionPane.showMessageDialog(null, "Informe o ID do software.", "ERRO", JOptionPane.ERROR_MESSAGE);	
		return false;			
		}
		
		if(this.tf_cod_cliente.getText().length() == 0){
			
		JOptionPane.showMessageDialog(null, "Informe o código do cliente.", "ERRO", JOptionPane.ERROR_MESSAGE);	
		return false;			
		}
		
		if(this.tf_cod_soft.getText().length() == 0){
			
		JOptionPane.showMessageDialog(null, "Informe o código do software.", "ERRO", JOptionPane.ERROR_MESSAGE);	
		return false;			
		}
		
		if(!this.dateValidation(this.tf_data.getText())){
			
		JOptionPane.showMessageDialog(null, "Informe a data inicial do software.", "ERRO", JOptionPane.ERROR_MESSAGE);	
		return false;			
		}
		
		
		if(this.tf_cont_dias.getText().length() == 0 || Integer.parseInt(this.tf_cont_dias.getText()) == 0){
		
		JOptionPane.showMessageDialog(null, "Informe a quantidade de dias de licença do software.", "ERRO", JOptionPane.ERROR_MESSAGE);	
		return false;			
		}	
		
	return true;
	}
	
	
	

	
	private boolean dateValidation (String date){
		
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	dateFormat.setLenient(false);
		
		try { dateFormat.parse(date); } 
		catch(ParseException e) {return false;}
		
	return true;	
	}
	
	
	
	

	
	private String encrypt( String textopuro, String chaveencriptacao) {
       
	String IV = "AAAAAAAAAAAAAAAA";	
		
	Cipher	encripta = null;
	String result = null;
		
		try {
		encripta = Cipher.getInstance("AES/CBC/NoPadding");
	
		SecretKeySpec key = new SecretKeySpec(chaveencriptacao.getBytes("UTF-8"), "AES");
	
		encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));

		result = new String(Base64.encodeBase64(encripta.doFinal(textopuro.getBytes("UTF-8"))), "UTF-8");  
   
		} 
		catch (	NoSuchAlgorithmException | 
						InvalidAlgorithmParameterException |
						NoSuchPaddingException |
						InvalidKeyException | 
						UnsupportedEncodingException | 
						IllegalBlockSizeException |
						BadPaddingException e2) {
	
		JOptionPane.showMessageDialog(null, "Um erro ocorreu ao gerar a chave serial.", "ERRO", JOptionPane.ERROR_MESSAGE);		
		return "";
		} 
	
	return result;
	}

	

	
}
