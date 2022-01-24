

/**
 * 
 *  
 *Esta classe descende de PlainDocument e pode ser usada, atraves do método subscrito insertString,
* para formatar JTextField's tipo data. na forma: xx/xx/xxxx
* 
*
* esta classe deve ser chamada da sequinte forma:
* 
* JTextField textField = new JTextField( num_colunn);
* textField.setDocument( new Format_TextField_Date(  textField )  ); 
* 
* 
* STATUS = COMPLETA
* 
* 
*/



package util;





import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;





/* o contrutor recebe uma referencia a ao proprio JTextField.
 */
     

public class Format_TextField_Date extends PlainDocument  {  
   
	
	

private static final long serialVersionUID = 1L;


private final  int MAXLENGTH = 8;  
private JTextField textField;

  







    public Format_TextField_Date( JTextField textField ) {  
        
    super();  
    
    this.textField = textField;
    }  

  
    
    
    
    
    
    
    
    
    
    
    
    
   /* o metodo insertString é subscrito da classe PlainDocument e é invocado sempre que o usuario alterar- lo
     * e campo do jtextfield.
     */
  
 
    public void insertString( int offset, String str, AttributeSet attr)  throws BadLocationException {  
       
       
    String aux = "";
    String campo = "";
    int i;
       

     /* str não pode ser null*/ 
    if( str == null )
    return;
   
       
    
    /*adiciona a string str que foi digitada no campo de texto, na posicao correta na string que ja havia
     * no campo de texto caso o usuario cole, adicione no fim, inicio ou meio,
     * alem de remover caracteres invalidos como letras.
     */
    
        for( i = 0 ; i < offset ; i++ ){
        	
        if( getText( i , 1 ).compareTo(  "/") == 0)
        continue;
        
        aux+= getText( i , 1 );	
        }
        
        
        
        
        
        for( i = 0 ; i < str.length() ; i++ ){
        	
        if( str.charAt(i) == '0' || str.charAt(i) == '1' ||str.charAt(i) == '2' ||str.charAt(i) == '3' ||str.charAt(i) == '4' ||
            str.charAt(i) == '5' ||str.charAt(i) == '6' ||str.charAt(i) == '7' ||str.charAt(i) == '8' ||str.charAt(i) == '9' )
        aux+= str.charAt(i);	
        	
        }
     
        
        int posicao_cursor = aux.length(); 	
        if( posicao_cursor > this.MAXLENGTH)
        posicao_cursor = MAXLENGTH;
        
        
        
        
        for( i = offset ; i < getLength() ; i++ ){
        	
        if( getText( i , 1 ).compareTo(  "/") == 0)
        continue;
       
        aux+= getText( i , 1 );	
        }
        
 
        
        
        
        
        for( i = 0 ; i < aux.length() && i < MAXLENGTH; i++){
       		
       
        campo += aux.charAt(i); 
    	
        	if( i  == 1 ||  i == 3 ){
        	campo+="/";
        	
        	if(offset ==  getLength())
        	posicao_cursor++; 
        		
        	}
        }
 	
        
 
        if(offset !=  getLength()){
        	
        if( posicao_cursor > 1)
        posicao_cursor++;
        
        if( posicao_cursor > 4)
        posicao_cursor ++;
        	
        }
        	
        
    
    
     // limpa o campo antes de regrava-lo.  
    this.textField.setText( null );
    
    //grava a nova string no textfield.
    super.insertString( 0, campo , attr); 
    
    
    	
    
    
    

    
    textField.moveCaretPosition(posicao_cursor); // move o cursor para a posição seguinte à string adicionada.
    textField.setSelectionEnd(0);// remove qualquer seleção.
    }  
      
    	
  
    
    
    
    
    
    
}